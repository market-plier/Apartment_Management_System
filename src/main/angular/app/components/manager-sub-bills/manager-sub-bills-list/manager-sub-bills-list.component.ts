import { Component, OnInit } from '@angular/core';
import {ManagerSubBill} from "../../../models/manager-sub-bill";
import {Data, Router} from "@angular/router";
import {ManagerSubBillService} from "../../../services/manager-sub-bill.service";
import {MatTableDataSource} from "@angular/material/table";
import {CommunalUtility} from "../../../models/communal-utility";
import {TokenStorageService} from "../../../services/token-storage.service";
import {Sort} from '@angular/material/sort';


export interface Dessert {
    idSort: number;
    nameSort: string;
    statusSort: string;
    calculationSort: string;
    balanceSort: number;
}

function compare(a: number | string | Date, b: number | string | Date, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}

@Component({
  selector: 'app-manager-sub-bills-list',
  templateUrl: './manager-sub-bills-list.component.html',
  styleUrls: ['./manager-sub-bills-list.component.scss']
})


export class ManagerSubBillListComponent implements OnInit {

  managerSubBills?: ManagerSubBill[] = [];
  datasource: MatTableDataSource<ManagerSubBill>
  communal: CommunalUtility[];
  sortedData: ManagerSubBill[];
  displayedColumns: string[] = ['id', 'name', 'status', 'deadline', 'calculation', 'balance'];

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

    sortData(sort: Sort) {
        const data = this.managerSubBills.slice();
        if (!sort.active || sort.direction === '') {
            this.sortedData = data;
            return;
        }

        this.sortedData = data.sort((a, b) => {
            const isAsc = sort.direction === 'asc';
            switch (sort.active) {
                case 'idSort': return compare(a.subBillId, b.subBillId, isAsc);
                case 'statusSort': return compare(a.communalUtility.status, b.communalUtility.status, isAsc);
                case 'balanceSort': return compare(a.balance, b.balance, isAsc);
                case 'deadlineSort': return compare(a.communalUtility.deadline, b.communalUtility.deadline, isAsc);
                case 'nameSort': return compare(a.communalUtility.name, a.communalUtility.name, isAsc);
                case 'calculationSort': return compare(a.communalUtility.calculationMethod, a.communalUtility.calculationMethod, isAsc);
                default: return 0;
            }

        });
    }

}
