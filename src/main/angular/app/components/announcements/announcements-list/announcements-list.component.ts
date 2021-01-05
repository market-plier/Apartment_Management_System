import {Component, OnInit} from '@angular/core';
import {Announcement} from "../../../models/announcement";
import {AnnouncementService} from "../../../services/announcement.service";
// @ts-ignore
import {PageEvent} from "@angular/material";
import {TokenStorageService} from "../../../services/token-storage.service";

@Component({
    selector: 'app-announcements-list',
    templateUrl: './announcements-list.component.html',
    styleUrls: ['./announcements-list.component.css']
})
export class AnnouncementsListComponent implements OnInit {
    announcements?: Announcement[];
    currentAnnouncement?: Announcement;
    currentIndex: number = -1;
    title = '';

    lowValue = 0;
    highValue = 10;

    constructor(private announcementService: AnnouncementService,
                private tokenStorageService: TokenStorageService) {}

    ngOnInit(): void {
        this.retrieveAnnouncements();
    }

    public getPaginatorData(event: PageEvent): PageEvent {
        this.lowValue = event.pageIndex * event.pageSize;
        this.highValue = this.lowValue + event.pageSize;
        return event;
    }

    getUser(): any {
        return this.tokenStorageService.getUser();
    }

    retrieveAnnouncements(): void {
        this.announcementService.getAnnouncementList()
            .subscribe(
                data => {
                    this.announcements = data;
                    console.log(data);
                },
                error => {
                    console.log(error);
                });
    }

    refreshList(): void {
        this.retrieveAnnouncements();
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
