import { Component, OnInit } from '@angular/core';
import {ManagerService} from "../../services/manager.service";
import {ManagerOperation} from "../../models/manager-operation";
import {Manager} from "../../models/manager";
import {ActivatedRoute, Router} from "@angular/router";
import {ManagerBill} from "../../models/manager-bill";

// @ts-ignore
@Component({
  selector: 'app-manager-info-page',
  templateUrl: './manager-info-page.component.html',
  styleUrls: ['./manager-info-page.component.scss']
})
export class ManagerInfoPageComponent implements OnInit {

  manager: Manager = {
    managerBill: new ManagerBill(),
    accountId: 0,
    firstName: '',
    lastName: '',
    email: '',
    password: null,
    phoneNumber: '',
    role: 'OWNER'
  };

  constructor(private managerService:ManagerService, private router: Router)  { }

  ngOnInit(): void {

    this.managerService.getManagerInfo().subscribe(data =>
    {
      console.log(data)
      this.manager = data;
    });

  }

  updateManager() {
    this.router.navigate(['/manager-info/update'])
  }

}
