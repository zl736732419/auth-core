package com.zheng.auth.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zheng.auth.annotation.AuthOper;
import com.zheng.auth.annotation.Res;
import com.zheng.auth.dao.IBaseDao;
import com.zheng.auth.dao.IControllerResDao;
import com.zheng.auth.domain.ControllerOper;
import com.zheng.auth.domain.ControllerResource;
import com.zheng.auth.service.IControllerResService;
import com.zheng.auth.util.BasicSysKit;

@Service
public class ControllerResServiceImpl extends
		BaseServiceImpl<ControllerResource> implements IControllerResService {

	@Autowired
	private IControllerResDao dao;

	@Override
	protected IBaseDao<ControllerResource> getBaseDao() {
		return dao;
	}

	@Override
	public void addResource(ControllerResource cr, String psn) {
		dao.addResource(cr, psn);
	}

	@Override
	public List<ControllerResource> listByParent(Integer pid) {
		return dao.listByParent(pid);
	}

	@Override
	public void addOper(ControllerOper oper, String rsn) {
		dao.addOper(oper, rsn);
	}

	@Override
	public void updateOper(ControllerOper oper) {
		dao.updateOper(oper);
	}

	@Override
	public void deleteOper(Integer operId) {
		dao.deleteOper(operId);
	}

	@Override
	public List<ControllerOper> listOperByRes(Integer rid) {
		return dao.listOperByRes(rid);
	}

	@Override
	public ControllerOper loadOperById(Integer operId) {
		return dao.loadOperById(operId);
	}

	@Override
	public ControllerOper loadOperBySn(String sn) {
		return dao.loadOperBySn(sn);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void initControllerRes(String[] packages) {
		List<Class> clazs = new ArrayList<>();
		List<Class> tmpClazs = null;
		for(String pkg : packages) {
			tmpClazs = BasicSysKit.listByClassAnnotation(pkg, Res.class);
			BasicSysKit.mergeList(clazs, tmpClazs);
		}
		
		//添加到数据库
		for(Class c : clazs) {
			ControllerResource cr = addResource(c);
			addAuthOper(c, cr);
		}
	}

	/**
	 * 添加controller中的操作资源到数据库
	 *
	 * @author zhenglian
	 * @data 2015年12月26日 下午2:14:02
	 * @param c
	 * @param cr
	 */
	private void addAuthOper(Class c, ControllerResource cr) {
		Method[] methods = c.getDeclaredMethods();
		for(Method m : methods) {
			if(m.isAnnotationPresent(AuthOper.class)) {
				addControllerOper(m, cr);
			}
		}
	}

	private void addControllerOper(Method m, ControllerResource cr) {
		String methodName  =m.getName();
		AuthOper ao = m.getAnnotation(AuthOper.class);
		String sn = ao.sn();
		ControllerOper co = loadOperBySn(sn);
		if(co == null) {
			co = new ControllerOper();
		}
		
		co.setMethodName(methodName);
		co.setSn(getOperSn(methodName, ao));
		co.setName(getOperName(methodName, ao));
		co.setIndexRes(getOperIndex(methodName, ao));
		addOper(co, cr.getSn());
		
	}

	/**
	 * 获取操作的sn
	 *
	 * @author zhenglian
	 * @data 2015年12月26日 下午2:43:46
	 * @param m
	 * @param ao
	 * @return
	 */
	private String getOperSn(String methodName, AuthOper ao) {
		if(!StringUtils.isBlank(ao.sn())) {
			return ao.sn();
		}
		if(methodName.startsWith("add")) return "ADD";
		else if(methodName.startsWith("update")) return "UPDATE";
		else if(methodName.startsWith("delete")) return "DELETE";
		else return "READ";
	}

	/**
	 * 获取操作方法的权限位置
	 *
	 * @author zhenglian
	 * @data 2015年12月26日 下午2:22:49
	 * @param methodName
	 * @param ao
	 * @return
	 */
	private Integer getOperIndex(String methodName, AuthOper ao) {
		if(ao.index() >= 0) {
			return ao.index();
		}
		
		if(methodName.startsWith("add")){
			return 0;
		}else if(methodName.startsWith("update")) {
			return 2;
		}else if(methodName.startsWith("delete")) { 
			return 3;
		}else return 1;
	}

	/**
	 * 获取操作方法的名称
	 *
	 * @author zhenglian
	 * @data 2015年12月26日 下午2:22:34
	 * @param methodName
	 * @param ao
	 * @return
	 */
	private String getOperName(String methodName, AuthOper ao) {
		if(!StringUtils.isBlank(ao.name())) {
			return ao.name();
		}
		
		if(methodName.startsWith("add")) {
			return "添加";
		}else if(methodName.startsWith("update")) {
			return "更新";
		}else if(methodName.startsWith("delete")) {
			return "删除";
		}else {
			return "查询";
		}
			
	}

	/**
	 * 将当前controller资源添加到数据库
	 *
	 * @author zhenglian
	 * @data 2015年12月26日 下午2:09:48
	 * @param c
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private ControllerResource addResource(Class c) {
		Res res = (Res)c.getAnnotation(Res.class);
		String sn = res.sn();
		ControllerResource cr = dao.loadBySn(sn, ControllerResource.class);
		if(cr == null) {
			cr = new ControllerResource();
		}
		cr.setClassname(c.getName());
		cr.setName(res.name());
		cr.setOrderNum(res.orderNum());
		cr.setSn(sn);
		addResource(cr, res.psn());
		return cr;
	}

}
