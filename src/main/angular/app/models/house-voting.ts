import {Announcement} from "./announcement";
import {VotingOption} from "./voting-option";

export class HouseVoting {
    houseVotingId?: number;
    announcement?: Announcement;
    title?: String;
    votingOptions?: VotingOption[];
}
