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
    isOpen = false;
    username?: string;

    ngOnInit(): void {
        this.isLoggedIn = this.tokenStorageService.isAuthenticated();

        if (this.isLoggedIn) {
            const user = this.tokenStorageService.getUser();
            this.role = user.role;
            this.username = user.username;
        }

    }

    getIsLoggedIn() {
        this.ngOnInit();
        if (!this.isLoggedIn && this.router.url !== '/login') {
            this.tokenStorageService.signOut()
            this.router.navigate(['/login'])
        }
        return this.isLoggedIn;
    }

    logout(): void {
        this.tokenStorageService.signOut();
        this.isLoggedIn = false;
        this.isOpen = false;
        window.location.reload();
        this.redirectPage();
    }

    redirectPage(): void {
        this.router.navigate(['/login/']);
    }

    editAccountPage(){
        if (this.role == 'MANAGER') {
            this.router.navigate(['manager-info/update', {id: this.tokenStorageService.getAccountId()}]);
        }
        if (this.role == 'OWNER') {
            this.router.navigate(['apartments/update/:id', {id: this.tokenStorageService.getAccountId()}]);
        }
    }

    goToAccountPage() {
        if (this.role == 'MANAGER') {
            this.router.navigate(['/manager-info']);
        }
        if (this.role == 'OWNER') {
            this.router.navigate(['/apartment', {id: this.tokenStorageService.getAccountId()}]);
        }
    }

}
