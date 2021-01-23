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
    apartmentToSave?: Apartment;
    hide = true;
    loading: boolean = false;

    constructor(private service: ApartmentInfoService, private route: ActivatedRoute, private router: Router,
                private _snackBar: MatSnackBar, public tokenStorage: TokenStorageService) {
    }

    firstFormGroup: FormGroup = new FormBuilder().group({
        'emailCtrl': ['', [Validators.required, Validators.email]],
        'firstNameCtrl': ['', [Validators.required, Validators.pattern("^[а-яА-Яa-zA-Z]+(([',. -][а-яА-Яa-zA-Z ])?[а-яА-Яa-zA-Z]*)*$")]],
        'lastNameCtrl': ['', [Validators.required, Validators.pattern("^[а-яА-Яa-zA-Z]+(([',. -][а-яА-Яa-zA-Z ])?[а-яА-Яa-zA-Z]*)*$")]],
        'phoneNumberCtrl': ['', [Validators.required]],
        'passwordCtrl': ['', [Validators.minLength(8), Validators.maxLength(256)]],
        'peopleCountCtrl': ['', [Validators.required, Validators.min(0)]]
    });

    public mask = ['+', /[1-9]/, /\d/, /\d/, /\d/, /\d/, /\d/, /\d/, /\d/, /\d/, /\d/, /\d/, /\d/];

    updateApartment() {
        this.loading = true;
        this.apartmentToSave = Object.assign({}, this.apartment)
        this.service.updateApartment(this.apartmentToSave).subscribe(
            data => {
                this.openSnackBar('Apartment is updated', '');
                this.loading = false;
                this.goToApartmentsList();
            });
        if (this.apartment.password != null) {
            this.service.updatePassword(this.apartmentToSave);

        }

    }

    openSnackBar(message: string, action: string) {
        this._snackBar.open(message, action, {
            duration: 10000,
        });
    }

    goToApartmentsList() {
        this.router.navigate(['/apartments']);
    }


    ngOnInit(): void {
        this.loading=true;
        this.service.getApartmentByAccountId(this.route.snapshot.params['id']).subscribe(data => {
            this.apartment = data;
            this.loading=false;
        }, error => console.log(error));
    };

}
