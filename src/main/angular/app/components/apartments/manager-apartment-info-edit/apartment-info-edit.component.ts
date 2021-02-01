import {Component, OnInit} from '@angular/core';
import {Apartment} from "../../../models/apartment";
import {ApartmentInfoService} from "../../../services/apartment-info.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";
import {TokenStorageService} from "../../../services/token-storage.service";

@Component({
    selector: 'app-manager-apartment-info-edit',
    templateUrl: './apartment-info-edit.component.html',
    styleUrls: ['./apartment-info-edit.component.scss']
})
export class ApartmentInfoEditComponent implements OnInit {

    apartmentToSave?: Apartment;
    apartment: Apartment = {
        apartmentNumber: 1,
        squareMetres: 15,
        floor: 1,
        peopleCount: 0,
        accountId: 0,
        firstName: '',
        lastName: '',
        email: '',
        password: null,
        phoneNumber: '',
        role: 'OWNER'
    };

    hide = true;
    loading: boolean = false;

    firstFormGroup: FormGroup = new FormBuilder().group({
        'firstNameCtrl': ['', [Validators.required, Validators.pattern("^[а-яА-Яa-zA-Z]+(([',. -][а-яА-Яa-zA-Z ])?[а-яА-Яa-zA-Z]*)*$")]],
        'lastNameCtrl': ['', [Validators.required, Validators.pattern("^[а-яА-Яa-zA-Z]+(([',. -][а-яА-Яa-zA-Z ])?[а-яА-Яa-zA-Z]*)*$")]],
        'phoneNumberCtrl': ['', [Validators.required]],
        'peopleCountCtrl': ['', [Validators.required, Validators.min(0)]]
    });

    secondFormGroup: FormGroup = new FormBuilder().group({
        'emailCtrl': ['', [Validators.required, Validators.email]],
        'passwordCtrl': ['', [Validators.minLength(8), Validators.maxLength(256)]]
    });

    public mask = ['+', /[1-9]/, /\d/, /\d/, /\d/, /\d/, /\d/, /\d/, /\d/, /\d/, /\d/, /\d/, /\d/];

    constructor(private service: ApartmentInfoService, private route: ActivatedRoute, private router: Router,
                private _snackBar: MatSnackBar, public tokenStorage: TokenStorageService) {
    }

    ngOnInit(): void {
        this.loading = true;

        this.service.getApartmentByAccountId(this.route.snapshot.params['id']).subscribe(
            data => {
                this.apartment = data;
                this.loading = false;
            },
            error => {
                this.loading = false;
                console.log(error);
            });

    };

    updateApartment() {
        if (this.secondFormGroup.valid &&
            (this.tokenStorage.getRole()=='MANAGER' && this.firstFormGroup.valid || this.tokenStorage.getRole()=='OWNER')){

            this.apartmentToSave = Object.assign({}, this.apartment)

            this.service.updateApartment(this.apartmentToSave).subscribe(
                data => {
                    this.openSnackBar('Apartment is updated', '');
                    this.goToApartmentsList();
                },
                error => {
                    console.log(error);
                });

            if (this.apartment.password != null) {
                this.service.updatePassword(this.apartmentToSave);
            }
        }
    }

    goToApartmentsList() {
        this.router.navigate(['/apartments']);
    }

    openSnackBar(message: string, action: string) {
        this._snackBar.open(message, action, {
            duration: 10000,
        });
    }
}
