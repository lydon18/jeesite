package com.thinkgem.jeesite.common.result;

public class ResultSingle<T> extends Result<T> {
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
