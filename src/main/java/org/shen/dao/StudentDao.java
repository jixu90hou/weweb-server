package org.shen.dao;

import org.shen.entity.Student;
public interface StudentDao extends BaseDao<Student>{
	Student selectById(Long id);
}
