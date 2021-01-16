import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {CommunalUtility} from "../models/communal-utility";
import {Observable, of} from "rxjs";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class CommunalUtilityService {

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  private utilitiesUrl = 'http://localhost:8888/communal-utilities';
  private calcMethodUrl = 'http://localhost:8888/calculation-method'

  constructor(private http: HttpClient) {
  }


  getCommunalUtilities(): Observable<CommunalUtility[]> {
    return this.http.get<CommunalUtility[]>(this.utilitiesUrl)
        .pipe(
            catchError(this.handleError<CommunalUtility[]>('getUtilities', [])));
  }

  getCommunalUtility(id: number): Observable<CommunalUtility> {
    const url = `${this.utilitiesUrl}/${id}`;
    return this.http.get<CommunalUtility>(url).pipe(
        catchError(this.handleError<CommunalUtility>(`getCommunalUtility id=${id}`))
    );
  }

  updateCommunalUtility(communalUtility: CommunalUtility): Observable<any> {
    return this.http.put(this.utilitiesUrl, communalUtility, this.httpOptions).pipe(
        catchError(this.handleError<any>('updateUtility'))
    );
  }

  addCommunalUtility(communalUtility: CommunalUtility): Observable<CommunalUtility> {
    return this.http.post<CommunalUtility>(this.utilitiesUrl, communalUtility, this.httpOptions).pipe(
        catchError(this.handleError<CommunalUtility>('addUtility'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
