import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {AnnouncementsListComponent} from "./components/announcements/announcements-list/announcements-list.component";
import {AnnouncementsCreateComponent} from "./components/announcements/announcements-create/announcements-create.component";
import {LoginComponent} from "./components/login/login.component";
import {AnnouncementsShowComponent} from "./components/announcements/announcements-show/announcements-show.component";
//import {AnnouncementsUpdateComponent} from "./components/announcements/announcements-update/announcements-update.component";
import {ManagerOperationListComponent} from "./components/manager-operation/manager-operation-list/manager-operation-list.component";
import {ManagerOperationCreateComponent} from "./components/manager-operation/manager-operation-create/manager-operation-create.component";
import {AuthGuard} from "./services/auth.guard";


const routes: Routes = [
    {path: '', component: LoginComponent },
    {path: 'manager-operation', component: ManagerOperationListComponent, canActivate:[AuthGuard]},
    {path: 'manager-operation/create', component: ManagerOperationCreateComponent, canActivate:[AuthGuard]},
    {path: 'announcements', component: AnnouncementsListComponent, canActivate:[AuthGuard]},
    {path: 'announcements/create', component: AnnouncementsCreateComponent, canActivate:[AuthGuard]},
    {path: 'announcements/:id', component:AnnouncementsShowComponent, canActivate:[AuthGuard]},
];



@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
