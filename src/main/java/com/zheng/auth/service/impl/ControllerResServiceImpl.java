package com.zheng.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zheng.auth.dao.IBaseDao;
import com.zheng.auth.dao.IControllerResDao;
import com.zheng.auth.domain.ControllerOper;
import com.zheng.auth.domain.ControllerResource;
import com.zheng.auth.service.IControllerResService;

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

}
