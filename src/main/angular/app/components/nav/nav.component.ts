import {Component} from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {map, shareReplay} from 'rxjs/operators';
import {TokenStorageService} from "../../services/token-storage.service";
import {Router} from "@angular/router";
import {Observable} from "rxjs";

@Component({
    selector: 'app-nav',
    templateUrl: './nav.component.html',
    styleUrls: ['./nav.component.css']
})
export class NavComponent {

    isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
        .pipe(
            map(result => result.matches),
            shareReplay()
        );

    constructor(private breakpointObserver: BreakpointObserver,
                private tokenStorageService: TokenStorageService, private router: Router
    ) {
    }

    role?: string;
    isLoggedIn = false;
    username?: string;

    ngOnInit(): void {
        this.isLoggedIn = !!this.tokenStorageService.getToken();

        if (this.isLoggedIn) {
            const user = this.tokenStorageService.getUser();
            this.role = user.role;
            this.username = user.username;
        }

    }

    getIsLoggedIn() {
        this.ngOnInit();
        return this.isLoggedIn;
    }

    logout(): void {
        this.tokenStorageService.signOut();
        this.isLoggedIn=false;
        this.redirectPage();
    }

    redirectPage(): void {
        this.router.navigate(['/login/']);
    }

}
