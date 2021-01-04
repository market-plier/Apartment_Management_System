import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from '@angular/material/list';
import {MatDividerModule} from '@angular/material/divider';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatExpansionModule} from "@angular/material/expansion";
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatRadioModule} from '@angular/material/radio';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatTableModule} from "@angular/material/table";
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {MatSelectModule} from "@angular/material/select";

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AnnouncementsListComponent} from './components/announcements/announcements-list/announcements-list.component';
import {AnnouncementsCreateComponent} from './components/announcements/announcements-create/announcements-create.component';
import {LoginComponent} from './components/login/login.component';

import {authInterceptorProviders} from './helpers/auth.interceptor';
import {JWT_OPTIONS, JwtHelperService} from "@auth0/angular-jwt";
import {FlexLayoutModule} from "@angular/flex-layout";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ManagerOperationListComponent } from './components/manager-operation/manager-operation-list/manager-operation-list.component';
import {ManagerOperationCreateComponent } from './components/manager-operation/manager-operation-create/manager-operation-create.component';
import {ManagerOperationUpdateComponent } from './components/manager-operation/manager-operation-update/manager-operation-update.component';
import {AnnouncementsShowComponent} from "./components/announcements/announcements-show/announcements-show.component";
import {AuthGuard} from "./services/auth.guard";

@NgModule({
    declarations: [
        AppComponent,
        AnnouncementsListComponent,
        AnnouncementsShowComponent,
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
        HttpClientModule,
        BrowserAnimationsModule,
        MatCardModule,
        MatButtonModule,
        MatIconModule,
        MatListModule,
        MatDividerModule,
        MatFormFieldModule,
        MatInputModule,
        MatExpansionModule,
        MatCheckboxModule,
        MatRadioModule,
        MatProgressBarModule,
        MatPaginatorModule,
        MatTableModule,
        ReactiveFormsModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatTableModule,
        MatPaginatorModule,
        MatIconModule,
        MatSelectModule
    ],
    providers: [authInterceptorProviders,{ provide: JWT_OPTIONS, useValue: JWT_OPTIONS },
        JwtHelperService, AuthGuard],
    bootstrap: [AppComponent]
})
export class AppModule {}
