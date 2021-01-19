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
    legendPosition: string = 'below';

    title = "Balance";

    colorScheme = {
        domain: ['#5B5F97', '#C2E812', '#F1DB48', '#FC7753', '#A0C1D1', '#F93943', '#9C0D38']
    };

    single: any[];
    view: any[] = [700, 280];

    constructor(public subbillsSevice: ApartmentSubBillService, public token: TokenStorageService) {
    }

    onResize(event) {
        this.view = [event.target.innerWidth - 500, 280];
    }

    ngOnInit(): void {
        this.subbillsSevice.getApartmentSubBillList().subscribe(
            data => {
                this.subbills = data
                var j;
                var single: single[] = [];
                for (j in this.subbills) {
                    single[j] = {name: this.subbills[j].communalUtility.name, value: this.subbills[j].balance};
                    this.balance = this.balance + this.subbills[j].balance;
                }
                single[3] = {name: "this.subbills[j].communalUtility.name", value: 67};
                single[4] = {name: "this.subbills[5j].communalUtility.name", value: 67};
                single[5] = {name: "this.subbil5ls[5j].communalUtility.name", value: 67};
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
