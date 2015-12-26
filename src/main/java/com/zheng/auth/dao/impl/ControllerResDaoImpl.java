package com.zheng.auth.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.zheng.auth.dao.IControllerResDao;
import com.zheng.auth.domain.ControllerOper;
import com.zheng.auth.domain.ControllerResource;

@Repository
public class ControllerResDaoImpl extends BaseDaoImpl<ControllerResource> implements IControllerResDao {

	private String getSelectHql() {
		String hql = "select new ControllerResource(cr.id, cr.name,cr.sn,cr.psn, cr.classname, cr.orderNum)";
		return hql;
	}
	
	@Override
	public void addResource(ControllerResource cr, String psn) {
		ControllerResource parent = null;
		if(!StringUtils.isBlank(psn)) {
			parent = loadBySn(psn, ControllerResource.class);
			if(parent == null) {
				throw new RuntimeException("资源父节点不存在!");
			}
			
			cr.setPsn(psn);
			cr.setParent(parent);
		}
		
		ControllerResource self = loadBySn(cr.getSn(), ControllerResource.class);
		if(self == null) { //是新增
			if(cr.getOrderNum() <= 0) { //没有设置排序号
				if(parent == null) { //没有父节点
					cr.setOrderNum(getMaxOrder(null, ControllerResource.class) + 1);
				}else {
					cr.setOrderNum(getMaxOrder(parent.getId(), ControllerResource.class) + 1);
				}
			}
			
			self = cr;
		}else { //修改
			self.setClassname(cr.getClassname());
			self.setName(cr.getName());
			if(cr.getOrderNum() > 0) {
				self.setOrderNum(cr.getOrderNum());
			}
			self.setParent(cr.getParent());
			self.setPsn(cr.getPsn());
		}
		
		getSession().saveOrUpdate(self);
	}

	@Override
	public List<ControllerResource> listByParent(Integer pid) {
		String hql = null;
		if(pid == null) {
			hql = getSelectHql() + " from ControllerResource o where o.parent.id is null order by orderNum asc";
		}else {
			hql = getSelectHql() + " from ControllerResource o where o.parent.id=" + pid + " order by orderNum asc";
		}
		
		return findListByHql(hql, new Object[] {});
	}

	@Override
	public void addOper(ControllerOper oper, String rsn) {
		if(StringUtils.isBlank(rsn)) {
			throw new RuntimeException("controllerresource资源对象不存在!");
		}
		
		ControllerResource parent = loadBySn(rsn, ControllerResource.class);
		if(parent == null) {
			throw new RuntimeException("controllerresource资源对象不存在!");
		}
		
		ControllerOper dbOper = loadOperBySn(oper.getSn());
		if(dbOper == null) { //新增
			dbOper = oper;
		}else { //修改
			dbOper.setIndexRes(oper.getIndexRes());
			dbOper.setMethodName(oper.getMethodName());
			dbOper.setName(oper.getName());
			dbOper.setSn(oper.getSn());
		}
		dbOper.setRsn(parent.getSn());
		dbOper.setRid(parent.getId());
		
		getSession().saveOrUpdate(dbOper);
	}

	@Override
	public void updateOper(ControllerOper oper) {
		getSession().update(oper);
	}

	@Override
	public void deleteOper(Integer operId) {
		getSession().delete(loadOperById(operId));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ControllerOper> listOperByRes(Integer rid) {
		if(rid == null || rid <= 0) {
			throw new RuntimeException("缺少controllerresource父对象!");
		}
		
		String hql = "from " + ControllerOper.class.getSimpleName() + " o where rid=?";
		List<ControllerOper> opers = (List<ControllerOper>) listObj(hql, rid);
		return opers;
	}

	@Override
	public ControllerOper loadOperById(Integer operId) {
		return (ControllerOper) loadObj(operId, ControllerOper.class);
	}

	@Override
	public ControllerOper loadOperBySn(String sn) {
		String hql = "select o from " + ControllerOper.class.getSimpleName() + " o where sn=?";
		return (ControllerOper) loadObjByHql(hql, sn);
	}

}
