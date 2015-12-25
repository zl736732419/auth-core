package com.zheng.auth.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.zheng.auth.dao.IBaseDao;
import com.zheng.auth.dto.ObjectQuery;
import com.zheng.auth.dto.Pager;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

	private Class<?> clazz;

	public BaseDaoImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class<?>) type.getActualTypeArguments()[0];
	}

	public SessionFactory getFactory() {
		return super.getSessionFactory();
	}

	@Autowired
	public void setFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public Session getSession() {
		return getFactory().getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T save(T t) {

		return (T) getHibernateTemplate().save(t);
	}

	@Override
	public void update(T t) {
		getHibernateTemplate().update(t);
	}

	@Override
	public void delete(Serializable id) {
		getHibernateTemplate().delete(findById(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(Serializable id) {
		return (T) getHibernateTemplate().load(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findSingleByHql(String hql, Object... params) {
		Query query = getSession().createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return (T) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findListByHql(String hql, Object... params) {
		Query query = getSession().createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}

		return query.list();
	}

	@Override
	public void executeUpdateHql(String hql, Object... params) {
		Query query = getSession().createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}

		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findSingleByQuery(ObjectQuery query) {
		String hql = " from " + clazz.getSimpleName() + " o where 1=1 ";
		if (query != null && !StringUtils.isBlank(query.getWhere())) {
			hql += query.getWhere();
		}
		Query q = getSession().createQuery(hql);
		if (!query.getParams().isEmpty()) {
			for (int i = 0; i < query.getParams().size(); i++) {
				Object param = query.getParams().get(i);
				q.setParameter(i, param);
			}
		}
		return (T) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findListByQuery(ObjectQuery query) {
		String hql = " from " + clazz.getSimpleName() + " o where 1=1 ";
		if (query != null && !StringUtils.isBlank(query.getWhere())) {
			hql += query.getWhere();
		}
		
		Query q = getSession().createQuery(hql);
		if (!query.getParams().isEmpty()) {
			for (int i = 0; i < query.getParams().size(); i++) {
				Object param = query.getParams().get(i);
				q.setParameter(i, param);
			}
		}

		// 设置分页
		int start = (query.getPageNo() - 1) * query.getPageSize();

		q.setFirstResult(start);
		q.setMaxResults(query.getPageSize());
		
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<T> findPagerByQuery(ObjectQuery query) {
		int totalRows = getCount(query);
		Pager<T> pager = new Pager<T>(query.getPageNo(), query.getPageSize(), totalRows);
		
		String hql = "from " + clazz.getSimpleName() + " o where 1=1 ";
		if (query != null && !StringUtils.isBlank(query.getWhere())) {
			hql += query.getWhere();
		}
		Query q = getSession().createQuery(hql);
		if (!query.getParams().isEmpty()) {
			for (int i = 0; i < query.getParams().size(); i++) {
				Object param = query.getParams().get(i);
				q.setParameter(i, param);
			}
		}
		
		// 设置分页
		int start = (pager.getPageNo() - 1) * pager.getPageSize();
		q.setFirstResult(start);
		q.setMaxResults(pager.getPageSize());
		
		List<T> list = q.list();
		pager.setRows(list);
		
		return pager;
	}

	@Override
	public int getCount(ObjectQuery query) {
		String hql = "select count(o) from " + clazz.getSimpleName() + " o where 1=1 ";
		if(query != null && !StringUtils.isBlank(query.getWhere())) {
			hql += query.getWhere();
		}
		Query q = getSession().createQuery(hql);
		if(!query.getParams().isEmpty()) {
			for (int i = 0; i < query.getParams().size(); i++) {
				Object param = query.getParams().get(i);
				q.setParameter(i, param);
			}
		}
		
		return ((Long) q.uniqueResult()).intValue();
	}

	@Override
	public int getCount() {
		return getCount(null);
	}

}
