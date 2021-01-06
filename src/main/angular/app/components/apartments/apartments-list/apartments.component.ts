import {Apartment} from "../../../models/apartment";
import {ApartmentInfoService} from "../../../services/apartment-info.service";
import {Component, OnInit} from "@angular/core";
import {NavigationExtras, Router} from "@angular/router";


@Component({
    selector: 'app-apartments',
    templateUrl: './apartments.component.html',
    styleUrls: ['./apartments.component.css']
})
export class ApartmentsComponent implements OnInit {
    apartments?: Apartment[];

    constructor(private apartmentInfoService: ApartmentInfoService, private router: Router) {
    }

    getAllApartments() {
        this.apartmentInfoService.getAllApartments().subscribe(
            data => {
                this.apartments = data;
            }
        );
    }

    ngOnInit(): void {
        this.getAllApartments();
    }

    getAllApartmentsByFloor(floor: Number) {
        this.apartmentInfoService.getAllApartmentsByFloor(floor).subscribe(
            data => {
                this.apartments = data;
            }
        );
    }

    updateApartment(id: Number) {
        this.router.navigate(['apartments/update',id]);
    }
}
