import { Injectable } from '@angular/core';
import {ManagerOperation} from "../models/manager-operation";
import {Observable, Subject, throwError} from "rxjs";
import {HttpClient, HttpErrorResponse, HttpParams} from "@angular/common/http";
import {catchError, map} from "rxjs/operators";
import {ManagerSubBill} from "../models/manager-sub-bill";
import {CommunalUtility} from "../models/communal-utility";

@Injectable({
  providedIn: 'root'
})
export class ManagerOperationService {


  constructor(private http: HttpClient) { }
    private baseUrl = 'http://localhost:8888/manager-operation-spending/get-by-date/';
    private urlManagerOperation = 'http://localhost:8888/getAllManagerSubBills';
    private urlCreateManagerOperation = 'http://localhost:8888/manager-operation-spending/';
    private urlUpdateManagerOperation='http://localhost:8888/manager-operation-spending/';
    private urlFilterByDateAndCommunalUtility='http://localhost:8888/manager-operation-spending/get-by-date-comm-util/';
    private urlFilterByCommunalUtility='http://localhost:8888/manager-operation-spending/get-by-comm-util/';
    private urlGetAllCommUtility='http://localhost:8888/communal_utilities/comm-util/';


    public error$: Subject<string> = new Subject<string>()
    public success$: Subject<string> = new Subject<string>()

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

  getAllCommunalUtility(): Observable<CommunalUtility[]>
  {
      return this.http.get<CommunalUtility[]>(this.urlGetAllCommUtility);
  }

  makeSpending(managerSpending:ManagerOperation)
  {
      console.log(managerSpending);
      this.http.post<ManagerOperation>(this.urlCreateManagerOperation, managerSpending).pipe(
          catchError(this.handleErrorBalance.bind(this)),
      );
  }

  updateSpending(managerOperation:ManagerOperation)
  {

     return this.http.put<ManagerOperation>(this.urlUpdateManagerOperation,managerOperation).pipe(
          catchError(this.handleErrorBalance.bind(this))
      ).subscribe(

          (res) => {
              console.log(res)
          },
          (err) => console.log(err)
      )
  }


  handleSuccess(result)
  {
      console.log(result)
      if (result.statusCode == 200)
      {
          this.success$.next("Manager spending was added")
      }
  }



    private handleErrorBalance(error: HttpErrorResponse)
    {
        console.log(error.error.errorCode)
        if (error.error.errorCode == 8092)
        {
            this.error$.next('Not enough money')
        }

        return throwError(error)
    }

    filterByDateAndCommunalUtility(communalUtility:CommunalUtility[],start,end)
    {

        let params = this.getParams(communalUtility).set("start",start).set("end", end);
        console.log(params)
        return this.http.get(this.urlFilterByDateAndCommunalUtility,{params:params})
            .pipe(map((response: {[key: string]: any}) => {
                return Object
                    .keys(response)
                    .map(key => ({
                        ...response[key],
                        createdAt: new Date(response[key].createdAt)
                    }))
            }))
    }

    filterByCommunalUtility(communalUtility:CommunalUtility[])
    {
        let params = this.getParams(communalUtility);
        console.log(params)
        return this.http.get(this.urlFilterByCommunalUtility,{params:params})
            .pipe(map((response: {[key: string]: any}) => {
                return Object
                    .keys(response)
                    .map(key => ({
                        ...response[key],
                        createdAt: new Date(response[key].createdAt)
                    }))
            }))
    }

    private getParams(query) {
        let params: HttpParams = new HttpParams();
        for (const key of Object.keys(query)) {
            if (query[key]) {
                if (query[key] instanceof Array) {
                    query[key].forEach((item) => {
                        params = params.append(`${key.toString()}`, item);
                    });
                } else {
                    params = params.append(key.toString(), query[key]);
                }
            }
        }
        return params;
    }


}
