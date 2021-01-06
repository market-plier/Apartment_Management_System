import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from "@angular/router";
import {Observable} from 'rxjs';
import {TokenStorageService} from "../token-storage.service";
import {AuthService} from "../auth.service";
import {map} from "rxjs/operators";


@Injectable()
export class ManagerGuard implements CanActivate {


    constructor(
        private tokenService: TokenStorageService,
        private router: Router,

    ){}

    canActivate(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
        if (this.tokenService.getRole() == 'MANAGER')
        {
            return true;
        }
        this.tokenService.signOut()
        this.router.navigate(['/login'], {
            queryParams: {
                loginAgain: true
            }
        });
    }

}








