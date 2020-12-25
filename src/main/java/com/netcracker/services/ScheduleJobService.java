package com.netcracker.services;

import com.netcracker.jobs.DebtPaymentsJob;
import com.netcracker.jobs.NotificationJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Service
public class ScheduleJobService {
    private final ThreadPoolTaskScheduler scheduler;
    Map<Integer, ScheduledFuture<?>> jobsMap = new HashMap<>();

    private final DebtPaymentsJob debtPaymentsJob;
    private final NotificationJob notificationJob;

    @Autowired
    public ScheduleJobService(ThreadPoolTaskScheduler scheduler,
                              DebtPaymentsJob debtPaymentsJob,
                              NotificationJob notificationJob) {
        this.scheduler = scheduler;
        this.debtPaymentsJob = debtPaymentsJob;
        this.notificationJob = notificationJob;
        addAllJobs();
    }

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

    void addAllJobs() {
        addJobToScheduler(1, this.debtPaymentsJob.getJob(), this.debtPaymentsJob.getTrigger());
        addJobToScheduler(2, this.notificationJob.getJob(), this.notificationJob.getTrigger());
    }
}
