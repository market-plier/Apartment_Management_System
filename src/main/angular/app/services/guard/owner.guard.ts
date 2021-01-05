import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from "@angular/router";
import {TokenStorageService} from "../token-storage.service";
import {Observable} from "rxjs";


@Injectable()
export class OwnerGuard implements CanActivate {
    constructor(
        private tokenService: TokenStorageService,
        private router: Router
    ) {
    }

    canActivate(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
        if (this.tokenService.getUser().role === "OWNER") {
            console.log(this.tokenService.isAuthenticated())
            return true
        } else {
            this.tokenService.signOut()
            this.router.navigate([''], {
                queryParams: {
                    noPermission: true
                }
            })
        }
    }
}