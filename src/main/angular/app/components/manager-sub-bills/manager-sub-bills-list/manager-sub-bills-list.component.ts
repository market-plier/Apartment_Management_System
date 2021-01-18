import { Component, OnInit } from '@angular/core';
import {ManagerSubBill} from "../../../models/manager-sub-bill";
import {Router} from "@angular/router";
import {ManagerSubBillService} from "../../../services/manager-sub-bill.service";
import {Observable, of} from "rxjs";
import {map, startWith} from "rxjs/operators";
import {FormControl} from "@angular/forms";
import {MatTableDataSource} from "@angular/material/table";
import {CommunalUtility} from "../../../models/communal-utility";
import {TokenStorageService} from "../../../services/token-storage.service";

@Component({
  selector: 'app-manager-sub-bills-list',
  templateUrl: './manager-sub-bills-list.component.html',
  styleUrls: ['./manager-sub-bills-list.component.scss']
})

export class ManagerSubBillListComponent implements OnInit {

  managerSubBills?: ManagerSubBill[] = [];
  datasource: MatTableDataSource<ManagerSubBill>
  communal: CommunalUtility[];
  displayedColumns: string[] = ['name', 'status', 'deadline', 'calculation', 'balance'];

  constructor(private managerSubBillService: ManagerSubBillService,
              private tokenStorageService: TokenStorageService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getAllManagerSubBills();
  }

    applyFilterDisabled() {
        this.datasource.filter = 'Enabled';
    }

    applyFilterAll() {
        this.datasource.filter = '';
    }

  getAllManagerSubBills() {
    this.managerSubBillService.getAllManagerSubBills()
        .subscribe(data => {
              console.log(data)
              this.managerSubBills = data;
              this.datasource = new MatTableDataSource(data)
            },
            error => {
              console.log(error)
            },
        );
  }

  redirectToManagerSubBillShow(id: number): void{
    this.router.navigate([`/manager-sub-bill/${id}`])
  }

    getRole(): any {
        return this.tokenStorageService.getRole();
    }

}
