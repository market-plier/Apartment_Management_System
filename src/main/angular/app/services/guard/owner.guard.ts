import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from "@angular/router";
import {TokenStorageService} from "../token-storage.service";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {AuthService} from "../auth.service";


@Injectable()
export class OwnerGuard implements CanActivate {
    constructor(
        private tokenService: TokenStorageService,
        private router: Router,
        private authService: AuthService
    ) {
    }

    canActivate(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot):Observable<boolean>|boolean {
        return this.authService.getAccount().pipe(map((auth) => {
            if (auth.role == "ROLE_OWNER")  {
                console.log(auth);
                return true;
            }
            console.log('not authenticated');
            this.tokenService.signOut();
            this.router.navigateByUrl('');
            return false;
        }));
    }
}