import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {HouseVoting} from "../models/house-voting";
import {BackEndError} from "../models/back-end-error";
import {MatSnackBar, MatSnackBarConfig} from "@angular/material/snack-bar";

@Injectable({
  providedIn: 'root'
})
export class HouseVotingService {
  private baseUrl = 'http://localhost:8888/announcements';
  err: BackEndError | undefined;

  constructor(private http: HttpClient, private _snackBar: MatSnackBar) {}

  getHouseVotingByAnnouncementId(announcementId: number): Observable<HouseVoting> {
    return this.http.get(`${this.baseUrl}/${announcementId}/house_voting`);
  }

  createHouseVoting(announcementId: number, houseVoting: HouseVoting): Observable<any> {
    return this.http.post(`${this.baseUrl}/${announcementId}/house_voting`, houseVoting);
  }

  deleteHouseVoting(announcementId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${announcementId}/house_voting`);
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
