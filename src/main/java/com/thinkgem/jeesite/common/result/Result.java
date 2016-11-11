package com.thinkgem.jeesite.common.result;

public class Result<T> {
	public static final int SUCCESS = 0;
	public static final int ERROR = 1;
	/** 响应状态 */
	private int status;
	/** 响应消息 */
	private String message;

	public Result() {
	}

	public Result(int status, String message) {
		this.set(status, message);
	}

	public void set(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
