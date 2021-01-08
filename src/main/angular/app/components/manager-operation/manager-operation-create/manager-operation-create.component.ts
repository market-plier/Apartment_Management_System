import { Component, OnInit } from '@angular/core';
import {ManagerOperationService} from "../../../services/manager-operation.service";
import {ManagerOperation} from "../../../models/manager-operation";
import {ManagerSubBill} from "../../../models/manager-sub-bill";
import {Subject, Subscription} from "rxjs";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Params, Router} from "@angular/router";

@Component({
  selector: 'app-manager-operation-create',
  templateUrl: './manager-operation-create.component.html',
  styleUrls: ['./manager-operation-create.component.css']
})
export class ManagerOperationCreateComponent implements OnInit {

  subBills: ManagerSubBill[] = []
  pSub: Subscription
  form: FormGroup;

  error:Subject<string>;
  message:string


  constructor(public managerService: ManagerOperationService, private router: Router,private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe((params:Params)=>
    {
      if(params['addedSuccess'])
      {
        this.message = 'Spending added successfully'
      }
    })


    this.error = null;

    this.pSub = this.managerService.getAllManagerSubBill().subscribe(subBills => {
      console.log(subBills);
      this.subBills = subBills
    })

    this.form = new FormGroup({
      description: new FormControl('',[Validators.required, Validators.minLength(2)]),
      sum:new FormControl('',Validators.required),
      subBillId:new FormControl('',Validators.required)
    })


  }
  onClickSubmit() {
    this.error = null;
    console.log(this.form.value)
    this.managerService.makeSpending(this.form.value);
    this.error = this.managerService.error$;
    console.log(this.error)
    if (this.error.observers.length == 0)
    {
      this.form.reset();
      this.router.navigate(['manager-operation/create'], {
        queryParams: {
          addedSuccess: true
        }
      })

    }

  }


}
