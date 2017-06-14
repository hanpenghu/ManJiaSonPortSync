package com.ManJiaSonPort.sync.test;
import java.util.Date;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class QuartzTest {
    private static SchedulerFactory sf = new StdSchedulerFactory();
    private static Scheduler sched=null;
    public static SchedulerFactory getSchedulerFactoryInstance(){
        if (sf == null) {
            sf = new StdSchedulerFactory();
        }
        return sf;
    }

    public static Scheduler getScheduler() throws SchedulerException {
        if(sched==null){
            sched=getSchedulerFactoryInstance().getScheduler();
        }
        return sched;
    }

    public static JobDetail getJobDetail(Class clazz,String jobName,String groupName){
        return newJob(clazz).withIdentity("job1", "group1").build();
    }

    public static CronTrigger getCronTrigger(String triggerName,String groupName,String cronSchedule){
        return newTrigger()
                .withIdentity(triggerName, groupName)
                .withSchedule(cronSchedule(cronSchedule))
                .build();
    }

    public static Date scheduleJob(JobDetail job, CronTrigger trigger) throws SchedulerException {
        Date date=sched.scheduleJob(job,trigger);
        System.out.println("我们的定时任务是(my schedule is): "+date+"!!!!!!");
        return date;
    }
    public static void startSchedule() throws SchedulerException {
        sched.start();
    }

    public static Date scheduleJobAndStart(JobDetail job, CronTrigger trigger)throws SchedulerException{
        Date date=sched.scheduleJob(job,trigger);
        System.out.println("我们的定时任务是(my schedule is): "+date+"!!!!!!");
        sched.start();
        return date;
    }

}
/*************************************************************************/


