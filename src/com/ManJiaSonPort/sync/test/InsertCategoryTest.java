package com.ManJiaSonPort.sync.test;

import com.ManJiaSonPort.sync.service.CategoryDo.InsertCategoryService;
import com.ManJiaSonPort.sync.utils.SpringUtil;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/15.
 */
@Component
public class InsertCategoryTest {
    @Autowired
    InsertCategoryService insertCategoryService;

    public void f() throws IOException {
        String str="C:\\Users\\Administrator\\Desktop\\11.txt";
        String s = FileUtil.readAsString(new File(str));
        insertCategoryService.insertOrUpdateCategory(s);
    }


    public static void main(String[]args) throws IOException {
        String []configs={"classpath*:applicationContext.xml"};
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configs);
        System.out.println(ctx+"---------------------");
        InsertCategoryTest ict = SpringUtil.getBean(InsertCategoryTest.class);
        ict.f();

    }
}
