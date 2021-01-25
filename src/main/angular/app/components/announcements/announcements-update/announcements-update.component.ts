import {Component, OnInit} from '@angular/core';
import {Announcement} from "../../../models/announcement";
import {AnnouncementService} from "../../../services/announcement.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
    selector: 'app-announcements-update',
    templateUrl: './announcements-update.component.html',
    styleUrls: ['./announcements-update.component.css']
})
export class AnnouncementsUpdateComponent implements OnInit {
    form: FormGroup;
    id: number;
    announcement: Announcement;

    loading: boolean = false;

    constructor(private announcementService: AnnouncementService,
                private router: Router,
                private route: ActivatedRoute,
                private _snackBar: MatSnackBar) {
    }

    ngOnInit(): void {
        this.id = this.route.snapshot.params['id'];
        this.loading = true;
        this.announcementService.getAnnouncement(this.id)
            .subscribe(
                data => {
                    this.announcement = data;
                    console.log(data);
                    this.loading = false;
                },
                error => {
                    console.log(error);
                    this.loading = false;
                });

        this.form = new FormGroup({
            title: new FormControl('', [
                Validators.required,
                this.noWhitespaceValidator,
                Validators.minLength(2),
                Validators.maxLength(255)
            ]),
            body: new FormControl('', Validators.maxLength(1023)),
            isOpened: new FormControl('', Validators.required)
        })
    }

    public noWhitespaceValidator(control: FormControl) {
        const isWhitespace = (control.value || '').trim().length === 0;
        const isValid = !isWhitespace;
        return isValid ? null : {'whitespace': true};
    }

    saveAnnouncement(): void {
        if (this.form.valid) {
            this.loading = true;
            this.announcementService.updateAnnouncement(this.id, this.announcement)
                .subscribe(
                    response => {
                        console.log(response);
                        this.openSnackBar('Announcement is updated', 'OK');
                        this.router.navigateByUrl('announcements/' + this.id);
                        this.loading = false;
                    },
                    error => {
                        console.log(error);
                        this.loading = false;
                    });
        }
    };

    openSnackBar(message: string, action: string) {
        this._snackBar.open(message, action, {
            duration: 10000,
        });
    }
}
