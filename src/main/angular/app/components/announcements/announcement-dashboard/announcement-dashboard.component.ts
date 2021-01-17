import { Component, OnInit } from '@angular/core';
import {Announcement} from "../../../models/announcement";
import {AnnouncementService} from "../../../services/announcement.service";
import {TokenStorageService} from "../../../services/token-storage.service";
import {MatSnackBar} from "@angular/material/snack-bar";
// @ts-ignore
import {PageEvent} from "@angular/material";

@Component({
  selector: 'app-announcement-dashboard',
  templateUrl: './announcement-dashboard.component.html',
  styleUrls: ['./announcement-dashboard.component.css']
})
export class AnnouncementDashboardComponent implements OnInit {
  announcements?: Announcement[];

  lowValue = 0;
  highValue = 5;

  constructor(private announcementService: AnnouncementService,
              private tokenStorageService: TokenStorageService,
              private _snackBar: MatSnackBar) {}

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

}
