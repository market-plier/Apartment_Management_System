import { Injectable } from '@angular/core';
import {BackEndError} from "../models/back-end-error";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {ApartmentRequestToManager} from "../models/apartment-request-to-manager";
import {catchError} from "rxjs/operators";


@Injectable({
  providedIn: 'root'
})
export class RequestToManagerService {
  private baseURL = 'http://localhost:8888/createApartmentRequestToManager';
  err: BackEndError | undefined;

  constructor(private httpClient: HttpClient) {
  }


  sendRequest(request:ApartmentRequestToManager): Observable<Object> {
    return this.httpClient.post(this.baseURL, request).pipe(
        catchError(this.handleError.bind(this))
    );
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
