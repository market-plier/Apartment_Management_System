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


// @ts-ignore
@Injectable({
    providedIn: 'root'
})

export class ManagerService {

    private baseURL = 'http://localhost:8888/manager-info';
    err: BackEndError | undefined;

    constructor(private httpClient: HttpClient) { }

    getManagerInfo(): Observable<Manager>
    {
        return this.httpClient.get<Manager>(`${this.baseURL}/manager`)
    }

    updateManager(manager : Manager) : Observable<Manager> {
        return this.httpClient.post(`${this.baseURL}/update-manager`, manager).pipe(
            catchError(this.handleError.bind(this)));
    }

    updateManagerPassword(manager : Manager) {
        manager.password = sha256(manager.password + "");
        return this.httpClient.post(`${this.baseURL}/update-manager-password`, manager).pipe(
            catchError(this.handleError.bind(this))
        ).subscribe(data => console.log(data))
    }

    updateManagerBill(managerBill : ManagerBill) {
        return this.httpClient.post(`${this.baseURL}/update-manager-bill`, managerBill).pipe(
            catchError(this.handleError.bind(this))
        ).subscribe(data => console.log(data))
    }


    handleError(error: HttpErrorResponse) {
        let err = new BackEndError();
        let errorMessage = '';
        err = error.error;

        // @ts-ignore
        errorMessage = errorMessage.concat(err.errors);

        window.alert(errorMessage);
        return throwError(error);
    }
}