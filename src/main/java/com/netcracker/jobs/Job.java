package com.netcracker.jobs;

import org.springframework.scheduling.Trigger;

public interface Job {
    Trigger getTrigger();

    Runnable getJob();
}
