import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {TokenStorageService} from '../../services/token-storage.service';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Params, Router} from "@angular/router";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


    form:FormGroup;
    message:string;



    constructor(public authService: AuthService,
                private tokenStorage: TokenStorageService,
                private router: Router,
                private route:ActivatedRoute) {
    }

    ngOnInit(): void {

        this.route.queryParams.subscribe((params:Params)=>
        {
            if(params['loginAgain'])
            {
                this.message = 'Please, enter data (unauthorized)'
            }

        })


        this.form = new FormGroup({
            email: new FormControl('',[Validators.required, Validators.email]),
            password: new FormControl('',[Validators.required, Validators.minLength(6)])
        })
    }

    onSubmit(): void {


        this.authService.login(this.form.value.email, this.form.value.password, ).subscribe(

            data => {
                this.tokenStorage.saveToken(data.token);
                this.tokenStorage.saveUser(data);
                this.redirectPage()
            },
        );
    }

    redirectPage(): void {
        this.router.navigate(['/home']);
    }
}

