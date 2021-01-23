import {Component, OnInit,} from '@angular/core';
import {ApartmentSubBill} from "../../../models/apartment-sub-bill";
import {ApartmentSubBillService} from "../../../services/apartment-sub-bill.service";
import {TokenStorageService} from "../../../services/token-storage.service";

class single {
    name?: String;
    value?: Number;
}

@Component({
    selector: 'app-apartment-sub-bill-dashboard',
    templateUrl: './apartment-sub-bill-dashboard.component.html',
    styleUrls: ['./apartment-sub-bill-dashboard.component.scss']
})
export class ApartmentSubBillDashboardComponent implements OnInit {

    subbills: ApartmentSubBill[] = [];
    balance: number = 0;

// options
    gradient: boolean = true;
    showLegend: boolean = true;
    showLabels: boolean = true;
    isDoughnut: boolean = false;
    legendPosition: string = 'right';
    maxChars = 30;
    title = "Balance";
    single: any[];
    view: any[] = [1000, 320];
    loading: boolean = false;

    constructor(public subbillsSevice: ApartmentSubBillService, public token: TokenStorageService) {
    }

    onResize(event) {
        if (event.target.innerWidth > 1000) {
            this.view = [1000, 320];
        } else if (event.target.innerWidth < 1000 && event.target.innerWidth > 700) {
            this.view = [700, 310];
            this.maxChars = 15;
        } else {
            this.view = [500, 220];
            this.maxChars = 10;
        }
        console.log(event.target.innerWidth);
    }

    ngOnInit(): void {
        this.loading = true;
        this.subbillsSevice.getApartmentSubBillList().subscribe(
            data => {
                this.subbills = data
                var j;
                var single: single[] = [];
                for (j in this.subbills) {
                    single[j] = {name: this.subbills[j].communalUtility.name, value: this.subbills[j].balance};
                    if (single[j].value == 0) single[j].value = 0.0000001
                    this.balance = this.balance + this.subbills[j].balance;
                }
                this.loading = false;
                Object.assign(this, {single});

            });

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
