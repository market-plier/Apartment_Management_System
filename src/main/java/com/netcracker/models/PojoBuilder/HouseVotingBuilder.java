package com.netcracker.models.PojoBuilder;

import com.netcracker.models.Announcement;
import com.netcracker.models.Comment;
import com.netcracker.models.HouseVoting;
import com.netcracker.models.VotingOption;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class HouseVotingBuilder {
    private BigInteger houseVotingId;
    private Announcement announcement;
    private String title;
    private Collection<VotingOption> votingOptions;

    public HouseVotingBuilder withHouseVotingId(BigInteger houseVotingId) {
        this.houseVotingId = houseVotingId;
        return this;
    }

    public HouseVotingBuilder withAnnouncement(Announcement announcement) {
        this.announcement = announcement;
        return this;
    }

    public HouseVotingBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public HouseVotingBuilder withVotingOptions(List<VotingOption> votingOptions) {
        this.votingOptions = votingOptions;
        return this;
    }

    public HouseVoting build() {
        return new HouseVoting(houseVotingId, announcement, title, votingOptions);
    }
}
