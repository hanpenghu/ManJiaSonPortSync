package com.ManJiaSonPort.sync.service.CategoryDo;
import com.ManJiaSonPort.sync.utils.SpringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/6/15.
 */
@Component
public class DoInsertOrUpdateCategoryUseJsonStr {
    private Logger log= LogManager.getLogger(this.getClass().getName());
    @Autowired
    GetCategoryJsonArrayStrFromInterface getCategoryJsonArrayStrFromInterface;
    @Autowired
    InsertCategoryService insertCategoryService;




    public void doIt(){
        log.info("开始同步商品分类表！！！");
        System.out.println("开始同步商品分类表！！！");
        String cjsi = getCategoryJsonArrayStrFromInterface.getCategoryJsonArrayStrFromInterface();
        System.out.println("￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥"+cjsi);
        insertCategoryService.insertOrUpdateCategory(cjsi);
    }



}
