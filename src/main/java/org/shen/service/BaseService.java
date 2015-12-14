package org.shen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import tk.mybatis.mapper.common.Mapper;

import com.github.pagehelper.Page;
//http://www.tuicool.com/articles/bIRfUv(学习参考)
public abstract class BaseService<T,Dao extends Mapper<T>> {
	@Autowired
	protected Dao dao;
	/**
	 * 更新操作
	 * @param record
	 * @param flag
	 * @return 
	 */
	public Integer update(T clazz, Boolean selective) {
		if (selective) {
			return dao.updateByPrimaryKeySelective(clazz);
		}
		return dao.updateByPrimaryKey(clazz);
	}
	/**
	 * 删除操作
	 * @param record
	 * @return
	 */
	public Integer delete(T record) {
		return dao.delete(record);
	}
	/**
	 * 增加操作
	 * @param record
	 * @param selective
	 * @return
	 */
	public Integer add(T record, Boolean selective) {
		if (selective) {
			return dao.insertSelective(record);
		}
		return dao.insert(record);
	}
	/**
	 * 查询内容
	 * @param record实体
	 * @return
	 */
	public List<T> select(T record){
		return dao.select(record);
	}
	/**
	 * 查询所有
	 * @return
	 */
	public List<T> selectAll(){
		return dao.selectAll();
	}
	/**
	 * 查询分页
	 * @return
	 */
	public Page<T> selectPage(){
		return (Page<T>)selectAll();
	}
}
