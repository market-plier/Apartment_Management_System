import {Injectable} from '@angular/core';
import {BackEndError} from "../models/back-end-error";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {ApartmentRequestToManager} from "../models/apartment-request-to-manager";
import {catchError} from "rxjs/operators";
import {MatSnackBar, MatSnackBarConfig} from "@angular/material/snack-bar";


@Injectable({
    providedIn: 'root'
})
export class RequestToManagerService {
    private baseURL = 'https://housemanagement.herokuapp.com/createApartmentRequestToManager';
    err: BackEndError | undefined;

    constructor(private httpClient: HttpClient,private _snackBar: MatSnackBar) {
    }


    sendRequest(request: ApartmentRequestToManager): Observable<Object> {
        return this.httpClient.post(this.baseURL, request).pipe(
            catchError(this.handleError.bind(this))
        );
    }


    handleError(error: HttpErrorResponse) {
        let err = new BackEndError();
        let errorMessage = '';
        err = error.error;

        // @ts-ignore
        errorMessage = errorMessage.concat(err.errors);

        this.openSnackBar(errorMessage, "OK");
    }

    openSnackBar(message: string, action: string) {
        const config = new MatSnackBarConfig();
        config.panelClass = ['snack-bar-error'];
        config.duration = 10000;
        this._snackBar.open(message, action, config
        );
    }
}
