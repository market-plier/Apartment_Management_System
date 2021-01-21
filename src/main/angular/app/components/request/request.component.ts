import {Component, OnInit} from '@angular/core';
import {ApartmentRequestToManager} from "../../models/apartment-request-to-manager";
import {RequestToManagerService} from "../../services/request-to-manager.service";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {TokenStorageService} from "../../services/token-storage.service";
import {Apartment} from "../../models/apartment";
import {ApartmentInfoService} from "../../services/apartment-info.service";
import {ThemePalette} from "@angular/material/core";
import {ProgressSpinnerMode} from "@angular/material/progress-spinner";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ManagerService} from "../../services/manager.service";
import {Account} from "../../models/account";

@Component({
    selector: 'app-request',
    templateUrl: './request.component.html',
    styleUrls: ['./request.component.scss']
})
export class RequestComponent implements OnInit {

    request: ApartmentRequestToManager = new ApartmentRequestToManager();
    apartment?: Apartment = new Apartment();
    isSent = false;
    color: ThemePalette = 'primary';
    mode: ProgressSpinnerMode = 'determinate';
    value = 0;
    manager: Account=new Account();
    formGroup: FormGroup = new FormBuilder().group({
        'requestText': ['', [Validators.required, Validators.minLength(1)]]
    });

    constructor(private service: RequestToManagerService, private apartmentService: ApartmentInfoService, private router: Router,
                private _snackBar: MatSnackBar, public tokenStorage: TokenStorageService,
                private managerService: ManagerService) {
        managerService.getManagerInfo().subscribe(
            data => this.manager = data)
    }

    ngOnInit(): void {
        this.apartmentService.getApartmentByAccountId(this.tokenStorage.getAccountId())
            .subscribe(
                data => {
                    this.apartment = data;
                }
            );
    }

    sendRequest() {
        if (this.formGroup.valid && this.request.text.trim().length > 0) {
            this.mode = "indeterminate";
            this.request.apartmentId = this.tokenStorage.getAccountId();
            this.service.sendRequest(this.request).subscribe(
                data => {
                    this.openSnackBar('Request has been sent', '');
                    this.isSent = true;
                    this.mode = "determinate";
                    this.value = 100;
                    this.request.text = ' ';
                });
        } else this.openSnackBar("Text can not be empty", "OK");
    }

    openSnackBar(message: string, action: string) {
        this._snackBar.open(message, action, {
            duration: 10000,
        });
    }
}
