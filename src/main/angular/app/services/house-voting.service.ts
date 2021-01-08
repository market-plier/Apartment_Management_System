import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Announcement} from "../models/announcement";
import {HouseVoting} from "../models/house-voting";

@Injectable({
  providedIn: 'root'
})
export class HouseVotingService {
  private baseUrl = 'http://localhost:8888/announcements';

  constructor(private http: HttpClient) { }

  getHouseVotingByAnnouncementId(announcementId: number): Observable<HouseVoting> {
    return this.http.get(`${this.baseUrl}/${announcementId}/house_voting`);
  }

  createHouseVoting(announcementId: number, houseVoting: HouseVoting): Observable<any> {
    return this.http.post(`${this.baseUrl}/${announcementId}/house_voting`, houseVoting);
  }

  deleteHouseVoting(announcementId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${announcementId}/house_voting`);
  }

}
