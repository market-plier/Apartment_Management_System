import { Component, OnInit } from '@angular/core';
import {ManagerSubBill} from "../../../models/manager-sub-bill";
import {Subscription} from "rxjs";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ManagerOperationService} from "../../../services/manager-operation.service";

@Component({
  selector: 'app-manager-operation-update',
  templateUrl: './manager-operation-update.component.html',
  styleUrls: ['./manager-operation-update.component.css']
})
export class ManagerOperationUpdateComponent implements OnInit {


  form: FormGroup;

  constructor(private managerService: ManagerOperationService) { }

  ngOnInit(): void {


    this.form = new FormGroup({
      description: new FormControl('',[Validators.required, Validators.minLength(2)]),
      sum:new FormControl('',Validators.required),
      subBillId:new FormControl('',Validators.required)
    })


  }
  onClickSubmit() {
    this.managerService.updateSpending(this.form.value)
  }
}
