package org.shen.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.shen.entity.Student;
import org.shen.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:resources/spring-mybatis.xml"})  
public class TestMyBatis {
	 private static Logger logger = Logger.getLogger(TestMyBatis.class);  
	 @Autowired
	 private StudentService studentService;
	 @Test  
	 public void test1() {  
        Student student = studentService.selectById(1l); 
        // System.out.println(user.getUserName());  
        // logger.info("值："+user.getUserName());  
        System.out.print(JSON.toJSONString(student));  
	 }  
}
