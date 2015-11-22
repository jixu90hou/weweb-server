package org.shen.controller;

import org.shen.entity.Student;
import org.shen.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentServiceImpl studentService;
	 @RequestMapping("/selectById")  
	public @ResponseBody String selectById(){
		Student student=studentService.selectById(1l);
		 return JSON.toJSONString(student);
	 } 
	 @RequestMapping("/delete")  
	public @ResponseBody String delete(){
		Student student=studentService.selectById(1l);
		studentService.delete(student);
		 return JSON.toJSONString(student);
	 } 

}
