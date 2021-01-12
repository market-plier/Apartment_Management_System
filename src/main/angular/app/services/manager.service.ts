import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

import {Observable} from "rxjs";
import {Manager} from "../models/manager";


@Injectable({
    providedIn: 'root'
})

export class ManagerService {

    private urlGetManager = 'http://localhost:8888/manager-info/manager';

    constructor(private http: HttpClient) { }


    getManagerInfo(): Observable<Manager>
    {
        return this.http.get<Manager>(this.urlGetManager)
    }


}