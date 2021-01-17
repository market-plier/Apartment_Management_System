package com.netcracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Service
public class ScheduleJobService {
    @Autowired
    private ThreadPoolTaskScheduler scheduler;

    Map<Integer, ScheduledFuture<?>> jobsMap = new HashMap<>();

    public void addJobToScheduler(int id, Runnable job, Trigger trigger) {
        ScheduledFuture<?> scheduledTask = scheduler.schedule(job, trigger);
        jobsMap.put(id, scheduledTask);
    }

    public void removeJobFromScheduler(int id) {
        ScheduledFuture<?> scheduledTask = jobsMap.get(id);
        if (scheduledTask != null) {
            scheduledTask.cancel(true);
            jobsMap.put(id, null);
        }
    }

    public ThreadPoolTaskScheduler getScheduler() {
        return scheduler;
    }

    public Map<Integer, ScheduledFuture<?>> getJobsMap() {
        return jobsMap;
    }
}
