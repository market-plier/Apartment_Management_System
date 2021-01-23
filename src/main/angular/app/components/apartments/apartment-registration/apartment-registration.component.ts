import {ApartmentInfoService} from "../../../services/apartment-info.service";
import {Apartment} from "../../../models/apartment";
import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
    selector: 'app-apartment-registration',
    templateUrl: './apartment-registration.component.html',
    styleUrls: ['./apartment-registration.component.scss']
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
    loading: boolean = false;

    firstFormGroup: FormGroup = new FormBuilder().group({
        'emailCtrl': ['', [Validators.required, Validators.pattern("^[0-9a-zA-Z]+([0-9a-zA-Z]*[-._+])*[0-9a-zA-Z]+@[0-9a-zA-Z]+([-.][0-9a-zA-Z]+)*([0-9a-zA-Z]*[.])[a-zA-Z]{2,6}$")]],
        'firstNameCtrl': ['', [Validators.required, Validators.pattern("^[а-яА-Яa-zA-Z]+(([',. -][а-яА-Яa-zA-Z ])?[а-яА-Яa-zA-Z]*)*$")]],
        'lastNameCtrl': ['', [Validators.required, Validators.pattern("^[а-яА-Яa-zA-Z]+(([',. -][а-яА-Яa-zA-Z ])?[а-яА-Яa-zA-Z]*)*$")]],
        'phoneNumberCtrl': ['', [Validators.required, Validators.pattern("\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|4[987654310]|3[9643210]|2[70]|7|1)\\d{1,13}$")]],
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
        this.loading=true;
        this.apartmentSave = Object.assign({}, this.apartment)
        this.apartmentInfoService.createApartment(this.apartmentSave).subscribe(
            data => {
                this.openSnackBar('Apartment is created', 'OK');
                this.isCreated = true;
                this.loading=false;
                this.goToApartmentsList();

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
