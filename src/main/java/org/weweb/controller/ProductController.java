package org.weweb.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.weweb.entity.result.Result;
import org.weweb.entity.result.ResultFactory;
import org.weweb.service.ProductService;
import org.weweb.service.ProductStockService;
import org.weweb.util.ConstantUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by jackshen on 2017/3/12.
 */

@Controller
@RequestMapping("/product")
public class ProductController {
    private static Logger logger = Logger.getLogger(NewsController.class);
    @Autowired
    private ProductStockService productStockService;
    private final Executor executor= Executors.newCachedThreadPool();
    private final BlockingQueue<Thread> blockingQueue=new ArrayBlockingQueue<>(100);
    @Autowired
    private ProductService productService;
    @RequestMapping(value = "/killProduct",method = RequestMethod.POST)
    public
    @ResponseBody
    Result killProduct(HttpServletRequest request) {
        try {
            int threadNum=10;
            int result=0;
            String reqThreadNum=request.getParameter("threadNum");
            if (StringUtils.isNotEmpty(reqThreadNum)) threadNum=Integer.valueOf(reqThreadNum);
            for(int i=0;i<threadNum;i++){
                new Thread(()->{
                    productStockService.deductStock(17L);
                }).start();
            }
            //抢购小米6
        } catch (Exception e) {
            logger.error("killProducts--->", e);
            return ResultFactory.generateResult(ConstantUtil.ERROR_CODE, ConstantUtil.ERROR_MSG);
        }
        return ResultFactory.generateResult(ConstantUtil.SUCCESS_CODE, ConstantUtil.SUCCESS_MSG);

    }


    @RequestMapping(value = "/addData",method = RequestMethod.POST)
    public
    @ResponseBody
    Result addData(HttpServletRequest request) {
        try {
            for (int i=0;i<10;i++){
               productService.addData(i);
            }

        } catch (Exception e) {
            logger.error("killProducts--->", e);
            return ResultFactory.generateResult(ConstantUtil.ERROR_CODE, ConstantUtil.ERROR_MSG);
        }
        return ResultFactory.generateResult(ConstantUtil.SUCCESS_CODE, ConstantUtil.SUCCESS_MSG);

    }

}
