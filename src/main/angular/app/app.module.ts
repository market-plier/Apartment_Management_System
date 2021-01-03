import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {MatTableModule} from '@angular/material/table';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';

import {AnnouncementsListComponent} from './components/announcements/announcements-list/announcements-list.component';

import {AnnouncementsCreateComponent} from './components/announcements/announcements-create/announcements-create.component';

import {LoginComponent} from './components/login/login.component';

import {MatIconModule} from '@angular/material/icon';
import {authInterceptorProviders} from './helpers/auth.interceptor';
import {JWT_OPTIONS, JwtHelperService} from "@auth0/angular-jwt";
import {FlexLayoutModule} from "@angular/flex-layout";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {MatFormFieldModule} from "@angular/material/form-field";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatCardModule} from "@angular/material/card";
import { ManagerOperationListComponent } from './components/manager-operation/manager-operation-list/manager-operation-list.component';
import { ManagerOperationCreateComponent } from './components/manager-operation/manager-operation-create/manager-operation-create.component';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {MatPaginatorModule} from "@angular/material/paginator";
import { ManagerOperationUpdateComponent } from './components/manager-operation/manager-operation-update/manager-operation-update.component';
import {MatSelectModule} from "@angular/material/select";
@NgModule({
    declarations: [
        AppComponent,
        AnnouncementsListComponent,
        AnnouncementsCreateComponent,
        LoginComponent,
        ManagerOperationListComponent,
        ManagerOperationCreateComponent,
        ManagerOperationUpdateComponent,
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        FlexLayoutModule,
        MatFormFieldModule,
        MatInputModule,
        MatButtonModule,
        MatCardModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatTableModule,
        MatPaginatorModule,
        MatIconModule,
        MatSelectModule
    ],
    providers: [authInterceptorProviders,{ provide: JWT_OPTIONS, useValue: JWT_OPTIONS },
        JwtHelperService,],
    bootstrap: [AppComponent]
})
export class AppModule {
}
