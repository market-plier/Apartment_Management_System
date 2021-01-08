import {Injectable} from '@angular/core';
import {JwtHelperService} from '@auth0/angular-jwt';
import {Router} from "@angular/router";

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Injectable({
    providedIn: 'root'
})
export class TokenStorageService {
    constructor( private jwtHelper: JwtHelperService) {
    }

    signOut(): void {
        window.sessionStorage.clear();
    }

    public saveToken(token: string): void {
        window.sessionStorage.removeItem(TOKEN_KEY);
        window.sessionStorage.setItem(TOKEN_KEY, token);
    }

    public getToken(): string | null {

        if (this.jwtHelper.isTokenExpired(window.sessionStorage.getItem(TOKEN_KEY))) {
            this.signOut()
            return null;
        }
        return window.sessionStorage.getItem(TOKEN_KEY);
    }


    public getRole(): string | null {
        if (window.sessionStorage.getItem(TOKEN_KEY)) {
            return this.jwtHelper.decodeToken(window.sessionStorage.getItem(TOKEN_KEY)).role;
        }
        return null;
    }

    public getEmail(): string | null {
        if (window.sessionStorage.getItem(TOKEN_KEY)) {
            return this.jwtHelper.decodeToken(window.sessionStorage.getItem(TOKEN_KEY)).sub;
        }
    }

    public getAccountId(): number | null {
        if (window.sessionStorage.getItem(TOKEN_KEY)) {
            return this.jwtHelper.decodeToken(window.sessionStorage.getItem(TOKEN_KEY)).accountId;
        }
        return null;
    }


    public saveUser(user: any): void {
        window.sessionStorage.removeItem(USER_KEY);
        window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
    }

    public getUser(): any {
        const user = window.sessionStorage.getItem(USER_KEY);
        if (user) {
            return JSON.parse(user);
        }

        return {};
    }
    isAuthenticated(): boolean {
        return !!this.getToken()
    }
}
