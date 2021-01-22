import {Component, OnInit} from "@angular/core";
import {ApartmentSubBillService} from "../../../services/apartment-sub-bill.service";
import {ApartmentOperation} from "../../../models/apartment-operation";
import {ApartmentSubBill} from "../../../models/apartment-sub-bill";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
    selector: 'app-apartment-sub-bill-transfer-create',
    templateUrl: './apartment-sub-bill-transfer-create.component.html',
    styleUrls: ['./apartment-sub-bill-transfer-create.component.css']
})
export class ApartmentSubBillTransferCreateComponent implements OnInit {
    apartmentSubBills?: ApartmentSubBill[];
    fromApartmentSubBill: string;
    toApartmentSubBill: string;
    sum: number;

    ngOnInit() {
        this.retrieveApartmentSubBills();
    }

    constructor(private apartmentSubBillService: ApartmentSubBillService,
                private router: Router, private _snackBar: MatSnackBar) {
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

    transferCreate(): void {
        if (this.sum == undefined) {
            this.sum = 0;
        }
        if (this.fromApartmentSubBill == this.toApartmentSubBill) {
            this.openSnackBar('SubBills are identical, select another', '');
        } else {
            this.apartmentSubBillService.createApartmentSubBillTransfer([this.fromApartmentSubBill, this.toApartmentSubBill, this.sum.toString()])
                .subscribe(
                    response => {
                        this.openSnackBar('Transfer successful', '');
                        this.redirectToApartmentSubBillList();
                        console.log(response);
                    },
                    error => {
                        console.log(error);
                        this.openSnackBar(error.error.message, '');
                    });
        }
    }

    openSnackBar(message: string, action: string) {
        this._snackBar.open(message, action, {
            duration: 10000,
        });
    }

    redirectToApartmentSubBillList(): void {
        this.router.navigate([`/apartment-sub-bills`])
    }

}