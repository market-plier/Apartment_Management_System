import {Injectable} from "@angular/core";
import {
    ActivatedRouteSnapshot,
    CanActivate,
    Router,
    RouterStateSnapshot,
    UrlTree
} from "@angular/router";
import {Observable} from "rxjs";
import {TokenStorageService} from "../token-storage.service";

@Injectable()
export class IsAuthGuard implements CanActivate {
    constructor(
        private tokenService: TokenStorageService,
        private router: Router,

    ) {}

    canActivate(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
        if (!this.tokenService.isAuthenticated()) {
            return true
        }
        this.router.navigate(['/home']);
    }





}