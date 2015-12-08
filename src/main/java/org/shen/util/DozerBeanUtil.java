package org.shen.util;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.dozer.MappingException;
/**
 * 通过依赖注入Dozer工具
 * @author shen
 */
public class DozerBeanUtil {
	private static Mapper mapper;
	/**
	 * 对象转换
	 * @param source
	 * @param destinationClass
	 * @return
	 * @throws MappingException
	 */
	public static <T> T map(Object source, Class<T> destinationClass)
			throws MappingException {
		return mapper.map(source, destinationClass);
	}
	/**
	 * List集合对象转换
	 * @param sourceList 原List集合
	 * @param destinationClass 目的对象
	 * @return
	 */
	public static <T> List<T> mapList(List<?> sourceList,
			Class<T> destinationClass) {
		List<T> destinationList=new ArrayList<T>();
		for(Object source:sourceList){
			destinationList.add(map(source, destinationClass));
		}
		return destinationList;
	}
	public Mapper getMapper() {
		return mapper;
	}
	public void setMapper(Mapper mapper) {
		DozerBeanUtil.mapper = mapper;
	}

}
