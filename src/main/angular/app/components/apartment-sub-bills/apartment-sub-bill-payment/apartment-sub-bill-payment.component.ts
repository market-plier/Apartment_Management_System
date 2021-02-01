import {Component, OnInit} from "@angular/core";
import {ApartmentSubBillService} from "../../../services/apartment-sub-bill.service";
import {ApartmentSubBill} from "../../../models/apartment-sub-bill";
import {ApartmentOperation} from "../../../models/apartment-operation";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatDialog} from "@angular/material/dialog";

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
                private router: Router, private _snackBar: MatSnackBar,
                private dialog: MatDialog) {
    }

    public cardMask = [/\d/, /\d/, /\d/, /\d/,'-',/\d/, /\d/, /\d/, /\d/,'-',/\d/, /\d/, /\d/, /\d/,'-',/\d/, /\d/, /\d/, /\d/];
    public cvvMask = [/\d/,/\d/,/\d/];

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

    openSnackBar(message: string, action: string) {
        this._snackBar.open(message, action, {
            duration: 10000,
        });
    }

    pay(): void {
        let apartmentOperations: ApartmentOperation[] = [];
        for (let i = 0; i < this.apartmentSubBills.length; i++) {
            if(this.sums[i] != undefined) {
                apartmentOperations.push({sum: this.sums[i], apartmentSubBill: this.apartmentSubBills[i]})
            } else {
                apartmentOperations.push({sum: 0, apartmentSubBill: this.apartmentSubBills[i]})
            }
        }
        this.apartmentSubBillService.updateApartmentSubBillsByApartmentOperation(apartmentOperations)
            .subscribe(
                response => {
                    this.openSnackBar('Payment successful', '');
                    this.redirectToApartmentSubBillList();
                },
                error => {
                    this.openSnackBar(error.error.message, '');
                    console.log(error);
                });
    }

    redirectToApartmentSubBillList(): void {
        this.router.navigate([`/apartment-sub-bills`])
    }

}