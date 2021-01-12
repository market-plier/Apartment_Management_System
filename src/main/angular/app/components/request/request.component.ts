import {Component, OnInit} from '@angular/core';
import {ApartmentRequestToManager} from "../../models/apartment-request-to-manager";
import {RequestToManagerService} from "../../services/request-to-manager.service";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {TokenStorageService} from "../../services/token-storage.service";
import {Apartment} from "../../models/apartment";
import {ApartmentInfoService} from "../../services/apartment-info.service";

@Component({
    selector: 'app-request',
    templateUrl: './request.component.html',
    styleUrls: ['./request.component.scss']
})
export class RequestComponent implements OnInit {

    request: ApartmentRequestToManager=new ApartmentRequestToManager();
    apartment?: Apartment=new Apartment();
    isSent = false;

    constructor(private service: RequestToManagerService, private apartmentService: ApartmentInfoService, private router: Router,
                private _snackBar: MatSnackBar, public tokenStorage: TokenStorageService) {
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
        this.request.apartmentId = this.tokenStorage.getAccountId();
            this.service.sendRequest(this.request).subscribe(
                data => {
                    this.openSnackBar('Request has been sent', '');
                    this.isSent = true;
                });

    }

    openSnackBar(message: string, action: string) {
        this._snackBar.open(message, action, {
            duration: 10000,
        });
    }
}
