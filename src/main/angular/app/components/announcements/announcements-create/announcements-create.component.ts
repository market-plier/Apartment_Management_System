import {Component, OnInit} from '@angular/core';
import {Announcement} from "../../../models/announcement";
import {AnnouncementService} from "../../../services/announcement.service";
import {FormControl, Validators} from "@angular/forms";

@Component({
    selector: 'app-announcements-create',
    templateUrl: './announcements-create.component.html',
    styleUrls: ['./announcements-create.component.css']
})
export class AnnouncementsCreateComponent implements OnInit {
    announcement: Announcement = {
        title: '',
        body: '',
        isOpened: false
    };
    submitted = false;
    titleFormControl = new FormControl('', [
        Validators.required,
    ]);


    constructor(private announcementService: AnnouncementService) {}

    ngOnInit(): void {}

    saveAnnouncement(): void {
        const data = {
            title: this.announcement.title,
            body: this.announcement.body,
            isOpened: this.announcement.isOpened
        };

        this.announcementService.createAnnouncement(data)
            .subscribe(
                response => {
                    console.log(response);
                    this.submitted = true;
                },
                error => {
                    console.log(error);
                });
    }

    newAnnouncement(): void {
        this.submitted = false;
        this.announcement = {
            title: '',
            body: '',
            isOpened: false
        };
    }
}
