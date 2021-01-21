import { Component, OnInit } from '@angular/core';
import {ManagerSubBillService} from "../../services/manager-sub-bill.service";
import {ManagerSubBill} from "../../models/manager-sub-bill";
import {ApartmentSubBill} from "../../models/apartment-sub-bill";


class single {
    name?: String;
    value?: Number;
}

@Component({
  selector: 'app-dashboard-manager-bill-debt',
  templateUrl: './dashboard-manager-bill-debt.component.html',
  styleUrls: ['./dashboard-manager-bill-debt.component.css']
})


export class DashboardManagerBillDebtComponent implements OnInit {

    subbills: ApartmentSubBill[] = [];
    balance: number = 0;

// options
    gradient: boolean = true;
    showLegend: boolean = true;
    showLabels: boolean = true;
    isDoughnut: boolean = false;
    legendPosition: string = 'below';
    maxChars = 30;
    title = "Debt";
    single: any[];
    view: any[] = [800, 260];

    billMap: Map<ManagerSubBill, number> = new Map<ManagerSubBill, number>();
    sumDebt: number=0;
    constructor(private managerSubBillService: ManagerSubBillService) {

    }

    ngOnInit(): void {
        this.managerSubBillService.getManagerSubBillsDebt().subscribe(
            response => {

                var i = 0;
                var single: single[] = [];
                Object.keys(response).forEach(key => {

                        this.billMap.set(JSON.parse(key), response[key]);
                        single[i] = {name: JSON.parse(key).communalUtility.name, value: response[key] }

                        this.sumDebt +=response[key];

                        i++;
                    }
                )
                console.log(single)
                console.log(this.billMap)
                console.log(this.sumDebt)
                Object.assign(this, {single});

            },

            error => {
                console.log(error)
            }

            )

    }


    onResize(event) {
        if (event.target.innerWidth > 1000) {
            this.view = [700, 280];
        } else if (event.target.innerWidth < 1000 && event.target.innerWidth > 700) {
            this.view = [500, 250];
            this.maxChars = 15;
        } else {
            this.view = [300, 150];
            this.maxChars = 10;
        }
        console.log(event.target.innerWidth);
    }



    onSelect(data): void {
        console.log('Item clicked', JSON.parse(JSON.stringify(data)));
    }

    onActivate(data): void {
        console.log('Activate', JSON.parse(JSON.stringify(data)));
    }

    onDeactivate(data): void {
        console.log('Deactivate', JSON.parse(JSON.stringify(data)));
    }
}
