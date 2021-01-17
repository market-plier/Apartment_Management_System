import {Component, OnInit} from "@angular/core";
import {ApartmentSubBill} from "../../../models/apartment-sub-bill";
import {Apartment} from "../../../models/apartment";
import {ApartmentSubBillService} from "../../../services/apartment-sub-bill.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
    selector: 'app-apartment-sub-bill-show',
    templateUrl: './apartment-sub-bill-show.component.html',
    styleUrls: ['./apartment-sub-bill-show.component.css']
})
export class ApartmentSubBillShowComponent implements OnInit {
    currentApartmentSubBill: ApartmentSubBill;

    constructor(private apartmentSubBillService: ApartmentSubBillService,
                private route: ActivatedRoute,
                private router: Router) {
    }

    ngOnInit(): void {
        this.getApartmentSubBill(this.route.snapshot.params.id);
    }

    getApartmentSubBill(id: number): void{
        this.apartmentSubBillService.getApartmentSubBill(id)
            .subscribe(
                data => {
                    this.currentApartmentSubBill = data;
                    console.log(data);
                },
                error => {
                    console.log(error);
                });
    }

    redirectToApartmentSubBillList(): void{
        this.router.navigate([`/apartment-sub-bills`])
    }

}