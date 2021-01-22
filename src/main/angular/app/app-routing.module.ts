import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AnnouncementsListComponent} from "./components/announcements/announcements-list/announcements-list.component";
import {AnnouncementsCreateComponent} from "./components/announcements/announcements-create/announcements-create.component";
import {LoginComponent} from "./components/login/login.component";
import {AnnouncementsShowComponent} from "./components/announcements/announcements-show/announcements-show.component";
import {ManagerOperationListComponent} from "./components/manager-operation/manager-operation-list/manager-operation-list.component";
import {AuthGuard} from "./services/guard/auth.guard";
import {AnnouncementsUpdateComponent} from "./components/announcements/announcements-update/announcements-update.component";
import {ManagerGuard} from "./services/guard/manager.guard";
import {ApartmentInfoEditComponent} from "./components/apartments/manager-apartment-info-edit/apartment-info-edit.component";
import {ApartmentRegistrationComponent} from "./components/apartments/apartment-registration/apartment-registration.component";
import {ApartmentsComponent} from "./components/apartments/apartments-list/apartments.component";
import {HomeComponent} from "./components/home/home.component";
import {NotFoundComponent} from "./components/not-found/not-found.component";
import {ApartmentInfoPageComponent} from "./components/apartments/apartment-info-page/apartment-info-page.component";
import {CommunalUtilitiesCreateComponent} from "./components/communal-utilities/communal-utilities-create/communal-utilities-create.component";
import {CommunalUtilitiesListComponent} from "./components/communal-utilities/communal-utilities-list/communal-utilities-list.component";
import {CommunalUtilitiesShowComponent} from "./components/communal-utilities/communal-utilities-show/communal-utilities-show.component";
import {ReportCreateComponent} from "./components/report-create/report-create.component";
import {IsAuthGuard} from "./services/guard/isauth.guard";
import {ApartmentSubBillListComponent} from "./components/apartment-sub-bills/apartment-sub-bill-list/apartment-sub-bill-list.component";
import {ApartmentSubBillShowComponent} from "./components/apartment-sub-bills/apartment-sub-bill-show/apartment-sub-bill-show.component";
import {ApartmentSubBillTransferCreateComponent} from "./components/apartment-sub-bills/apartment-sub-bill-transfer-create/apartment-sub-bill-transfer-create.component";
import {ApartmentSubBillPaymentComponent} from "./components/apartment-sub-bills/apartment-sub-bill-payment/apartment-sub-bill-payment.component";
import {RequestComponent} from "./components/request/request.component";
import {JobTriggerComponent} from "./components/job-trigger/job-trigger.component";
import {ManagerInfoPageComponent} from "./components/manager-info-page/manager-info-page.component";
import {ManagerOperationCreateComponent} from "./components/manager-operation/manager-operation-create/manager-operation-create.component";
import {ApartmentOperationListComponent} from "./components/apartment-operation-list/apartment-operation-list.component";
import {ManagerInfoUpdateComponent} from "./components/manager/manager-info-update.component";


const routes: Routes = [
    {path: '', redirectTo: '/login', pathMatch: 'full', canActivate: [IsAuthGuard]},
    {path: 'apartments', component: ApartmentsComponent, canActivate: [AuthGuard, ManagerGuard]},
    {path: 'login', component: LoginComponent, canActivate: [IsAuthGuard]},
    {path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
    {path: 'apartments/create', component: ApartmentRegistrationComponent, canActivate: [AuthGuard, ManagerGuard]},
    {path: 'apartments/update/:id', component: ApartmentInfoEditComponent, canActivate: [AuthGuard]},
    {path: 'manager-operation', component: ManagerOperationListComponent, canActivate: [AuthGuard, ManagerGuard]},
    {path: 'announcements', component: AnnouncementsListComponent, canActivate: [AuthGuard]},
    {path: 'announcements/create', component: AnnouncementsCreateComponent, canActivate: [AuthGuard, ManagerGuard]},
    {path: 'announcements/:id', component: AnnouncementsShowComponent, canActivate: [AuthGuard]},
    {path: 'announcements/:id/update', component: AnnouncementsUpdateComponent, canActivate: [AuthGuard, ManagerGuard]},
    {path: 'manager-info', component: ManagerInfoPageComponent, canActivate: [AuthGuard, ManagerGuard]},
    {path: 'apartment-operation', component: ApartmentOperationListComponent, canActivate: [AuthGuard, ManagerGuard]},
    {path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
    {path: 'apartments/create', component: ApartmentRegistrationComponent, canActivate: [AuthGuard, ManagerGuard]},
    {path: 'apartments/update/:number', component: ApartmentInfoEditComponent, canActivate: [AuthGuard]},
    {path: 'manager-operation', component: ManagerOperationListComponent, canActivate: [AuthGuard, ManagerGuard]},
    {path: 'manager-operation/create', component: ManagerOperationCreateComponent, canActivate: [AuthGuard]},
    {path: 'manager-info/update', component: ManagerInfoUpdateComponent, canActivate: [AuthGuard, ManagerGuard]},
    {path: 'apartment-sub-bills', component: ApartmentSubBillListComponent, canActivate: [AuthGuard]},
    {path: 'apartment-sub-bills/:id', component: ApartmentSubBillShowComponent, canActivate: [AuthGuard]},
    {
        path: 'apartment-sub-bill-transfer-create',
        component: ApartmentSubBillTransferCreateComponent,
        canActivate: [AuthGuard]
    },
    {path: 'apartment-sub-bill-payment', component: ApartmentSubBillPaymentComponent, canActivate: [AuthGuard]},
    {
        path: 'communal-utilities/create',
        component: CommunalUtilitiesCreateComponent,
        canActivate: [AuthGuard, ManagerGuard]
    },
    {path: 'communal-utilities', component: CommunalUtilitiesListComponent, canActivate: [AuthGuard]},
    {path: 'communal-utilities/:id', component: CommunalUtilitiesShowComponent, canActivate: [AuthGuard, ManagerGuard]},
    {path: 'apartment', component: ApartmentInfoPageComponent},
    {path: 'request-to-manager', component: RequestComponent},
    {path: 'reports', component: ReportCreateComponent, canActivate: [AuthGuard]},
    {path: 'triggers', component: JobTriggerComponent, canActivate: [AuthGuard, ManagerGuard]},
    {path: '**', component: NotFoundComponent},
];


@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {}
