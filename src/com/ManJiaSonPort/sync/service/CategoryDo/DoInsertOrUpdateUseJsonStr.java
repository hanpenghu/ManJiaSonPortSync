package com.ManJiaSonPort.sync.service.CategoryDo;

import com.ManJiaSonPort.sync.utils.SpringUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/6/15.
 */
@Component
public class DoInsertOrUpdateUseJsonStr implements Job{
    @Autowired
    GetCategoryJsonArrayStrFromInterface getCategoryJsonArrayStrFromInterface;
    @Autowired
    InsertCategoryService insertCategoryService;
/////////////////////////////////////////////////////////////////////////
    public void doIt(){
        String cjsi = getCategoryJsonArrayStrFromInterface.getCategoryJsonArrayStrFromInterface();
        insertCategoryService.insertOrUpdateCategory(cjsi);
    }
/////////////////////////////////////////////////////////////////////////

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        doIt();
    }
    /////////////////////////////////////////////////////////////////////////
    public static void main(String[]args){
        String []configs={"classpath*:applicationContext.xml"};
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configs);
        System.out.println(ctx+"---------------------");
        DoInsertOrUpdateUseJsonStr bean = SpringUtil.getBean(DoInsertOrUpdateUseJsonStr.class);
        bean.doIt();
    }
}
/////////////////////////////////////////////////////////////////////////