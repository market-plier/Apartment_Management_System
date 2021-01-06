import {Component, Inject, OnInit} from '@angular/core';
import {Announcement} from "../../../models/announcement";
import {AnnouncementService} from "../../../services/announcement.service";
import {ActivatedRoute, Router} from "@angular/router";
import {TokenStorageService} from "../../../services/token-storage.service";
import {VotingOptionService} from "../../../services/voting-option.service";
import {VotingOption} from "../../../models/voting-option";
import {Comment} from "../../../models/comment";
import {CommentService} from "../../../services/comment.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MatDialog, MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
    selector: 'app-announcements-show',
    templateUrl: './announcements-show.component.html',
    styleUrls: ['./announcements-show.component.css']
})
export class AnnouncementsShowComponent implements OnInit {
    announcement: Announcement = {
        title: '',
        body: '',
        isOpened: false,
        comments: []
    };

    selectedVotingOptionId?:number;
    currentVotingOption?:VotingOption;

    form: FormGroup;
    commentsListOpen = false;
    commentsCreationOpen = false;
    comment: Comment = {
        body: ''
    }

    constructor(public dialog: MatDialog,
                private announcementService: AnnouncementService,
                private votingOptionService: VotingOptionService,
                private commentService: CommentService,
                private tokenStorageService: TokenStorageService,
                private route: ActivatedRoute,
                private router: Router) {
    }

    ngOnInit(): void {
        this.getAnnouncement(this.route.snapshot.params.id);
        this.getVotingOption(this.route.snapshot.params.id);

        this.form = new FormGroup({
            body: new FormControl('',[
                Validators.required,
                Validators.minLength(1),
                Validators.maxLength(1000)
            ]),
        })
    }

    getRole(): any {
        return this.tokenStorageService.getRole();
    }

    getAccountId(): any {
        return this.tokenStorageService.getAccountId();
    }

    getAnnouncement(id: number): void {
        this.announcementService.getAnnouncement(id)
            .subscribe(
                data => {
                    this.announcement = data;
                    if (this.announcement.houseVoting != null
                        && this.announcement.houseVoting.votingOptions?.length != 0) {
                        let sum = this.getSumVotes();
                        var votingOptions = this.announcement.houseVoting.votingOptions;

                        if(votingOptions != null) {
                            this.selectedVotingOptionId = votingOptions[0].votingOptionId;

                            for(let i = 0; i < votingOptions.length;++i) {
                                // @ts-ignore
                                votingOptions[i]["percent"] = votingOptions[i].count/sum*100;
                            }
                        }

                    }
                    console.log(data);
                },
                error => {
                    console.log(error);
                });
    }

    getSumVotes(): number {
        var sum = 0;

        // @ts-ignore
        for (let votingOption of this.announcement.houseVoting.votingOptions) {
            // @ts-ignore
            sum += votingOption.count;
        }
        return sum;
    }

    getVotingOption(id: number): void {
        this.votingOptionService.getVotingOption(id)
            .subscribe(
                data => {
                    this.currentVotingOption = data;
                    console.log(data);
                },
                error => {
                    console.log(error);
                });
    }

    addVote(): void {
        if (this.selectedVotingOptionId != null && this.announcement.announcementId != null)
            this.votingOptionService.addVote(
                this.announcement.announcementId,
                this.selectedVotingOptionId
            );
    }

    deleteAnnouncement(announcementId: number): void {
        this.announcementService.deleteAnnouncement(announcementId)
            .subscribe(
                response => {
                    console.log(response);
                    this.router.navigate(["/announcements"]);
                },
                error => {
                    console.log(error);
                });
    }

    saveComment() {
        let currentComment = this.comment;
        currentComment['announcementId'] = this.announcement.announcementId;

        this.commentService.createComment(currentComment)
            .subscribe(
                response => {
                    console.log(response);
                    this.getAnnouncement(this.announcement.announcementId);
                    this.commentsCreationOpen = false;
                    this.comment.body = '';
                },
                error => {
                    console.log(error);
                });
    }

    deleteComment(commentId: number) {
        this.commentService.deleteComment(commentId)
            .subscribe(
                response => {
                    console.log(response);
                    this.getAnnouncement(this.announcement.announcementId);
                },
                error => {
                    console.log(error);
                });
    }

    openDialog(comment: Comment): void {
        const dialogRef = this.dialog.open(CommentEditDialog, {
            data: comment
        });

        dialogRef.afterClosed().subscribe(result => {
            console.log('The dialog was closed');
            this.getAnnouncement(this.announcement.announcementId);
        });
    }
}

@Component({
    selector: 'comment-edit-dialog',
    templateUrl: 'comment-edit-dialog.html',
})

export class CommentEditDialog {
    form: FormGroup;

    constructor(private commentService: CommentService,
                public dialogRef: MatDialogRef<CommentEditDialog>,
                @Inject(MAT_DIALOG_DATA) public data: Comment) {
        this.form = new FormGroup({
            body: new FormControl('',[
                Validators.required,
                Validators.minLength(1),
                Validators.maxLength(1000)
            ]),
        })
    }

    onNoClick(): void {
        this.dialogRef.close();
    }

    updateComment() {
        this.commentService.updateComment(this.data)
            .subscribe(
                response => {
                    console.log(response);
                    this.dialogRef.close();
                },
                error => {
                    console.log(error);
                });
    }
}