package org.shen.service;

import org.shen.dao.BaseDao;
import org.shen.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
public class BaseService<T extends BaseEntity, dao extends BaseDao<T>> {
	@Autowired
	private BaseDao<T> dao;
	public void delete(T entity){
		dao.delete(entity);
	}
	public void add(T entity){
		dao.add(entity);
	}
}
