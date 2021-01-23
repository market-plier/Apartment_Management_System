import {Apartment} from "../../../models/apartment";
import {ApartmentInfoService} from "../../../services/apartment-info.service";
import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {FormControl} from "@angular/forms";
import {Observable} from "rxjs";
import {map, startWith,} from "rxjs/operators";


@Component({
    selector: 'app-apartments',
    templateUrl: './apartments.component.html',
    styleUrls: ['./apartments.component.scss']
})
export class ApartmentsComponent implements OnInit {
    apartments?: Apartment[] = [];
    searchValue?: Number;
    floors?;
    selectedFloor?: Number;
    myControl = new FormControl();
    options: Number[] = [];
    filteredOptions: Observable<Number[]>;
    loading: boolean = false;

    constructor(private apartmentInfoService: ApartmentInfoService, private router: Router
        , private _snackBar: MatSnackBar) {
    }

    getAllApartments() {
        this.loading = true;
        this.apartmentInfoService.getAllApartments().subscribe(
            data => {
                this.apartments = data;
                this.floors = this.uniqueArray(this.apartments.map(item => item.floor));
                this.options = this.apartments.map(item => item.apartmentNumber);
                this.loading=false;
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
        this.filteredOptions = this.myControl.valueChanges
            .pipe(
                startWith(''),
                map(value => this._filter(value))
            );
    }

    private _filter(value: Number): Number[] {
        if (this.options != null && value != null) {
            return this.options.filter(option => option.toString().includes(value.toString()));
        }
    }


    getApartment() {
        this.loading=true;
        if (this.searchValue != null) {
            this.apartmentInfoService.getApartmentByApartmentNumber(this.searchValue).subscribe(
                data => {
                    this.apartments = [];
                    this.apartments[0] = data;
                    this.loading=false;
                },
                error => {
                    this.openSnackBar("No apartment was found", "Input another value");
                }
            );
        }
    }

    openSnackBar(message: string, action: string) {
        this._snackBar.open(message, action, {
            duration: 10000,
        });
    }

    getAllApartmentsByFloor() {
        this.loading=true;
        if (this.selectedFloor != null && this.selectedFloor > 0) {
            this.apartmentInfoService.getAllApartmentsByFloor(this.selectedFloor).subscribe(
                data => {
                    this.apartments = data;
                    this.loading=false;
                },
                error => {
                    this.openSnackBar("No apartments on this floor", "Choose another one");
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


}
