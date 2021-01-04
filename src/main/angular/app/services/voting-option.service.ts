import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from 'rxjs';
import {VotingOption} from "../models/voting-option";
import {Announcement} from "../models/announcement";

@Injectable({
  providedIn: 'root'
})

export class VotingOptionService {
  private baseUrl = 'http://localhost:8888/announcements';

  constructor(private http: HttpClient) { }

  getVotingOption(announcementId: number): Observable<VotingOption> {
    return this.http.get(`${this.baseUrl}/${announcementId}/house_voting/voting_options/get_vote`);
  }

  addVote(announcementId: number, votingOptionId: number): Observable<any> {
    // @ts-ignore
    return this.http.post(`${this.baseUrl}/${announcementId}/house_voting/voting_options/${votingOptionId}/add_vote`);
  }
}
