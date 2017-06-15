package com.ManJiaSonPort.sync.test.test_schedule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
/**
 * Created by Administrator on 2017/6/15.
 */
////////////////////////2017_6_15   weekday(4)   9:45:41/////////////////////////////////////////////////
public class ScheduleTest {
    protected Logger log= LogManager.getLogger(this.getClass().getName());
    //////////////////////////////2017_6_15   weekday(4)   9:45:30///////////////////////////////////////////
    public static void main(String[]args) throws SchedulerException {
        new ScheduleTest().go();
    }
    /////////////////////////////2017_6_15   weekday(4)   9:45:23////////////////////////////////////////////
    public void go() throws SchedulerException {
        //得到一个scheduler引用
        SchedulerFactory sf= new StdSchedulerFactory();
        Scheduler sch = sf.getScheduler();
        //将job(JobTest001)加入到真正要工作的job中
        JobDetail job = newJob(JobTest001.class).withIdentity("job1", "group1").build();
        CronTrigger trigger = newTrigger().withIdentity("trigger1", "group1")
                .withSchedule(cronSchedule("0/3 * * * * ?")).build();//每隔3秒执行一次
        Date date = sch.scheduleJob(job, trigger);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        System.out.println(job.getKey() + " 已被安排执行于: " + sdf.format(date) + "，并且以如下重复规则重复执行: " + trigger.getCronExpression());


        //开始job2
        // job 2将每2分钟执行一次（在该分钟的第15秒)
        job = newJob(JobTest002.class).withIdentity("job2", "group1").build();
        //2秒钟执行一次
        trigger = newTrigger().withIdentity("trigger2", "group1").withSchedule(cronSchedule("0/2 * * * * ?")).build();
        date = sch.scheduleJob(job, trigger);
        System.out.println(job.getKey() + " 已被安排执行于: " + sdf.format(date) + "，并且以如下重复规则重复执行: "+ trigger.getCronExpression());


        // 开始执行，start()方法被调用后，计时器就开始工作，计时调度中允许放入N个Job
        sch.start();

        try {
            //主线程等待一分钟
            Thread.sleep(60L * 1000L);
        } catch (Exception e) {}
        //关闭定时调度，定时器不再工作
        sch.shutdown(true);

    }
/////////////////////////////////2017_6_15   weekday(4)   9:45:14////////////////////////////////////////
}
////////////////////////////////////2017_6_15   weekday(4)   9:45:08/////////////////////////////////////


/*
0 0 12 * * ?	每天12点触发
        0 15 10 ? * *	每天10点15分触发
        0 15 10 * * ?	每天10点15分触发
        0 15 10 * * ? *	每天10点15分触发
        0 15 10 * * ? 2005	2005年每天10点15分触发
        0 * 14 * * ?	每天下午的 2点到2点59分每分触发
        0 0/5 14 * * ?	每天下午的 2点到2点59分(整点开始，每隔5分触发)
        0 0/5 14,18 * * ?	每天下午的 2点到2点59分(整点开始，每隔5分触发) 每天下午的 18点到18点59分(整点开始，每隔5分触发)
        0 0-5 14 * * ?	每天下午的 2点到2点05分每分触发
        0 10,44 14 ? 3 WED	3月分每周三下午的 2点10分和2点44分触发
        0 15 10 ? * MON-FRI	从周一到周五每天上午的10点15分触发
        0 15 10 15 * ?	每月15号上午10点15分触发
        0 15 10 L * ?	每月最后一天的10点15分触发
        0 15 10 ? * 6L	每月最后一周的星期五的10点15分触发
        0 15 10 ? * 6L 2002-2005	从2002年到2005年每月最后一周的星期五的10点15分触发
        0 15 10 ? * 6#3	每月的第三周的星期五开始触发
        0 0 12 1/5 * ?	每月的第一个中午开始每隔5天触发一次
        0 11 11 11 11 ?	每年的11月11号 11点11分触发(光棍节)*/
