import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable} from 'rxjs';
import {VotingOption} from "../models/voting-option";
import {BackEndError} from "../models/back-end-error";
import {MatSnackBar, MatSnackBarConfig} from "@angular/material/snack-bar";

@Injectable({
    providedIn: 'root'
})

export class VotingOptionService {
    private baseUrl = 'http://localhost:8888/announcements';
    err: BackEndError | undefined;

    constructor(private http: HttpClient, private _snackBar: MatSnackBar) {}

    createVotingOption(announcementId: number, votingOption: VotingOption): Observable<any> {
        return this.http.post(`${this.baseUrl}/${announcementId}/house_voting/voting_options`, votingOption);
    }

    getVotingOption(announcementId: number): Observable<VotingOption> {
        return this.http.get(`${this.baseUrl}/${announcementId}/house_voting/voting_options/get_vote`);
    }

    addVote(announcementId: number, votingOptionId: number): Observable<any> {
        return this.http.post(`${this.baseUrl}/${announcementId}/house_voting/voting_options/${votingOptionId}/add_vote`, null);
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
