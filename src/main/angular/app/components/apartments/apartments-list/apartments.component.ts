import {Apartment} from "../../../models/apartment";
import {ApartmentInfoService} from "../../../services/apartment-info.service";
import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";


@Component({
    selector: 'app-apartments',
    templateUrl: './apartments.component.html',
    styleUrls: ['./apartments.component.css']
})
export class ApartmentsComponent implements OnInit {
    apartments?: Apartment[];
    searchValue?: Number;
    floors?;
    selectedFloor?: Number;


    constructor(private apartmentInfoService: ApartmentInfoService, private router: Router) {
    }

    getAllApartments() {
        this.apartmentInfoService.getAllApartments().subscribe(
            data => {
                this.apartments = data;
                this.floors = this.uniqueArray(this.apartments.map(item => item.floor));
            }
        );
    }

    uniqueArray(ar) {
        var j = {};

        ar.forEach(function (v) {
            j[v + '::' + typeof v] = v;
        });

        return Object.keys(j).map(function (v) {
            return j[v];
        });
    }

    ngOnInit(): void {
        this.getAllApartments();
    }

    getApartment() {
        if (this.searchValue != null) {
            this.apartmentInfoService.getApartmentByApartmentNumber(this.searchValue).subscribe(
                data => {
                    this.apartments = [];
                    this.apartments[0] = data;
                }
            );
        }
    }

    getAllApartmentsByFloor() {
        if (this.selectedFloor != null && this.selectedFloor>0) {
            this.apartmentInfoService.getAllApartmentsByFloor(this.selectedFloor).subscribe(
                data => {
                    this.apartments = data;
                }
            );
        }
        if (this.selectedFloor == -1) {
            this.getAllApartments();
        }
    }

    apartmentInfo(id: Number) {
        this.router.navigate(['/apartment', {id: id}]);
    }

    updateApartment(id: Number) {
        this.router.navigate(['apartments/update', id]);
    }
}
