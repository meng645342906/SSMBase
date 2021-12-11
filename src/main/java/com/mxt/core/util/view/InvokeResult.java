package com.mxt.core.util.view;

/**
 * 门面调用封装
 *
 */
public class InvokeResult {

	private Object data;

	private String msg = "";

	private int code;

	public static InvokeResult success(Object data) {
		InvokeResult result = new InvokeResult();
		result.data = data;
		result.code = 0;
		return result;
	}

	public static InvokeResult success() {
		InvokeResult result = new InvokeResult();
		result.code = 0;
		return result;
	}

	public static InvokeResult failure(String msg) {
		return failure(-1, msg);
	}

	public static InvokeResult failure(int code, String msg) {
		InvokeResult result = new InvokeResult();
		result.msg = msg;
		result.code = code;
		return result;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public Object getData() {
		return data;
	}

	public String getMsg() {
		return msg;
	}

	public int getCode() {
		return code;
	}
}
