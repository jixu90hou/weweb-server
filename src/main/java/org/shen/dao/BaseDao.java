package org.shen.dao;

import org.shen.entity.BaseEntity;

import tk.mybatis.mapper.common.Mapper;

public interface BaseDao<T extends BaseEntity> extends Mapper<T>{
	public void add(T entity);
}
