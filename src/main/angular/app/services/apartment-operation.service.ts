import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {ManagerOperation} from "../models/manager-operation";
import {HttpClient, HttpParams} from "@angular/common/http";
import {map} from "rxjs/operators";
import {ApartmentOperation} from "../models/apartment-operation";
import {MatSnackBar} from "@angular/material/snack-bar";
import {CommunalUtility} from "../models/communal-utility";
import {environment} from "../../environments/environment.prod";


@Injectable({
    providedIn: 'root'
})
export class ApartmentOperationService {


    private url = environment.url;
    private getApartOperationURL = this.url + 'apartment-operation/';


    constructor(private httpClient: HttpClient, private _snackBar: MatSnackBar) {
    }

    getAllByAccountId(id:Number) : Observable<ApartmentOperation[]>{

        return this.httpClient.get<ApartmentOperation[]>(this.getApartOperationURL + id);

    }

}