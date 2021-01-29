// @ts-ignore
import {Injectable} from "@angular/core";
// @ts-ignore
import {HttpClient, HttpErrorResponse} from "@angular/common/http";

// @ts-ignore
import {Observable, throwError} from "rxjs";
import {Manager} from "../models/manager";
import {BackEndError} from "../models/back-end-error";
import {catchError} from "rxjs/operators";
import {sha256} from "js-sha256";
import {ManagerBill} from "../models/manager-bill";
import {MatSnackBar, MatSnackBarConfig} from "@angular/material/snack-bar";
import {environment} from "../../environments/environment.prod";


// @ts-ignore
@Injectable({
    providedIn: 'root'
})

export class ManagerService {

    private url = environment.url;
    private baseURL = this.url+ 'manager-info';
    err: BackEndError | undefined;

    constructor(private httpClient: HttpClient, private _snackBar: MatSnackBar) { }

    getManagerInfo(): Observable<Manager>
    {
        return this.httpClient.get<Manager>(`${this.baseURL}/manager`)
    }

    updateManager(manager : Manager) : Observable<Manager> {
        return this.httpClient.put(`${this.baseURL}/update-manager`, manager).pipe(
            catchError(this.handleError.bind(this)));
    }

    updateManagerPassword(manager : Manager) {
        manager.password = sha256(manager.password + "");
        return this.httpClient.put(`${this.baseURL}/update-manager-password`, manager).pipe(
            catchError(this.handleError.bind(this))
        ).subscribe(data => console.log(data))
    }

    updateManagerBill(managerBill : ManagerBill) {
        return this.httpClient.put(`${this.baseURL}/update-manager-bill`, managerBill).pipe(
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