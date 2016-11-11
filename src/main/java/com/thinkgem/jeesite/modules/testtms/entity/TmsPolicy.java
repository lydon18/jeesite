/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.testtms.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 单表生成Entity
 * @author ThinkGem
 * @version 2016-11-01
 */
public class TmsPolicy extends DataEntity<TmsPolicy> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 策略名称
	
	public TmsPolicy() {
		super();
	}

	public TmsPolicy(String id){
		super(id);
	}

	@Length(min=0, max=64, message="策略名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}