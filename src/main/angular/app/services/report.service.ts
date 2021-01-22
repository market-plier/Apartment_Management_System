import {Injectable} from '@angular/core';
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {MatSnackBar} from "@angular/material/snack-bar";

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


    constructor(private http: HttpClient,
                private _snackBar: MatSnackBar) {
    }

    makeManagerSpendingByDateCommName(start: string, end: string, communalUtility: string): Observable<Blob> {
        return this.http.get(this.managerSpendingByDateCommName, {
            responseType: 'blob', params: {
                start,
                end,
                communalUtility
            }
        }).pipe(
            catchError((err) => {
                console.log(err)
                console.error(err.message);
                this.openSnackBar('Data not found', 'OK')
                return throwError(err);    //Rethrow it back to component
            })
        );
    }

    openSnackBar(message: string, action: string) {
        this._snackBar.open(message, action, {
            duration: 10000,
        });
    }

    makeManagerSpendingByDate(start: string, end: string): Observable<Blob> {
        return this.http.get(this.managerSpendingByDate, {
            responseType: 'blob', params: {
                start,
                end
            }
        }).pipe(
            catchError((err) => {
                console.log(err)
                console.error(err.message);
                this.openSnackBar('Data not found', 'OK')
                return throwError(err);    //Rethrow it back to component
            })
        );
    }

  makeDeptManagerBill(communalUtility: string): Observable<Blob> {
      return this.http.get(this.managerDeptManagerBill, {
          responseType: 'blob', params: {
              communalUtility
          }
      }).pipe(
          catchError((err) => {
              console.log(err)
              console.error(err.message);
              this.openSnackBar('Data not found', 'OK')
              return throwError(err);    //Rethrow it back to component
          })
      );
  }

  makeApartmentDebtReport(accountId: string, communalUtility: string): Observable<Blob> {
      return this.http.get(this.apartmentDeptReport, {
          responseType: 'blob', params: {
              communalUtility,
              accountId
          }
      }).pipe(
          catchError((err) => {
              console.log(err)
              console.error(err.message);
              this.openSnackBar('Data not found', 'OK')
              return throwError(err);    //Rethrow it back to component
          })
      );
  }
}
