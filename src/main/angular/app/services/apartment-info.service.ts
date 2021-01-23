import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse,} from '@angular/common/http';
import {Observable, throwError} from "rxjs";
import {Apartment} from "../models/apartment";
import {BackEndError} from "../models/back-end-error";
import {sha256} from "js-sha256";
import {catchError} from "rxjs/operators";
import {MatSnackBar, MatSnackBarConfig} from "@angular/material/snack-bar";


@Injectable({
    providedIn: 'root'
})
export class ApartmentInfoService {

    private baseURL = 'https://housemanagement.herokuapp.com/apartments';
    err: BackEndError | undefined;

    constructor(private httpClient: HttpClient, private _snackBar: MatSnackBar) {
    }

    getApartmentByApartmentNumber(number: Number): Observable<Object> {
        return this.httpClient.get(`${this.baseURL}/${number}`);
    }

    getApartmentByAccountId(id: Number): Observable<Object> {
        return this.httpClient.get(`${this.baseURL}/apartment/?id=${id}`).pipe(
            catchError(this.handleError.bind(this))
        );
    }

    getAllApartments(): Observable<Apartment[]> {
        return this.httpClient.get<Apartment[]>(this.baseURL).pipe(
            catchError(this.handleError.bind(this))
        );
    }

    getAllApartmentsByFloor(floor: Number): Observable<Object[]> {
        return this.httpClient.get<Apartment[]>(`${this.baseURL}/apartments-on-floor?floor=${floor}`);
    }

    createApartment(apartment: Apartment): Observable<Object> {
        apartment.password = sha256(apartment.password + "");
        return this.httpClient.post(this.baseURL, apartment).pipe(
            catchError(this.handleError.bind(this))
        );
    }

    updateApartment(apartment: Apartment): Observable<Apartment> {
        return this.httpClient.put(this.baseURL, apartment).pipe(
            catchError(this.handleError.bind(this)));

    }

    updatePassword(apartment: Apartment) {
        apartment.password = sha256(apartment.password + "");
        return this.httpClient.put(`${this.baseURL}/updatePassword`, apartment).pipe(
            catchError(this.handleError.bind(this))
        ).subscribe(data => console.log(data))
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
