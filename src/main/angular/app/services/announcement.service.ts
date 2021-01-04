import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Announcement} from "../models/announcement";

@Injectable({
    providedIn: 'root'
})

export class AnnouncementService {
    private baseUrl = 'http://localhost:8888/announcements';

    constructor(private http: HttpClient) {
    }

    getAnnouncementList(): Observable<Announcement[]> {
        return this.http.get<Announcement[]>(this.baseUrl);
    }

    getAnnouncement(id: number): Observable<Announcement> {
        return this.http.get(`${this.baseUrl}/${id}`);
    }

    createAnnouncement(announcement: Announcement): Observable<any> {
        return this.http.post(this.baseUrl, announcement);
    }

    updateAnnouncement(id: number, value: Announcement): Observable<any> {
        return this.http.put(`${this.baseUrl}/${id}`, value);
    }

    deleteAnnouncement(id: number): Observable<any> {
        return this.http.delete(`${this.baseUrl}/${id}`);
    }
}
