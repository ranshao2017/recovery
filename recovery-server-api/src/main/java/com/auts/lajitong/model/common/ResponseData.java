package com.auts.lajitong.model.common;

/**
 * 响应基类
 * @author zqh
 */
public class ResponseData {
	public int err_code = 0;
    public String err_msg="请求成功";
	private Object data;
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public int getErr_code() {
		return err_code;
	}
	public void setErr_code(int err_code) {
		this.err_code = err_code;
	}
	public String getErr_msg() {
		return err_msg;
	}
	public void setErr_msg(String err_msg) {
		this.err_msg = err_msg;
	}
	
}
