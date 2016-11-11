package com.thinkgem.jeesite.common.result;

import java.util.List;

public class ResultMany<T> extends Result<T>{
	private List<T> data;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
}
