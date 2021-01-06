import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Comment} from "../models/comment";

@Injectable({
    providedIn: 'root'
})
export class CommentService {
    private baseUrl = 'http://localhost:8888/comment/';

    constructor(private http: HttpClient) {
    }

    createComment(comment: Comment): Observable<any> {
        return this.http.post(this.baseUrl, comment);
    }

    updateComment(comment: Comment): Observable<any> {
        return this.http.put(this.baseUrl, comment);
    }

    deleteComment(id: number): Observable<any> {
        return this.http.delete(`${this.baseUrl}${id}`);
    }
}
