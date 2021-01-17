import {Component, OnInit} from "@angular/core";
import {ApartmentSubBillService} from "../../../services/apartment-sub-bill.service";
import {ApartmentSubBill} from "../../../models/apartment-sub-bill";
import {ApartmentOperation} from "../../../models/apartment-operation";
import {Router} from "@angular/router";

@Component({
    selector: 'app-apartment-sub-bill-payment',
    templateUrl: './apartment-sub-bill-payment.component.html',
    styleUrls: ['./apartment-sub-bill-payment.component.css']
})
export class ApartmentSubBillPaymentComponent implements OnInit {
    apartmentSubBills?: ApartmentSubBill[];
    sums: number[] = [];
    displayedColumns: string[] = ['name', 'balance', 'sum'];

    constructor(private apartmentSubBillService: ApartmentSubBillService,
                private router: Router) {
    }

    ngOnInit() {
        this.retrieveApartmentSubBills();
    }

    retrieveApartmentSubBills(): void {
        this.apartmentSubBillService.getApartmentSubBillList()
            .subscribe(
                data => {
                    this.apartmentSubBills = data;
                    console.log(data);
                },
                error => {
                    console.log(error);
                });
    }

    pay(): void {
        let apartmentOperations: ApartmentOperation[] = [];
        for (let i = 0; i < this.apartmentSubBills.length; i++) {
            apartmentOperations.push({sum: this.sums[i], apartmentSubBill: this.apartmentSubBills[i]})
        }
        this.apartmentSubBillService.updateApartmentSubBillsByApartmentOperation(apartmentOperations)
            .subscribe(
                response => {
                    console.log(response);
                },
                error => {
                    console.log(error);
                });
    }

    redirectToApartmentSubBillList(): void {
        this.router.navigate([`/apartment-sub-bills`])
    }

}