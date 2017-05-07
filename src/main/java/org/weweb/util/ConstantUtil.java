package org.weweb.util;
/**
 * 常量工具类
 * @author Jack Shen
 * 2015年12月14日
 */
public interface ConstantUtil {
	String SUCCESS_CODE="200";
	String SUCCESS_MSG="操作成功";
	String ERROR_CODE="203";
	String ERROR_MSG="操作失败";

	String LOGIN_FAIL_CODE="601";
	String LOGIN_FAIL_MSG="login fail";
	String AUTH_FAIL_CODE="602";
	String AUTH_FAIL_MSG="auth fail";

	String REQUEST_EMPTY_CODE="500";
	String REQUEST_EMPTY_MSG="请求数据为空";
	String insertLog="添加日志";
	String updateLog="更新日志";
	String deleteLog="更新日志";
	String WEWEB_TOKEN="weweb_token";

	String startPage="1";
	String endPage="10";
}
