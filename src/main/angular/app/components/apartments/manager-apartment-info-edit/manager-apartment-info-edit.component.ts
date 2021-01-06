import {Component, OnInit} from '@angular/core';
import {Apartment} from "../../../models/apartment";
import {ApartmentInfoService} from "../../../services/apartment-info.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";


@Component({
    selector: 'app-manager-apartment-info-edit',
    templateUrl: './manager-apartment-info-edit.component.html',
    styleUrls: ['./manager-apartment-info-edit.component.css']
})
export class ManagerApartmentInfoEditComponent implements OnInit {
    apartment: Apartment = new Apartment();
    hide = true;

    constructor(private service: ApartmentInfoService, private route: ActivatedRoute, private router: Router,
         private _snackBar: MatSnackBar) {
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
        this.service.updateApartment(this.apartment);
        if (this.apartment.password!=null) {
            this.service.updatePassword(this.apartment);
        }
        this.openSnackBar('Apartment updated', 'OK');
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


    ngOnInit(): void {
        this.service.getApartmentByApartmentNumber(this.route.snapshot.params['number']).subscribe(data => {
            this.apartment = data;
        }, error => console.log(error));
    };

}
