import {Component, OnInit} from '@angular/core';
import {Announcement} from "../../../models/announcement";
import {AnnouncementService} from "../../../services/announcement.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {HouseVotingService} from "../../../services/house-voting.service";
import {VotingOptionService} from "../../../services/voting-option.service";
import {HouseVoting} from "../../../models/house-voting";
import {VotingOption} from "../../../models/voting-option";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
    selector: 'app-announcements-create',
    templateUrl: './announcements-create.component.html',
    styleUrls: ['./announcements-create.component.css']
})
export class AnnouncementsCreateComponent implements OnInit {
    announcementFormGroup: FormGroup;
    announcement: Announcement = {
        title: '',
        body: '',
        isOpened: false,
    };

    votingFormGroup: FormGroup;
    hasVoting: boolean = false;
    houseVoting: HouseVoting = {
        title: ''
    };
    maxVotingCount: number = 10;
    minVotingCount: number = 2;
    currVotingCount: number = this.minVotingCount;

    constructor(private announcementService: AnnouncementService,
                private houseVotingService: HouseVotingService,
                private votingOptionService: VotingOptionService,
                private router: Router,
                private _snackBar: MatSnackBar) {}

    counter(i: number) {
        return new Array(i);
    }

    public noWhitespaceValidator(control: FormControl) {
        const isWhitespace = (control.value || '').trim().length === 0;
        const isValid = !isWhitespace;
        return isValid ? null : { 'whitespace': true };
    }

    ngOnInit(): void {
        this.announcementFormGroup = new FormGroup({
            title: new FormControl('',[
                Validators.required,
                this.noWhitespaceValidator,
                Validators.minLength(2),
                Validators.maxLength(255)
            ]),
            body:new FormControl('',Validators.maxLength(1023)),
            isOpened:new FormControl('',Validators.required)
        })

        this.votingFormGroup = new FormGroup({
            title: new FormControl('',[
                Validators.required,
                this.noWhitespaceValidator,
                Validators.minLength(2),
                Validators.maxLength(255)
            ]),
        })

        for (let i = 0; i < this.currVotingCount; i++) {
            this.votingFormGroup.addControl(
                'name' + i,
                new FormControl('',[
                    Validators.required,
                    this.noWhitespaceValidator,
                    Validators.minLength(2),
                    Validators.maxLength(255)
                ])
            )
        }

        console.log(this.votingFormGroup);
    }

    saveAnnouncement(): void {
        this.announcementService.createAnnouncement(this.announcement)
            .subscribe(
                response => {
                    console.log(response);
                    if (this.hasVoting) {
                        this.houseVoting.announcement = response;
                        this.saveHouseVoting(response.announcementId);
                    }
                    else {
                        this.router.navigateByUrl('announcements');
                    }
                    this.openSnackBar('Announcement is created', 'OK');
                },
                error => {
                    console.log(error);
                });
    };

    addVotingOptionControl() {
        if (this.currVotingCount < this.maxVotingCount) {
            this.votingFormGroup.addControl(
                'name' + this.currVotingCount++,
                new FormControl('',[
                    Validators.required,
                    Validators.minLength(2),
                    Validators.maxLength(255)
                ])
            )
        }

    }

    deleteVotingOptionControl() {
        if (this.currVotingCount > this.minVotingCount) {
            this.votingFormGroup.removeControl('name' + (--this.currVotingCount));
        }

    }

    saveHouseVoting(announcementId: number): void {
        this.houseVotingService.createHouseVoting(announcementId, this.houseVoting)
            .subscribe(
                response => {
                    console.log(response);
                    for (let i = 0; i < this.currVotingCount; ++i) {
                        let votingOption: VotingOption = {
                            name: this.votingFormGroup.value['name'+ i],
                            houseVoting: response
                        };

                        this.saveVotingOption(announcementId, votingOption);
                    }

                    this.router.navigateByUrl('announcements');
                },
                error => {
                    console.log(error);
                });
    }

    saveVotingOption(announcementId: number, votingOption:VotingOption): void {
        console.log(votingOption);
        this.votingOptionService.createVotingOption(announcementId, votingOption)
            .subscribe(
                response => {
                    console.log(response);
                },
                error => {
                    console.log(error);
                });
    }

    openSnackBar(message: string, action: string) {
        this._snackBar.open(message, action, {
            duration: 10000,
        });
    }
}
