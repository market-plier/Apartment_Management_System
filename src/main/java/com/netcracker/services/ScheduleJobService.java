package com.netcracker.services;

import com.netcracker.jobs.DebtPaymentsJob;
import com.netcracker.jobs.DebtNotificationJob;
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
    private final DebtNotificationJob debtNotificationJob;

    @Autowired
    public ScheduleJobService(ThreadPoolTaskScheduler scheduler,
                              DebtPaymentsJob debtPaymentsJob,
                              DebtNotificationJob debtNotificationJob) {
        this.scheduler = scheduler;
        this.debtPaymentsJob = debtPaymentsJob;
        this.debtNotificationJob = debtNotificationJob;
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
        addJobToScheduler(2, this.debtNotificationJob.getJob(), this.debtNotificationJob.getTrigger());
    }

    public ThreadPoolTaskScheduler getScheduler() {
        return scheduler;
    }

    public Map<Integer, ScheduledFuture<?>> getJobsMap() {
        return jobsMap;
    }
}
