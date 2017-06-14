package com.ManJiaSonPort.sync.test;


import org.quartz.SchedulerException;
import org.springframework.stereotype.Component;

public class TestScheduleUtils {
    public TestScheduleUtils() throws SchedulerException {
        this.go();
    }

    public static void go() throws SchedulerException {
        QuartzTest.getScheduler();
        QuartzTest.scheduleJobAndStart(
                QuartzTest.getJobDetail(testJob001.class,"job2","group2")
                , QuartzTest.getCronTrigger
                        ("trigger2",
                                "group2",
                                "0/2 * * * * ?")//每隔2秒钟执行一次
//"15 0/2 * * * ?")////每2分钟执行一次（在该分钟的第15秒)
//"0 48 0/1 * * ? *")//每一小时的第48分钟执行一次
        );
    }
    public static void main(String args[]) throws SchedulerException {
        new TestScheduleUtils().go();
    }
}
/*************************************************************************/