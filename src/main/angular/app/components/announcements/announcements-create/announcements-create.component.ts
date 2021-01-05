import {Component, OnInit} from '@angular/core';
import {Announcement} from "../../../models/announcement";
import {AnnouncementService} from "../../../services/announcement.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
    selector: 'app-announcements-create',
    templateUrl: './announcements-create.component.html',
    styleUrls: ['./announcements-create.component.css']
})
export class AnnouncementsCreateComponent implements OnInit {
    form: FormGroup;

    constructor(private announcementService: AnnouncementService,
                private router: Router) {}

    ngOnInit(): void {
        this.form = new FormGroup({
            title: new FormControl('',[
                Validators.required,
                Validators.minLength(2),
                Validators.maxLength(255)
            ]),
            body:new FormControl('',Validators.maxLength(1023)),
            isOpened:new FormControl('',Validators.required)
        })
    }

    saveAnnouncement(): void {
        this.announcementService.createAnnouncement(this.form.value)
            .subscribe(
                response => {
                    console.log(response);
                    this.router.navigateByUrl('announcements');
                },
                error => {
                    console.log(error);
                });
    };
}
