import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {AnnouncementsListComponent} from "./components/announcements/announcements-list/announcements-list.component";
import {AnnouncementsCreateComponent} from "./components/announcements/announcements-create/announcements-create.component";
import {LoginComponent} from "./components/login/login.component";
import {AnnouncementsShowComponent} from "./components/announcements/announcements-show/announcements-show.component";
import {ManagerOperationListComponent} from "./components/manager-operation/manager-operation-list/manager-operation-list.component";
import {ManagerOperationCreateComponent} from "./components/manager-operation/manager-operation-create/manager-operation-create.component";
import {AuthGuard} from "./services/guard/auth.guard";
import {AnnouncementsUpdateComponent} from "./components/announcements/announcements-update/announcements-update.component";
import {ManagerGuard} from "./services/guard/manager.guard";
import {ManagerApartmentInfoEditComponent} from "./components/apartments/manager-apartment-info-edit/manager-apartment-info-edit.component";
import {ApartmentRegistrationComponent} from "./components/apartments/apartment-registration/apartment-registration/apartment-registration.component";
import {ApartmentsComponent} from "./components/apartments/apartments-list/apartments.component";
import {HomeComponent} from "./components/home/home.component";
import {NotFoundComponent} from "./components/not-found/not-found.component";


const routes: Routes = [
    {path: '',redirectTo: '/login', pathMatch: 'full'},
    {path: 'apartments', component: ApartmentsComponent, canActivate:[AuthGuard, ManagerGuard]},
    {path: 'login', component: LoginComponent},
    {path: 'home', component: HomeComponent, canActivate:[AuthGuard]},
    {path: 'apartments/create', component: ApartmentRegistrationComponent, canActivate:[AuthGuard, ManagerGuard]},
    {path: 'apartments/update/:number', component: ManagerApartmentInfoEditComponent, canActivate:[AuthGuard]},
    {path: 'manager-operation', component: ManagerOperationListComponent, canActivate:[AuthGuard, ManagerGuard]},
    {path: 'manager-operation/create', component: ManagerOperationCreateComponent, canActivate:[AuthGuard]},
    {path: 'announcements', component: AnnouncementsListComponent, canActivate:[AuthGuard]},
    {path: 'announcements/create', component: AnnouncementsCreateComponent, canActivate:[AuthGuard, ManagerGuard]},
    {path: 'announcements/:id', component:AnnouncementsShowComponent, canActivate:[AuthGuard]},
    {path: 'announcements/:id/update', component:AnnouncementsUpdateComponent, canActivate:[AuthGuard, ManagerGuard]},
    {path: '**', component: NotFoundComponent},
];



@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
