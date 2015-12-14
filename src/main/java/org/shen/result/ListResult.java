package org.shen.result;

import java.util.List;
/**
 * 返回到List集合结果集
 * @author Jack Shen
 * 2015年12月14日
 */
public class ListResult {
	private String code;
	private String msg;
	private List<?> data;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}
	
}
