import {Component, OnInit} from "@angular/core";
import {ApartmentSubBill} from "../../../models/apartment-sub-bill";
import {ApartmentSubBillService} from "../../../services/apartment-sub-bill.service";
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
    selector: 'app-apartment-sub-bill-list',
    templateUrl: './apartment-sub-bill-list.component.html',
    styleUrls: ['./apartment-sub-bill-list.component.scss']
})
export class ApartmentSubBillListComponent implements OnInit {
    apartmentSubBills?: ApartmentSubBill[];
    currentApartmentSubBill?: ApartmentSubBill;
    currentIndex: number = -1;

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
                    alert(error);
                });
    }

    refreshList(): void {
        this.retrieveApartmentSubBills();
        this.currentApartmentSubBill = undefined;
        this.currentIndex = -1;
    }

    setActiveApartmentSubBill(apartmentSubBill: ApartmentSubBill, index: number): void {
        this.currentApartmentSubBill = apartmentSubBill;
        this.currentIndex = index;
    }

    redirectToApartmentTransferCreate(): void{
        this.router.navigate([`/apartment-sub-bill-transfer-create`])
    }

    redirectToApartmentPayment(): void{
        this.router.navigate([`/apartment-sub-bill-payment`])
    }

}