import { Injectable } from '@angular/core';
import {ManagerOperation} from "../models/manager-operation";
import {Observable, Subject, throwError} from "rxjs";
import {HttpClient, HttpErrorResponse, HttpParams} from "@angular/common/http";
import {catchError, map} from "rxjs/operators";
import {Announcement} from "../models/announcement";
import {ManagerSubBill} from "../models/manager-sub-bill";

@Injectable({
  providedIn: 'root'
})
export class ManagerOperationService {


  constructor(private http: HttpClient) { }
    private baseUrl = 'http://localhost:8888/manager-operation-spending/get-by-date/';
    private urlManagerOperation = 'http://localhost:8888/getAllManagerSubBills';
    private urlCreateManagerOperation = 'http://localhost:8888/manager-operation-spending/';
    private urlUpdateManagerOperation='http://localhost:8888/manager-operation-spending/';


    public error$: Subject<string> = new Subject<string>()

  getAllByDate(startDate: String, endDate: String): Observable<ManagerOperation[]> {

      // @ts-ignore
      let params = new HttpParams().set("start",startDate).set("end", endDate); //Create new HttpParams
        console.log(params);
      return this.http.get(this.baseUrl,{params:params})
        .pipe(map((response: {[key: string]: any}) => {
          return Object
              .keys(response)
              .map(key => ({
                ...response[key],
                createdAt: new Date(response[key].createdAt)
              }))
        }))
  }

  getAllManagerSubBill(): Observable<ManagerSubBill[]>
  {
      return this.http.get<ManagerSubBill[]>(this.urlManagerOperation);
  }

  makeSpending(managerSpending:ManagerOperation)
  {
      console.log(managerSpending);
      this.http.post<ManagerOperation>(this.urlCreateManagerOperation, managerSpending).subscribe(
          (res) => console.log(res),
          (err) => console.log(err)
      );
  }

  updateSpending(managerOperation:ManagerOperation)
  {

     return this.http.put<ManagerOperation>(this.urlUpdateManagerOperation,managerOperation).pipe(
          catchError(this.handleError.bind(this))
      ).subscribe(
          (res) => console.log(res),
          (err) => console.log(err)
      )
  }

    private handleError(error: HttpErrorResponse)
    {
        if (error.error.status == 'BAD_REQUEST')
        {
            this.error$.next('Wrong password or email!')
        }

        return throwError(error)
    }




}
