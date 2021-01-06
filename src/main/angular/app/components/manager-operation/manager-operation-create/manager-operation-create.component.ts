import { Component, OnInit } from '@angular/core';
import {ManagerOperationService} from "../../../services/manager-operation.service";
import {ManagerOperation} from "../../../models/manager-operation";
import {ManagerSubBill} from "../../../models/manager-sub-bill";
import {Subject, Subscription} from "rxjs";
import {FormControl, FormGroup, Validators} from "@angular/forms";

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
  success:Subject<string>;

  constructor(public managerService: ManagerOperationService) { }

  ngOnInit(): void {


    this.success = this.managerService.success$;
    this.error = this.managerService.error$;

    this.pSub = this.managerService.getAllManagerSubBill().subscribe(subBills => {
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
    this.success = null;
    this.managerService.makeSpending(this.form.value);

  }


}
