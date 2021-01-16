import {Component, OnInit} from '@angular/core';
import {Announcement} from "../../../models/announcement";
import {AnnouncementService} from "../../../services/announcement.service";
// @ts-ignore
import {PageEvent} from "@angular/material";
import {TokenStorageService} from "../../../services/token-storage.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {DatePipe} from "@angular/common";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
    selector: 'app-announcements-list',
    templateUrl: './announcements-list.component.html',
    styleUrls: ['./announcements-list.component.scss']
})
export class AnnouncementsListComponent implements OnInit {
    announcements?: Announcement[];
    currentAnnouncement?: Announcement;
    currentIndex: number = -1;

    searchText: string;
    startDate: Date;
    endDate: Date;
    hasVoting: string;

    lowValue = 0;
    highValue = 10;

    range = new FormGroup({
        start: new FormControl(),
        end: new FormControl()
    });

    constructor(private announcementService: AnnouncementService,
                private tokenStorageService: TokenStorageService,
                private _snackBar: MatSnackBar,
                private datePipe: DatePipe) {}

    ngOnInit(): void {
        this.getAnnouncements();
    }

    public getPaginatorData(event: PageEvent): PageEvent {
        this.lowValue = event.pageIndex * event.pageSize;
        this.highValue = this.lowValue + event.pageSize;
        return event;
    }

    getRole(): any {
        return this.tokenStorageService.getRole();
    }

    getAnnouncements(): void {
        this.announcementService.getAnnouncementList(
            this.searchText,
            this.datePipe.transform(this.startDate,'MM/dd/yyyy'),
            this.datePipe.transform(this.endDate,'MM/dd/yyyy'),
            this.hasVoting
        )
            .subscribe(
                data => {
                    this.announcements = data;
                    this.refreshCurrentAnnouncement();
                    console.log(data);
                },
                error => {
                    console.log(error);
                });
    }


    refreshList(): void {
        this.getAnnouncements();
        this.refreshCurrentAnnouncement();
    }

    refreshCurrentAnnouncement(): void {
        this.currentAnnouncement = undefined;
        this.currentIndex = -1;
    }

    setActiveAnnouncement(announcement: Announcement, index: number): void {
        this.currentAnnouncement = announcement;
        this.currentIndex = index;
    }

    deleteAnnouncement(announcementId: number): void {
        this.announcementService.deleteAnnouncement(announcementId)
            .subscribe(
                response => {
                    console.log(response);
                    this.refreshList();
                },
                error => {
                    console.log(error);
                });
    }
}
