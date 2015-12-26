package com.zheng.auth.service.impl;

import java.io.Serializable;
import java.util.List;

import com.zheng.auth.dao.IBaseDao;
import com.zheng.auth.dto.ObjectQuery;
import com.zheng.auth.dto.Pager;
import com.zheng.auth.service.IBaseService;

public abstract class BaseServiceImpl<T> implements IBaseService<T> {

	protected abstract IBaseDao<T> getBaseDao();
	
	@Override
	public T save(T t) {
		return getBaseDao().save(t);
	}

	@Override
	public void update(T t) {
		getBaseDao().update(t);		
	}

	@Override
	public void delete(Serializable id) {
		getBaseDao().delete(id);
	}

	@Override
	public T findById(Serializable id) {
		return getBaseDao().findById(id);
	}

	@Override
	public T findSingleByHql(String hql, Object... params) {
		return getBaseDao().findSingleByHql(hql, params);
	}

	@Override
	public List<T> findListByHql(String hql, Object... params) {
		return getBaseDao().findListByHql(hql, params);
	}

	@Override
	public void executeUpdateHql(String hql, Object... params) {
		getBaseDao().executeUpdateHql(hql, params);
	}

	@Override
	public T findSingleByQuery(ObjectQuery query) {
		return getBaseDao().findSingleByQuery(query);
	}

	@Override
	public List<T> findListByQuery(ObjectQuery query) {
		return getBaseDao().findListByQuery(query);
	}

	@Override
	public Pager<T> findPagerByQuery(ObjectQuery query) {
		return getBaseDao().findPagerByQuery(query);
	}

	@Override
	public int getCount(ObjectQuery query) {
		return getBaseDao().getCount(query);
	}

	@Override
	public int getCount() {
		return getBaseDao().getCount();
	}

}
