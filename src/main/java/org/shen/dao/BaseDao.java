package org.shen.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.shen.entity.BaseEntity;

import tk.mybatis.mapper.common.Mapper;

public abstract class BaseDao<T extends BaseEntity> implements Mapper<T>{
	/**
	 * 定义一个类反射对象，可以通过这个对象反射调用成员和方法，构造函数
	 */
	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	public BaseDao() {
		// 获取传递父类的泛型参数类型，但你可以理解成一个集合
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		// 从集合中获取所有泛型参数类型，Type是Class的接口类
		Type[] types = type.getActualTypeArguments();
		// 得到传递的类类型（这里只有一个泛型）
		clazz = (Class<T>) types[0];
	}

}
