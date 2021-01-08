import {Injectable} from '@angular/core';
import {Observable, of} from "rxjs";
import {catchError} from "rxjs/operators";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/pdf',
      responseType: 'blob',
      Accept: 'application/pdf',
      observe: 'response'
    })
  };

  private managerSpendingByDateCommName = 'http://localhost:8888/make-report/manager-spending/by-date-comm-name';
  private managerSpendingByDate = 'http://localhost:8888/make-report/manager-spending/by-date';
  private apartmentDeptReport = 'http://localhost:8888/make-report/apartment/dept-report';
  private managerDeptManagerBill = 'http://localhost:8888/make-report/manager/dept-manager-bill';


  constructor(private http: HttpClient) {
  }

  makeManagerSpendingByDateCommName(start: string, end: string, communalUtility: string): Observable<Blob> {
    return this.http.get(this.managerSpendingByDateCommName, {
      responseType: 'blob', params: {
        start,
        end,
        communalUtility
      }
    }).pipe(
        catchError(this.handleError<Blob>('getPdf', null)));
  }

  makeManagerSpendingByDate(start: string, end: string): Observable<Blob> {
    return this.http.get(this.managerSpendingByDate, {
      responseType: 'blob', params: {
        start,
        end
      }
    }).pipe(
        catchError(this.handleError<Blob>('getPdf', null)));
  }

  makeDeptManagerBill(communalUtility: string): Observable<Blob> {
    return this.http.get(this.managerDeptManagerBill, {
      responseType: 'blob', params: {
        communalUtility
      }
    }).pipe(
        catchError(this.handleError<Blob>('getPdf', null)));
  }

  makeApartmentDebtReport(accountId: string, communalUtility: string): Observable<Blob> {
    return this.http.get(this.apartmentDeptReport, {
      responseType: 'blob', params: {
        communalUtility,
        accountId
      }
    }).pipe(
        catchError(this.handleError<Blob>('getPdf', null)));
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error); // log to console instead

      return of(result as T);
    };
  }
}
