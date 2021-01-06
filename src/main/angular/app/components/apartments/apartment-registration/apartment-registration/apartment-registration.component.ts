import {ApartmentInfoService} from "../../../../services/apartment-info.service";
import {Apartment} from "../../../../models/apartment";
import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
    selector: 'app-apartment-registration',
    templateUrl: './apartment-registration.component.html',
    styleUrls: ['./apartment-registration.component.css']
})
export class ApartmentRegistrationComponent implements OnInit {
    apartment: Apartment = {
        apartmentNumber: 1,
        squareMetres: 15,
        floor: 1,
        peopleCount: 0,
        accountId: 0,
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        phoneNumber: '',
        role: 'OWNER'
    };

    hide = true;
    isCreated = false;
    apartmentSave?: Apartment;

    firstFormGroup: FormGroup = new FormBuilder().group({
        'emailCtrl': ['', [Validators.required, Validators.email]],
        'firstNameCtrl': ['', [Validators.required, Validators.pattern("^[а-яА-Яa-zA-Z]+(([',. -][а-яА-Яa-zA-Z ])?[а-яА-Яa-zA-Z]*)*$")]],
        'lastNameCtrl': ['', [Validators.required, Validators.pattern("^[а-яА-Яa-zA-Z]+(([',. -][а-яА-Яa-zA-Z ])?[а-яА-Яa-zA-Z]*)*$")]],
        'phoneNumberCtrl': ['', [Validators.required]],
        'passwordCtrl': ['', [Validators.required, Validators.minLength(8), Validators.maxLength(256)]],
    });
    secondFormGroup: FormGroup = new FormBuilder().group({
        'apartmentNumberCtrl': ['', [Validators.required, Validators.min(1)]],
        'floorCtrl': ['', [Validators.required, Validators.min(1)]],
        'squareMetresCtrl': ['', [Validators.required, Validators.min(15)]],
        'peopleCountCtrl': ['', [Validators.required, Validators.min(0)]]
    });
    public mask = ['+', /[1-9]/, /\d/, /\d/, /\d/, /\d/, /\d/, /\d/, /\d/, /\d/, /\d/, /\d/, /\d/];


    constructor(private apartmentInfoService: ApartmentInfoService,
                private router: Router, private _snackBar: MatSnackBar) {
    }

    saveApartment() {
        this.apartmentSave = Object.assign({}, this.apartment)
        this.apartmentInfoService.createApartment(this.apartmentSave).subscribe(
            data => {
                this.openSnackBar('Apartment is created', 'OK');
                this.isCreated = true;
            },
            error => {
                console.log(error)
            }
        );
    }

    openSnackBar(message: string, action: string) {
        this._snackBar.open(message, action, {
            duration: 10000,
        });
        this._snackBar._openedSnackBarRef.onAction().subscribe(() => {
            this.goToApartmentsList();
        });
    }

    goToApartmentsList() {
        this.router.navigate(['/apartments']);
    }

    onSubmit() {
        this.saveApartment();
    }

    ngOnInit(): void {
    }

}
