import {Component, OnInit} from '@angular/core';
import {Announcement} from "../../../models/announcement";
import {AnnouncementService} from "../../../services/announcement.service";

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

    constructor(private announcementService: AnnouncementService) {
    }

    ngOnInit(): void {
        this.retrieveAnnouncements();
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
}
