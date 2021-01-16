import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {Comment} from "../models/comment";
import {BackEndError} from "../models/back-end-error";
import {MatSnackBar, MatSnackBarConfig} from "@angular/material/snack-bar";

@Injectable({
    providedIn: 'root'
})
export class CommentService {
    private baseUrl = 'http://localhost:8888/comment/';
    err: BackEndError | undefined;

    constructor(private http: HttpClient, private _snackBar: MatSnackBar) {}

    createComment(comment: Comment): Observable<any> {
        return this.http.post(this.baseUrl, comment);
    }

    updateComment(comment: Comment): Observable<any> {
        return this.http.put(this.baseUrl, comment);
    }

    deleteComment(id: number): Observable<any> {
        return this.http.delete(`${this.baseUrl}${id}`);
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
