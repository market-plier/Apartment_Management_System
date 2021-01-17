import {Component, OnInit} from "@angular/core";
import {ApartmentSubBillService} from "../../../services/apartment-sub-bill.service";
import {ApartmentOperation} from "../../../models/apartment-operation";
import {ApartmentSubBill} from "../../../models/apartment-sub-bill";
import {Router} from "@angular/router";

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
                private router: Router) {
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
        this.apartmentSubBillService.createApartmentSubBillTransfer([this.fromApartmentSubBill, this.toApartmentSubBill, this.sum.toString()])
            .subscribe(
                response=>{
                console.log(response);
            },
                error => {
                    console.log(error);
                });
    }

    redirectToApartmentSubBillList(): void{
        this.router.navigate([`/apartment-sub-bills`])
    }

    transferButtonClick(): void {
        this.transferCreate();
        this.redirectToApartmentSubBillList();
    }
}