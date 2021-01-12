import { Component, OnInit } from '@angular/core';
import {ManagerOperationService} from "../../../services/manager-operation.service";
import {ManagerSubBill} from "../../../models/manager-sub-bill";
import {Subject, Subscription} from "rxjs";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

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
  isCreated:boolean;


  constructor(public managerService: ManagerOperationService,
              private router: Router,
              private route:ActivatedRoute,
              private _snackBar: MatSnackBar) { }

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
      description: new FormControl('',[Validators.required, Validators.minLength(2),Validators.maxLength(1000)]),
      sum:new FormControl('',Validators.required),
      subBillId:new FormControl('',Validators.required)
    })


  }
  onClickSubmit() {
    this.error = null;
    console.log(this.form.value)
    this.managerService.makeSpending(this.form.value).subscribe(   data => {
      this.openSnackBar('Spending is created', 'OK');
      this.isCreated = true;
    },
        error => {
          console.log(error.error.errorCode);
          if (error.error.errorCode===8092)
          {
            this.openErrorSnackBar('Insufficient funds on the balance sheet, cant create', 'OK')
          }else
          {
            this.openErrorSnackBar('Cant create spending!', 'OK')
          }

        }
    );

  }


  openSnackBar(message: string, action: string) {
  this._snackBar.open(message, action, {
    duration: 100000,
  });
  this._snackBar._openedSnackBarRef.onAction().subscribe(() => {
    window.location.reload();
  });
 }

  openErrorSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      panelClass:['.snackBar-fail'],
      duration: 100000,
    });
  }



 refManagerOperationList()
 {
   this.router.navigate(['manager-operation'])
 }



}
