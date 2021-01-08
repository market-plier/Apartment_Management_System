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
import {MatNativeDateModule, MatRippleModule} from '@angular/material/core';
import {MatSelectModule} from "@angular/material/select";
import {MatButtonToggleModule} from '@angular/material/button-toggle';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';

import {AnnouncementsListComponent} from './components/announcements/announcements-list/announcements-list.component';
import {AnnouncementsCreateComponent} from './components/announcements/announcements-create/announcements-create.component';
// @ts-ignore
import {
    AnnouncementsShowComponent,
    CommentEditDialog
} from "./components/announcements/announcements-show/announcements-show.component";
import {AnnouncementsUpdateComponent} from "./components/announcements/announcements-update/announcements-update.component";
import {MatBadgeModule} from '@angular/material/badge';
import {LoginComponent} from './components/login/login.component';
import {authInterceptorProviders} from './helpers/auth.interceptor';
import {JWT_OPTIONS, JwtHelperService} from "@auth0/angular-jwt";
import {FlexLayoutModule} from "@angular/flex-layout";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ManagerOperationListComponent} from './components/manager-operation/manager-operation-list/manager-operation-list.component';
import {ManagerOperationCreateComponent} from './components/manager-operation/manager-operation-create/manager-operation-create.component';
import {ManagerOperationUpdateComponent} from './components/manager-operation/manager-operation-update/manager-operation-update.component';
import {AuthGuard} from "./services/guard/auth.guard";
import {OwnerGuard} from "./services/guard/owner.guard";
import {ManagerGuard} from "./services/guard/manager.guard";
import {MatDialogModule} from "@angular/material/dialog";
import {ApartmentsComponent} from "./components/apartments/apartments-list/apartments.component";
import {ApartmentRegistrationComponent} from "./components/apartments/apartment-registration/apartment-registration.component";
import {ApartmentInfoEditComponent} from "./components/apartments/manager-apartment-info-edit/apartment-info-edit.component";
import {NavComponent} from "./components/nav/nav.component";
import {HomeComponent} from "./components/home/home.component";
import {NotFoundComponent} from "./components/not-found/not-found.component";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatStepperModule} from "@angular/material/stepper";
import {MatMenuModule} from "@angular/material/menu";
import {MatGridListModule} from "@angular/material/grid-list";
import {LayoutModule} from "@angular/cdk/layout";
import {TextMaskModule} from "angular2-text-mask";
import {ApartmentInfoPageComponent} from './components/apartments/apartment-info-page/apartment-info-page.component';
import {CommunalUtilitiesShowComponent} from "./components/communal-utilities/communal-utilities-show/communal-utilities-show.component";
import {CommunalUtilitiesCreateComponent} from "./components/communal-utilities/communal-utilities-create/communal-utilities-create.component";
import {CommunalUtilitiesListComponent} from "./components/communal-utilities/communal-utilities-list/communal-utilities-list.component";
import {IsAuthGuard} from "./services/guard/isauth.guard";
import {ReportCreateComponent} from './components/report-create/report-create.component';


@NgModule({
    declarations: [
        AppComponent,
        CommunalUtilitiesShowComponent,
        CommunalUtilitiesCreateComponent,
        CommunalUtilitiesListComponent,
        AnnouncementsListComponent,
        AnnouncementsShowComponent,
        AnnouncementsCreateComponent,
        AnnouncementsUpdateComponent,
        CommentEditDialog,
        LoginComponent,
        ManagerOperationListComponent,
        ManagerOperationCreateComponent,
        ManagerOperationUpdateComponent,
        ApartmentsComponent,
        ApartmentRegistrationComponent,
        ApartmentInfoEditComponent,
        NavComponent,
        HomeComponent,
        NotFoundComponent,
        ReportCreateComponent,
        ApartmentInfoPageComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        FlexLayoutModule,
        MatCardModule,
        HttpClientModule,
        FormsModule,
        BrowserAnimationsModule,
        MatButtonModule,
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
        MatIconModule,
        MatSelectModule,
        MatButtonToggleModule,
        MatDialogModule,
        MatSelectModule,
        MatSnackBarModule,
        MatToolbarModule,
        MatSidenavModule,
        MatStepperModule,
        TextMaskModule,
        LayoutModule,
        MatGridListModule,
        MatMenuModule,
        MatRippleModule,
        MatMenuModule,
        MatBadgeModule
    ],
    providers: [authInterceptorProviders,{ provide: JWT_OPTIONS, useValue: JWT_OPTIONS },
        JwtHelperService, AuthGuard,OwnerGuard,ManagerGuard,IsAuthGuard],
    bootstrap: [AppComponent]
})
export class AppModule {}
