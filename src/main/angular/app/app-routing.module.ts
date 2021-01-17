import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AnnouncementsListComponent} from "../../../../../../../IdeaProjects/Netcracker/trunk/src/main/angular/app/components/announcements/announcements-list/announcements-list.component";
import {AnnouncementsCreateComponent} from "../../../../../../../IdeaProjects/Netcracker/trunk/src/main/angular/app/components/announcements/announcements-create/announcements-create.component";
import {LoginComponent} from "../../../../../../../IdeaProjects/Netcracker/trunk/src/main/angular/app/components/login/login.component";
import {AnnouncementsShowComponent} from "../../../../../../../IdeaProjects/Netcracker/trunk/src/main/angular/app/components/announcements/announcements-show/announcements-show.component";
import {ManagerOperationListComponent} from "../../../../../../../IdeaProjects/Netcracker/trunk/src/main/angular/app/components/manager-operation/manager-operation-list/manager-operation-list.component";
import {AuthGuard} from "../../../../../../../IdeaProjects/Netcracker/trunk/src/main/angular/app/services/guard/auth.guard";
import {AnnouncementsUpdateComponent} from "../../../../../../../IdeaProjects/Netcracker/trunk/src/main/angular/app/components/announcements/announcements-update/announcements-update.component";
import {ManagerGuard} from "../../../../../../../IdeaProjects/Netcracker/trunk/src/main/angular/app/services/guard/manager.guard";
import {ApartmentInfoEditComponent} from "../../../../../../../IdeaProjects/Netcracker/trunk/src/main/angular/app/components/apartments/manager-apartment-info-edit/apartment-info-edit.component";
import {ApartmentRegistrationComponent} from "../../../../../../../IdeaProjects/Netcracker/trunk/src/main/angular/app/components/apartments/apartment-registration/apartment-registration.component";
import {ApartmentsComponent} from "../../../../../../../IdeaProjects/Netcracker/trunk/src/main/angular/app/components/apartments/apartments-list/apartments.component";
import {HomeComponent} from "../../../../../../../IdeaProjects/Netcracker/trunk/src/main/angular/app/components/home/home.component";
import {NotFoundComponent} from "../../../../../../../IdeaProjects/Netcracker/trunk/src/main/angular/app/components/not-found/not-found.component";
import {ApartmentInfoPageComponent} from "../../../../../../../IdeaProjects/Netcracker/trunk/src/main/angular/app/components/apartments/apartment-info-page/apartment-info-page.component";
import {CommunalUtilitiesCreateComponent} from "../../../../../../../IdeaProjects/Netcracker/trunk/src/main/angular/app/components/communal-utilities/communal-utilities-create/communal-utilities-create.component";
import {CommunalUtilitiesListComponent} from "../../../../../../../IdeaProjects/Netcracker/trunk/src/main/angular/app/components/communal-utilities/communal-utilities-list/communal-utilities-list.component";
import {CommunalUtilitiesShowComponent} from "../../../../../../../IdeaProjects/Netcracker/trunk/src/main/angular/app/components/communal-utilities/communal-utilities-show/communal-utilities-show.component";
import {ReportCreateComponent} from "../../../../../../../IdeaProjects/Netcracker/trunk/src/main/angular/app/components/report-create/report-create.component";
import {IsAuthGuard} from "../../../../../../../IdeaProjects/Netcracker/trunk/src/main/angular/app/services/guard/isauth.guard";
import {ApartmentSubBillListComponent} from "../../../../../../../IdeaProjects/Netcracker/trunk/src/main/angular/app/components/apartment-sub-bills/apartment-sub-bill-list/apartment-sub-bill-list.component";
import {ApartmentSubBillShowComponent} from "../../../../../../../IdeaProjects/Netcracker/trunk/src/main/angular/app/components/apartment-sub-bills/apartment-sub-bill-show/apartment-sub-bill-show.component";
import {ApartmentSubBillTransferCreateComponent} from "../../../../../../../IdeaProjects/Netcracker/trunk/src/main/angular/app/components/apartment-sub-bills/apartment-sub-bill-transfer-create/apartment-sub-bill-transfer-create.component";
import {ApartmentSubBillPaymentComponent} from "../../../../../../../IdeaProjects/Netcracker/trunk/src/main/angular/app/components/apartment-sub-bills/apartment-sub-bill-payment/apartment-sub-bill-payment.component";
import {RequestComponent} from "../../../../../../../IdeaProjects/Netcracker/trunk/src/main/angular/app/components/request/request.component";
import {JobTriggerComponent} from "../../../../../../../IdeaProjects/Netcracker/trunk/src/main/angular/app/components/job-trigger/job-trigger.component";
import {ManagerInfoPageComponent} from "../../../../../../../IdeaProjects/Netcracker/trunk/src/main/angular/app/components/manager-info-page/manager-info-page.component";
import {ManagerInfoUpdateComponent} from "../../../../../../../IdeaProjects/Netcracker/trunk/src/main/angular/app/components/manager/manager-info-update.component";
import {ManagerSubBillListComponent} from "../../../../../../../IdeaProjects/Netcracker/trunk/src/main/angular/app/components/manager-sub-bill/manager-sub-bill-list/manager-sub-bill-list.component";
import {ManagerOperationCreateComponent} from "../../../../../../../IdeaProjects/Netcracker/trunk/src/main/angular/app/components/manager-operation/manager-operation-create/manager-operation-create.component";


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
    {path: 'manager-info', component: ManagerInfoPageComponent, canActivate: [AuthGuard,ManagerGuard]},
    {path: 'announcements/:id/update', component: AnnouncementsUpdateComponent, canActivate: [AuthGuard, ManagerGuard]},
    {path: 'communal-utilities/create', component: CommunalUtilitiesCreateComponent, canActivate: [ManagerGuard]},
    {path: 'communal-utilities', component: CommunalUtilitiesListComponent, canActivate: [AuthGuard, ManagerGuard]},
    {path: 'communal-utilities/:id', component: CommunalUtilitiesShowComponent, canActivate: [ManagerGuard]},

    {path: 'home', component: HomeComponent, canActivate:[AuthGuard]},
    {path: 'apartments/create', component: ApartmentRegistrationComponent, canActivate:[AuthGuard, ManagerGuard]},
    {path: 'apartments/update/:number', component: ApartmentInfoEditComponent, canActivate:[AuthGuard]},
    {path: 'manager-operation', component: ManagerOperationListComponent, canActivate:[AuthGuard, ManagerGuard]},
    {path: 'manager-operation/create', component: ManagerOperationCreateComponent, canActivate:[AuthGuard]},
    {path: 'announcements', component: AnnouncementsListComponent, canActivate:[AuthGuard]},
    {path: 'announcements/create', component: AnnouncementsCreateComponent, canActivate:[AuthGuard, ManagerGuard]},
    {path: 'announcements/:id', component:AnnouncementsShowComponent, canActivate:[AuthGuard]},
    {path: 'announcements/:id/update', component:AnnouncementsUpdateComponent, canActivate:[AuthGuard, ManagerGuard]},
    {path: 'apartment-sub-bills', component:ApartmentSubBillListComponent, canActivate:[AuthGuard]},
    {path: 'apartment-sub-bills/:id', component:ApartmentSubBillShowComponent, canActivate:[AuthGuard]},
    {path: 'apartment-sub-bill-transfer-create', component:ApartmentSubBillTransferCreateComponent, canActivate:[AuthGuard]},
    {path: 'apartment-sub-bill-payment', component:ApartmentSubBillPaymentComponent, canActivate:[AuthGuard]},
    {
        path: 'communal-utilities/create',
        component: CommunalUtilitiesCreateComponent,
        canActivate: [AuthGuard, ManagerGuard]
    },
    {path: 'communal-utilities', component: CommunalUtilitiesListComponent, canActivate: [AuthGuard]},
    {path: 'communal-utilities/:id', component: CommunalUtilitiesShowComponent, canActivate: [AuthGuard, ManagerGuard]},
    {path: 'manager-info', component: ManagerInfoPageComponent, canActivate: [AuthGuard,ManagerGuard]},
    {path: 'manager-info/update', component: ManagerInfoUpdateComponent, canActivate: [ManagerGuard]},
    {path: 'apartment', component: ApartmentInfoPageComponent},
    {path: 'request-to-manager', component: RequestComponent},
    {path: 'reports', component: ReportCreateComponent, canActivate: [AuthGuard]},
    {path: 'triggers', component: JobTriggerComponent,canActivate: [AuthGuard, ManagerGuard]},

    {path: '**', component: NotFoundComponent},
];


@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {}
