import { Injectable } from '@angular/core';
import {BackEndError} from "../models/back-end-error";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";
import {ManagerSubBill} from "../models/manager-sub-bill";
import {ApartmentSubBill} from "../models/apartment-sub-bill";

@Injectable({
  providedIn: 'root'
})
export class ManagerSubBillService {
  private baseURL = 'http://localhost:8888/manager-sub-bill';
  private urlGetManagerSubBillsDebt = 'http://localhost:8888/manager-sub-bill/get-manager-sub-bill-debt';
  err: BackEndError | undefined;

  constructor(private httpClient: HttpClient) {
  }

  getManagerSubBillByCommunalUtilityId(communalUtilityId: Number): Observable<Object> {
    return this.httpClient.get(`${this.baseURL}/get-by-communal/?communalUtilityId=${communalUtilityId}`);
  }

  getManagerSubBill(managerSubBillId: Number): Observable<ManagerSubBill> {
    return this.httpClient.get(`${this.baseURL}/get/${managerSubBillId}`).pipe(
        catchError(this.handleError.bind(this))
    );
  }

  getManagerSubBillsDebt()
  {
    return this.httpClient.get(this.urlGetManagerSubBillsDebt);
  }


  getAllManagerSubBills(): Observable<ManagerSubBill[]> {
    return this.httpClient.get<ManagerSubBill[]>(`${this.baseURL}/get`).pipe(
        catchError(this.handleError.bind(this))
    );
  }

  getManagerSubBillByCommunalName(id: String): Observable<ManagerSubBill>{
    return this.httpClient.get<ManagerSubBill>(`${this.baseURL}/get-by-communal-name/${id}`);
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
