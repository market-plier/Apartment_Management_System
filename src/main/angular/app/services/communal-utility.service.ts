import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {CommunalUtility} from "../models/communal-utility";
import {Observable, of, throwError} from "rxjs";
import {catchError} from "rxjs/operators";
import {MatSnackBar} from "@angular/material/snack-bar";

@Injectable({
    providedIn: 'root'
})
export class CommunalUtilityService {

    httpOptions = {
        headers: new HttpHeaders({'Content-Type': 'application/json'})
    };

    private utilitiesUrl = 'http://localhost:8888/communal-utilities';

    constructor(private _snackBar: MatSnackBar,
                private http: HttpClient) {
    }


    getCommunalUtilities(): Observable<CommunalUtility[]> {
        return this.http.get<CommunalUtility[]>(this.utilitiesUrl)
            .pipe(
                catchError((err) => {
                    console.log('error caught in service')
                    console.error(err);
                    this.openSnackBar(err.error.errors, 'OK')
                    return throwError(err);    //Rethrow it back to component
                }));
  }

  getCommunalUtility(id: number): Observable<CommunalUtility> {
    const url = `${this.utilitiesUrl}/${id}`;
      return this.http.get<CommunalUtility>(url).pipe(
          catchError((err) => {
              console.log('error caught in service')
              console.error(err);
              this.openSnackBar(err.error.errors, 'OK')
              return throwError(err);    //Rethrow it back to component
          }));
  }

  updateCommunalUtility(communalUtility: CommunalUtility): Observable<any> {
      return this.http.put(this.utilitiesUrl, communalUtility, this.httpOptions).pipe(
          catchError((err) => {
              console.log(err)
              console.error(err.message);
              this.openSnackBar(err.error.errors, 'OK')
              return throwError(err);    //Rethrow it back to component
          }));
  }

  addCommunalUtility(communalUtility: CommunalUtility): Observable<CommunalUtility> {
      return this.http.post<CommunalUtility>(this.utilitiesUrl, communalUtility, this.httpOptions).pipe(
          catchError((err) => {
              console.log('error caught in service')
              console.error(err);
              this.openSnackBar(err.error.errors, 'OK')
              return throwError(err);    //Rethrow it back to component
          })
      );
  }

    openSnackBar(message: string, action: string) {
        this._snackBar.open(message, action, {
            duration: 10000,
        });
    }

    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {

            this.openSnackBar('Can\'t ' + operation, 'OK')
            // Let the app keep running by returning an empty result.
            return of(result as T);
        };
    }
}
