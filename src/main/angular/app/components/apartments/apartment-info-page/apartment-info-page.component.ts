import {Component, OnInit} from '@angular/core';
import {Apartment} from "../../../models/apartment";
import {ApartmentInfoService} from "../../../services/apartment-info.service";
import {TokenStorageService} from "../../../services/token-storage.service";
import {ActivatedRoute} from "@angular/router";


@Component({
    selector: 'app-apartment-info-page',
    templateUrl: './apartment-info-page.component.html',
    styleUrls: ['./apartment-info-page.component.css']
})
export class ApartmentInfoPageComponent implements OnInit {

    apartment: Apartment = new Apartment();

    constructor(private service: ApartmentInfoService, private token: TokenStorageService,
                private route: ActivatedRoute) {
    }

    ngOnInit(): void {
            const accountId = this.route.snapshot.paramMap.get('id');
            this.service.getApartmentByAccountId(Number(accountId))
                .subscribe(data => this.apartment = data)
    }
}
