import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {ManagerOperation} from "../models/manager-operation";
import {HttpClient, HttpParams} from "@angular/common/http";
import {map} from "rxjs/operators";
import {ApartmentOperation} from "../models/apartment-operation";
import {MatSnackBar} from "@angular/material/snack-bar";
import {CommunalUtility} from "../models/communal-utility";
import {environment} from "../../environments/environment.prod";
import {A} from "@angular/cdk/keycodes";


@Injectable({
    providedIn: 'root'
})
export class ApartmentOperationService {


    private url = environment.url;
    private getApartOperationURL = this.url + 'apartment-operation/';
    private getApartmentOperationByApartmentNumberAndDateRangeURL = this.url + 'apartment-operation/by-date-and-apart-number/';
    private getApartmentOperationsByApartmentSubBillId = this.url + 'apartment-sub-bill/';


    constructor(private httpClient: HttpClient, private _snackBar: MatSnackBar) {
    }

    getAllByAccountId(id:Number) : Observable<ApartmentOperation[]>{

        return this.httpClient.get<ApartmentOperation[]>(this.getApartOperationURL + id);

    }

    getAllByAccountNumberAndDateRange(number,start,end)
    {
        let params: HttpParams = new HttpParams().set('number',number).set("start",start).set("end", end);
        return this.httpClient.get<ApartmentOperation[]>(this.getApartmentOperationByApartmentNumberAndDateRangeURL,{params:params});


    }

    getApartmentOperationsByApartmentSubBill(id: number): Observable<ApartmentOperation[]> {

        return this.httpClient.get<ApartmentOperation[]>(`${this.getApartmentOperationsByApartmentSubBillId}${id}`);
    }
}