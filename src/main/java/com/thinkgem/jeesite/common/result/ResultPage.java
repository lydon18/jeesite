package com.thinkgem.jeesite.common.result;


public class ResultPage<T> extends Result<T>{
	private Page<T> data;

	public Page<T> getData() {
		return data;
	}

	public void setData(Page<T> data) {
		this.data = data;
	}
	
	
}
