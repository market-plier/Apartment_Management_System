import {Component, OnInit} from '@angular/core';
import {BackEndError} from "../../models/back-end-error";
import {HttpClient} from "@angular/common/http";
import {MatSnackBar, MatSnackBarConfig} from "@angular/material/snack-bar";


@Component({
    selector: 'app-job-trigger',
    templateUrl: './job-trigger.component.html',
    styleUrls: ['./job-trigger.component.css']
})
export class JobTriggerComponent implements OnInit {

    ngOnInit(): void {
    }

    private baseURL = 'http://localhost:8888/trigger';
    err: BackEndError | undefined;

    constructor(private httpClient: HttpClient, private _snackBar: MatSnackBar) {
    }

    debtPaymentTrigger() {
        this.httpClient.get(`${this.baseURL}/debtPaymentJobTrigger`).subscribe(
            data=> this.openSnackBar("Triggered","OK"));
    }


    debtNotificationTrigger() {
        this.httpClient.get(`${this.baseURL}/debtNotificationJobTrigger`).subscribe(
            data=> this.openSnackBar("Triggered","OK"));
    }

    openSnackBar(message: string, action: string) {
        const config = new MatSnackBarConfig();
        config.panelClass = ['snack-bar-error'];
        config.duration = 10000;
        this._snackBar.open(message, action, config
        );
    }
}
