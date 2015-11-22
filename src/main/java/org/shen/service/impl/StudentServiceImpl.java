package org.shen.service.impl;

import javax.annotation.Resource;

import org.shen.dao.StudentDao;
import org.shen.entity.Student;
import org.shen.service.BaseService;
import org.shen.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends
		BaseService<Student, StudentDao> implements StudentService {
	@Resource
	private StudentDao dao;

	public Student selectById(Long id) {
		return dao.selectById(id);
	}
}
