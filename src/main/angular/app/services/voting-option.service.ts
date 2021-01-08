import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from 'rxjs';
import {VotingOption} from "../models/voting-option";
import {Announcement} from "../models/announcement";
import {HouseVoting} from "../models/house-voting";

@Injectable({
  providedIn: 'root'
})

export class VotingOptionService {
  private baseUrl = 'http://localhost:8888/announcements';

  constructor(private http: HttpClient) { }

  createVotingOption(announcementId: number, votingOption: VotingOption): Observable<any> {
    return this.http.post(`${this.baseUrl}/${announcementId}/house_voting/voting_options`, votingOption);
  }

  getVotingOption(announcementId: number): Observable<VotingOption> {
    return this.http.get(`${this.baseUrl}/${announcementId}/house_voting/voting_options/get_vote`);
  }

  addVote(announcementId: number, votingOptionId: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/${announcementId}/house_voting/voting_options/${votingOptionId}/add_vote`, null);
  }
}
