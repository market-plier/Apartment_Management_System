import {Comment} from "./comment";

export class Announcement {
    announcementId?: number;
    title?: String;
    body?: String;
    isOpened?: Boolean;
    createdAt?: Date;
    comments?: Comment[];
}
