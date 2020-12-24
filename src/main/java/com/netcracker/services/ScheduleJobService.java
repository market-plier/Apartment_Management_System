package com.netcracker.services;

import com.netcracker.jobs.DebtPaymentsJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Service
public class ScheduleJobService {
    final ThreadPoolTaskScheduler scheduler;
    Map<Integer, ScheduledFuture<?>> jobsMap = new HashMap<>();

    final DebtPaymentsJob debtPaymentsJob;

    @Autowired
    public ScheduleJobService(ThreadPoolTaskScheduler scheduler, DebtPaymentsJob debtPaymentsJob) {
        this.scheduler = scheduler;
        this.debtPaymentsJob = debtPaymentsJob;
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
    }
}
