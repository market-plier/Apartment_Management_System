import {Component, OnInit} from '@angular/core';
import {Announcement} from "../../../models/announcement";
import {AnnouncementService} from "../../../services/announcement.service";
import {ActivatedRoute, Router} from "@angular/router";
import {TokenStorageService} from "../../../services/token-storage.service";
import {VotingOptionService} from "../../../services/voting-option.service";
import {VotingOption} from "../../../models/voting-option";

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
    commentsOpenState = false;
    selectedVotingOptionId?:number;
    currentVotingOption?:VotingOption;

    constructor(
        private announcementService: AnnouncementService,
        private votingOptionService: VotingOptionService,
        private tokenStorageService: TokenStorageService,
        private route: ActivatedRoute,
        private router: Router) {
    }

    ngOnInit(): void {
        this.getAnnouncement(this.route.snapshot.params.id);
        this.getVotingOption(this.route.snapshot.params.id);
    }

    getUser(): any {
        return this.tokenStorageService.getUser();
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
}
