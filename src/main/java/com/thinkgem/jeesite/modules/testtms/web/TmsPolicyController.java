/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.testtms.web;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.result.Result;
import com.thinkgem.jeesite.common.result.ResultMany;
import com.thinkgem.jeesite.common.result.ResultPage;
import com.thinkgem.jeesite.common.result.ResultSingle;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.testtms.dto.TmsPolicyDto;
import com.thinkgem.jeesite.modules.testtms.entity.TmsPolicy;
import com.thinkgem.jeesite.modules.testtms.service.TmsPolicyService;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2016-11-01
 */
@Controller
@RequestMapping(value = "${adminPath}/")
public class TmsPolicyController extends BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(TmsPolicyController.class);

	@Autowired
	private TmsPolicyService tmsPolicyService;
	
	@ModelAttribute
	public TmsPolicy get(@RequestParam(required=false) String id) {
		TmsPolicy entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tmsPolicyService.get(id);
		}
		if (entity == null){
			entity = new TmsPolicy();
		}
		return entity;
	}
	
	//	@RequiresPermissions("testtms:tmsPolicy:edit")
	@RequestMapping(value = "tmsPolicys",method = RequestMethod.POST)
	@ResponseBody
	public ResultSingle<TmsPolicyDto> save(@RequestBody TmsPolicy tmsPolicy, Model model) {
		ResultSingle<TmsPolicyDto> result = new ResultSingle<TmsPolicyDto>();
		TmsPolicyDto tmsPolicyDto = new TmsPolicyDto();
		if (!beanValidator(model, tmsPolicy)){
			result.set(Result.ERROR ,"数据不合法");
			return result;
		}
		tmsPolicyService.save(tmsPolicy);
//		addMessage(redirectAttributes, "保存单表成功");
		TmsPolicy tmsPolicy2 = tmsPolicyService.get(tmsPolicy.getId());
		if(null == tmsPolicy2){
			result.set(Result.ERROR , "数据保存失败");
			if(log.isDebugEnabled()){
				log.debug("根据id="+tmsPolicy.getId()+"未查询到实体");
			}
			return result;
		}
		try {
			BeanUtils.copyProperties(tmsPolicyDto, tmsPolicy2);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			result.set(Result.ERROR, e.toString());
			return result;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			result.set(Result.ERROR, e.toString());
			return result;
		}
		result.setStatus(Result.SUCCESS);
		result.setData(tmsPolicyDto);
		log.debug(JsonMapper.toJsonString(result));
		return result;
	}
	
//	@RequiresPermissions("testtms:tmsPolicy:edit")
	@RequestMapping(value = "tmsPolicys/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public ResultSingle<TmsPolicyDto> delete(@PathVariable("id") String id ) {
		ResultSingle<TmsPolicyDto> result = new ResultSingle<TmsPolicyDto>();
		TmsPolicy tmsPolicy = tmsPolicyService.get(id);
		if(null == tmsPolicy){
			result.set(Result.ERROR, "删除失败");
			return result;
		}
		tmsPolicyService.delete(tmsPolicy);
//		addMessage(redirectAttributes, "删除单表成功");
		result.set(Result.SUCCESS, "删除成功");
		return result;
	}
	
//	@RequiresPermissions("testtms:tmsPolicy:view")
//	@RequestMapping(value = ("tmsPolicys"),method = RequestMethod.GET)
	@ResponseBody
	public ResultMany<TmsPolicyDto> list(TmsPolicy tmsPolicy) {
		ResultMany<TmsPolicyDto> resultMany = new ResultMany<TmsPolicyDto>();
		List<TmsPolicyDto> list = new ArrayList<TmsPolicyDto>();
		List<TmsPolicy> findList = tmsPolicyService.findList(tmsPolicy);
		for (TmsPolicy tmsPolicy2 : findList) {
			TmsPolicyDto tmsPolicyDto = new TmsPolicyDto();
			try {
				BeanUtils.copyProperties(tmsPolicyDto, tmsPolicy2);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				resultMany.set(Result.ERROR, e.toString());
				return resultMany;
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				resultMany.set(Result.ERROR, e.toString());
				return resultMany;
			}
			list.add(tmsPolicyDto);
		}
		resultMany.setStatus(Result.SUCCESS);
		resultMany.setData(list);
		return resultMany;
	}
//	@RequiresPermissions("testtms:tmsPolicy:view")
	@RequestMapping(value = ("tmsPolicys"),method = RequestMethod.GET)
	@ResponseBody
	public ResultPage<TmsPolicyDto> listPage( TmsPolicy tmsPolicy,HttpServletRequest request, HttpServletResponse response) {
		ResultPage<TmsPolicyDto> result = new ResultPage<TmsPolicyDto>();
		com.thinkgem.jeesite.common.result.Page<TmsPolicyDto> page1 = new com.thinkgem.jeesite.common.result.Page<TmsPolicyDto>();
		List<TmsPolicyDto> list = new ArrayList<TmsPolicyDto>();
		Page<TmsPolicy> page = tmsPolicyService.findPage(new Page<TmsPolicy>(request, response), tmsPolicy);
		List<TmsPolicy> findList = page.getList();
		try {
			for (TmsPolicy tmsPolicy2 : findList) {
				TmsPolicyDto tmsPolicyDto = new TmsPolicyDto();
				BeanUtils.copyProperties(tmsPolicyDto, tmsPolicy2);
				list.add(tmsPolicyDto);
			}
			
			BeanUtils.copyProperties(page1, page);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			result.set(Result.ERROR, e.toString());
			return result;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			result.set(Result.ERROR, e.toString());
			return result;
		}
//		page1.setPageNo(page.getPageNo());
//		page1.setPageSize(page.getPageSize());
//		page1.setLast(page.getLast());
//		page1.setCount(page.getCount());
		page1.setList(list);
		result.setStatus(Result.SUCCESS);
		result.setData(page1);
		return result;
	}

//	@RequiresPermissions("testtms:tmsPolicy:view")
	@RequestMapping(value = "tmsPolicys/{id}",method = RequestMethod.GET)
	@ResponseBody
	public ResultSingle<TmsPolicyDto> form(@PathVariable("id") String id) {
		ResultSingle<TmsPolicyDto> result = new ResultSingle<TmsPolicyDto>();
		TmsPolicy tmsPolicy = get(id);
		TmsPolicyDto tmsPolicyDto = new TmsPolicyDto();
		try {
			BeanUtils.copyProperties(tmsPolicyDto, tmsPolicy);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			result.set(Result.ERROR, e.toString());
			return result;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			result.set(Result.ERROR, e.toString());
			return result;
		}
		result.setStatus(Result.SUCCESS);
		result.setData(tmsPolicyDto);
		return result;
	}


	
	

}