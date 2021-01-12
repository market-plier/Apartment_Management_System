import { Component, OnInit } from '@angular/core';
import {ManagerService} from "../../services/manager.service";
import {ManagerOperation} from "../../models/manager-operation";
import {Manager} from "../../models/manager";

@Component({
  selector: 'app-manager-info-page',
  templateUrl: './manager-info-page.component.html',
  styleUrls: ['./manager-info-page.component.scss']
})
export class ManagerInfoPageComponent implements OnInit {

  manager: Manager;

  constructor(private managerService:ManagerService)  { }

  ngOnInit(): void {

    this.managerService.getManagerInfo().subscribe(data =>
    {
      console.log(data)
      this.manager = data;
    });


  }

}
