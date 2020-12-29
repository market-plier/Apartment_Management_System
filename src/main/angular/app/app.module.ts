import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AnnouncementsListComponent} from './components/announcements/announcements-list/announcements-list.component';
import {AnnouncementsShowComponent} from './components/announcements/announcements-show/announcements-show.component';
import {AnnouncementsCreateComponent} from './components/announcements/announcements-create/announcements-create.component';
import {AnnouncementsUpdateComponent} from './components/announcements/announcements-update/announcements-update.component';
import {LoginComponent} from './components/login/login.component';


import {authInterceptorProviders} from './helpers/auth.interceptor';

@NgModule({
    declarations: [
        AppComponent,
        AnnouncementsListComponent,
        AnnouncementsShowComponent,
        AnnouncementsCreateComponent,
        AnnouncementsUpdateComponent,
        LoginComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule
    ],
    providers: [authInterceptorProviders],
    bootstrap: [AppComponent]
})
export class AppModule {
}
