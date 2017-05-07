package org.weweb.service;

import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
//http://www.tuicool.com/articles/bIRfUv(学习参考)
public abstract class BaseService<T,Dao extends Mapper<T>> {
	@Autowired
	protected Dao dao;
	/**
	 * 更新操作
	 * @param clazz
	 * @param selective
	 * @return 
	 */
	public Integer update(T clazz, Boolean selective) {
		if (selective) {
			return dao.updateByPrimaryKeySelective(clazz);
		}
		return dao.updateByPrimaryKey(clazz);
	}

	/**
	 * 通过ID来查询实体对象
	 * @param id
	 * @return
	 */
	public T get(long id){

		return dao.selectByPrimaryKey(id);
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
	 * @param record 实体
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
