import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {Observable, Subject, Subscription, throwError} from 'rxjs';
import { sha256} from 'js-sha256';
import {catchError} from "rxjs/operators";
import {Account} from "../models/account";
import {environment} from "../../environments/environment";


const baseUrl=environment.url;
const AUTH_API = baseUrl+'/auth/';
const USER_API = baseUrl+'/auth/user/';


const httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    constructor(private http: HttpClient) {

    }


    public error$: Subject<string> = new Subject<string>()

    login(email: string, password: string): Observable<any> {

            password = sha256(password)
            return this.http.post(AUTH_API + 'login', {
                email,
                password
            }, httpOptions).pipe(
                catchError(this.handleError.bind(this))
            );
    }

   getAccount() : Observable<Account>{
       return  this.http.post<Account>(USER_API,httpOptions);
    }

    private handleError(error: HttpErrorResponse)
    {
        if (error.status === 401 || error.status === 403)
        {
            this.error$.next('Wrong password or email!')
        }

        return throwError(error)
    }
}
