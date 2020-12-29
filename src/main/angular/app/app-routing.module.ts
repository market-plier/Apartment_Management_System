import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {AnnouncementsListComponent} from "./components/announcements/announcements-list/announcements-list.component";
import {AnnouncementsCreateComponent} from "./components/announcements/announcements-create/announcements-create.component";
import {LoginComponent} from "./components/login/login.component";

const routes: Routes = [
    {path: 'login', component: LoginComponent},
    {path: 'announcements', component: AnnouncementsListComponent},
    {path: 'announcements/create', component: AnnouncementsCreateComponent},
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
