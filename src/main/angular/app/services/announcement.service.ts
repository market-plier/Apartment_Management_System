import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Announcement} from "../models/announcement";
import {MatSnackBar, MatSnackBarConfig} from "@angular/material/snack-bar";
import {BackEndError} from "../models/back-end-error";
import {catchError} from "rxjs/operators";

@Injectable({
    providedIn: 'root'
})

export class AnnouncementService {
    private baseUrl = 'http://localhost:8888/announcements';
    err: BackEndError | undefined;

    constructor(private http: HttpClient, private _snackBar: MatSnackBar) {}

    getAnnouncementList(searchText: string = null,
                        startDate: string = null,
                        endDate: string = null,
                        hasVoting: string = null): Observable<Announcement[]> {
        let params = new HttpParams();
        if (searchText != null) {
            params = params.set("searchText",searchText);
        }

        if (startDate != null && endDate != null) {
            params = params.set("startDate",startDate);
            params = params.set("endDate",endDate);
        }

        if (hasVoting != null && hasVoting) {
            params = params.set("hasVoting",hasVoting);
        }

        return this.http.get<Announcement[]>(`${this.baseUrl}/`, {params}).pipe(
            catchError(this.handleError.bind(this))
        );
    }

    getAnnouncement(id: number): Observable<Announcement> {
        return this.http.get(`${this.baseUrl}/${id}`).pipe(
            catchError(this.handleError.bind(this))
        );
    }

    createAnnouncement(announcement: Announcement): Observable<any> {
        return this.http.post(this.baseUrl, announcement).pipe(
            catchError(this.handleError.bind(this))
        );
    }

    updateAnnouncement(id: number, value: Announcement): Observable<any> {
        return this.http.put(`${this.baseUrl}/${id}`, value).pipe(
            catchError(this.handleError.bind(this))
        );
    }

    deleteAnnouncement(id: number): Observable<any> {
        return this.http.delete(`${this.baseUrl}/${id}`).pipe(
            catchError(this.handleError.bind(this))
        );
    }

    handleError(error: HttpErrorResponse) {
        let err = new BackEndError();
        let errorMessage = '';
        err = error.error;

        // @ts-ignore
        errorMessage = errorMessage.concat(err.errors);

        this.openSnackBar(errorMessage, "OK");
    }

    openSnackBar(message: string, action: string) {
        const config = new MatSnackBarConfig();
        config.panelClass = ['snack-bar-error'];
        config.duration = 10000;
        this._snackBar.open(message, action, config);
    }
}
