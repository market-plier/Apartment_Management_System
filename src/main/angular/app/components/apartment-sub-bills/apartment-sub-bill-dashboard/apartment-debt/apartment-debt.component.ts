import {Component, OnInit} from '@angular/core';
import {ApartmentSubBill} from "../../../../models/apartment-sub-bill";
import {ApartmentSubBillService} from "../../../../services/apartment-sub-bill.service";
import {Router} from "@angular/router";

@Component({
    selector: 'app-apartment-debt',
    templateUrl: './apartment-debt.component.html',
    styleUrls: ['./apartment-debt.component.scss']
})
export class ApartmentDebtComponent implements OnInit {
    colorDebt = 'red';
    colorBalance = 'green'
    subbills: ApartmentSubBill[] = [];
    debt: number = 0;
    balance: number = 0;

    displayedColumns = ['Communal', 'Debt', 'Balance', 'Pay'];

    constructor(public subbillsSevice: ApartmentSubBillService, private router: Router) {
    }

    ngOnInit(): void {
        this.subbillsSevice.getApartmentSubBillList().subscribe(
            data => {
                this.subbills = data
                var j;
                for (j in this.subbills) {
                    this.debt = this.debt + this.subbills[j].debt;
                    this.balance = this.balance + this.subbills[j].balance;
                }
            });
    }

    makePayment() {
        this.router.navigate(['/apartment-sub-bill-payment']);
    }
}
