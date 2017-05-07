package org.weweb.entity.result;

import java.util.List;
/**
 * 结果工厂集
 * @author Jack Shen
 * 2015年12月14日
 */
public class ResultFactory {
	public static Result generateResult(String code,String msg,List<?> data){
		Result result=new Result();
		result.setCode(code);
		result.setMsg(msg);
		result.setData(data);
		return result;
	}
	public static Result generateResult(String code,String msg,Object data){
		Result result=new Result();
		result.setCode(code);
		result.setMsg(msg);
		result.setData(data);
		return result;
	}
	public static Result generateResult(String code,String msg){
		Result result=new Result();
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
}
