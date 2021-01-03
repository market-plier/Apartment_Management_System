import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {TokenStorageService} from '../../services/token-storage.service';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    form: any = {
        email: null,
        password: null
    };
    isLoggedIn = false;
    isLoginFailed = false;
    errorMessage = '';
    role: string = '';

    constructor(private authService: AuthService,
                private tokenStorage: TokenStorageService,
                private router: Router) {
    }

    ngOnInit(): void {
        if (this.tokenStorage.getToken()) {
            console.log(this.tokenStorage.getToken())
            this.isLoggedIn = true;
            this.role = this.tokenStorage.getUser().role;
        }
    }

    onSubmit(): void {
        const {email, password} = this.form;

        this.authService.login(email, password).subscribe(

            data => {

                this.tokenStorage.saveToken(data.token);
                this.tokenStorage.saveUser(data);

                this.isLoginFailed = false;
                this.isLoggedIn = true;
                this.role = this.tokenStorage.getUser().role;
                this.redirectPage()
            },
            err => {
                this.errorMessage = err.error.message;
                this.isLoginFailed = true;
            },


        );
    }

    redirectPage(): void {
        this.router.navigate(['/announcements']);
    }
}
