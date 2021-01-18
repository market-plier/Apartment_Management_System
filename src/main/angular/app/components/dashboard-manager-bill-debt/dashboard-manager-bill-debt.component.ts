import { Component, OnInit } from '@angular/core';
import {ManagerSubBillService} from "../../services/manager-sub-bill.service";
import {ManagerSubBill} from "../../models/manager-sub-bill";

@Component({
  selector: 'app-dashboard-manager-bill-debt',
  templateUrl: './dashboard-manager-bill-debt.component.html',
  styleUrls: ['./dashboard-manager-bill-debt.component.css']
})


export class DashboardManagerBillDebtComponent implements OnInit {


    billMap: Map<ManagerSubBill, number> = new Map<ManagerSubBill, number>();
    sumDebt: number=0;
    constructor(private managerSubBillService: ManagerSubBillService) {

    }

    ngOnInit(): void {
        this.managerSubBillService.getManagerSubBillsDebt().subscribe(
            response => {

                Object.keys(response).forEach(key => {

                        this.billMap.set(JSON.parse(key), response[key]);
                        this.sumDebt +=response[key];
                    }
                )
                console.log(this.billMap)
                console.log(this.sumDebt)

            },

            error => {
                console.log(error)
            }

            )

    }
}
