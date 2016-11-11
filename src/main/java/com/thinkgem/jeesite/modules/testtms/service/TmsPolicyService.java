/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.testtms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.testtms.entity.TmsPolicy;
import com.thinkgem.jeesite.modules.testtms.dao.TmsPolicyDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2016-11-01
 */
@Service
@Transactional(readOnly = true)
public class TmsPolicyService extends CrudService<TmsPolicyDao, TmsPolicy> {

	public TmsPolicy get(String id) {
		return super.get(id);
	}
	
	public List<TmsPolicy> findList(TmsPolicy tmsPolicy) {
		return super.findList(tmsPolicy);
	}
	
	public Page<TmsPolicy> findPage(Page<TmsPolicy> page, TmsPolicy tmsPolicy) {
		return super.findPage(page, tmsPolicy);
	}
	
	@Transactional(readOnly = false)
	public void save(TmsPolicy tmsPolicy) {
		super.save(tmsPolicy);
	}
	
	@Transactional(readOnly = false)
	public void delete(TmsPolicy tmsPolicy) {
		super.delete(tmsPolicy);
	}
	
}