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
import {ApartmentSubBillListComponent} from "./components/apartment-sub-bills/apartment-sub-bill-list/apartment-sub-bill-list.component";
import {ApartmentSubBillShowComponent} from "./components/apartment-sub-bills/apartment-sub-bill-show/apartment-sub-bill-show.component";
import {ApartmentInfoPageComponent} from './components/apartments/apartment-info-page/apartment-info-page.component';
import {CommunalUtilitiesShowComponent} from "./components/communal-utilities/communal-utilities-show/communal-utilities-show.component";
import {CommunalUtilitiesCreateComponent} from "./components/communal-utilities/communal-utilities-create/communal-utilities-create.component";
import {CommunalUtilitiesListComponent} from "./components/communal-utilities/communal-utilities-list/communal-utilities-list.component";
import {ApartmentSubBillTransferCreateComponent} from "./components/apartment-sub-bills/apartment-sub-bill-transfer-create/apartment-sub-bill-transfer-create.component";
import {ApartmentSubBillPaymentComponent} from "./components/apartment-sub-bills/apartment-sub-bill-payment/apartment-sub-bill-payment.component";
import {IsAuthGuard} from "./services/guard/isauth.guard";
import {ReportCreateComponent} from './components/report-create/report-create.component';
import {DatePipe} from "@angular/common";
import {RequestComponent} from './components/request/request.component';
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {ManagerInfoPageComponent} from "./components/manager-info-page/manager-info-page.component";
import {JobTriggerComponent} from './components/job-trigger/job-trigger.component';
import {AnnouncementDashboardComponent} from './components/announcements/announcement-dashboard/announcement-dashboard.component';
import {ManagerOperationUpdateComponent} from "./components/manager-operation/manager-operation-update/manager-operation-update.component";
import {ApartmentSubBillDashboardComponent} from "./components/apartment-sub-bills/apartment-sub-bill-dashboard/apartment-sub-bill-dashboard.component";
import {NgxChartsModule} from "@swimlane/ngx-charts";
import {ManagerInfoUpdateComponent} from "./components/manager/manager-info-update.component";

import {DashboardManagerBillDebtComponent} from "./components/dashboard-manager-bill-debt/dashboard-manager-bill-debt.component";
import {ManagerSubBillsDashboardComponent} from './components/manager-sub-bills/manager-sub-bills-dashboard/manager-sub-bills-dashboard.component';
import {CarouselModule} from "ngx-bootstrap/carousel";
import {ApartmentOperationListComponent} from './components/apartment-operation-list/apartment-operation-list.component';
import {ApartmentDebtComponent} from "./components/apartment-sub-bills/apartment-sub-bill-dashboard/apartment-debt/apartment-debt.component";
import {MatSortModule} from "@angular/material/sort";
import {ManagerSubBillListComponent} from "./components/manager-sub-bills/manager-sub-bills-list/manager-sub-bills-list.component";

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
        ApartmentOperationListComponent,
        CommentEditDialog,
        LoginComponent,
        ManagerOperationListComponent,
        ManagerOperationCreateComponent,
        ManagerInfoPageComponent,
        ApartmentsComponent,
        ApartmentRegistrationComponent,
        ApartmentInfoEditComponent,
        NavComponent,
        HomeComponent,
        NotFoundComponent,
        ReportCreateComponent,
        ApartmentInfoPageComponent,
        NotFoundComponent,
        ApartmentSubBillListComponent,
        ApartmentSubBillShowComponent,
        ApartmentSubBillTransferCreateComponent,
        ApartmentSubBillPaymentComponent,
        ApartmentInfoPageComponent,
        ApartmentInfoPageComponent,
        RequestComponent,
        JobTriggerComponent,
        AnnouncementDashboardComponent,
        ManagerOperationUpdateComponent,
        ApartmentSubBillDashboardComponent,
        ManagerInfoUpdateComponent,
        ApartmentDebtComponent,
        ManagerSubBillListComponent,
        DashboardManagerBillDebtComponent,
        ManagerSubBillsDashboardComponent
    ],
    imports: [
        MatProgressSpinnerModule,
        MatAutocompleteModule,
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        FlexLayoutModule,
        MatCardModule,
        HttpClientModule,
        FormsModule,
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
        MatBadgeModule,
        MatMenuModule,
        MatBadgeModule,
        NgxChartsModule,
        CarouselModule,
        MatSortModule
    ],
    providers: [authInterceptorProviders,{ provide: JWT_OPTIONS, useValue: JWT_OPTIONS },
        JwtHelperService, AuthGuard,OwnerGuard,ManagerGuard,IsAuthGuard,DatePipe],
    bootstrap: [AppComponent]
})
export class AppModule {}
