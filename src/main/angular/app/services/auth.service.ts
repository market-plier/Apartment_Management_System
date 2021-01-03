import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import { sha256} from 'js-sha256';

const AUTH_API = 'http://localhost:8888/auth/';

const httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    constructor(private http: HttpClient) {
    }

    login(email: string, password: string): Observable<any> {

        if (password!=null && email!=null)
        {
            password = sha256(password)
            console.log(password)
            return this.http.post(AUTH_API + 'login', {
                email,
                password
            }, httpOptions);
        }
    }
}
