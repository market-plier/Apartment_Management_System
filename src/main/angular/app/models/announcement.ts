import {Comment} from "./comment";
import {HouseVoting} from "./house-voting";

export class Announcement {
    announcementId?: number;
    title?: String;
    body?: String;
    isOpened?: Boolean;
    createdAt?: Date;
    comments?: Comment[];
    houseVoting?: HouseVoting;
}
