package com.zheng.auth.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.zheng.auth.dao.IBaseDao;

/**
 * 通用数据接口实现
 *
 * @author zhenglian
 * @data 2015年12月24日 下午9:39:55
 * @param <T>
 */
public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements
		IBaseDao<T> {
	private Class<?> clazz;

	public BaseDaoImpl() {
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class<?>) pt.getActualTypeArguments()[0];

	}

	public SessionFactory getFactory() {
		return super.getSessionFactory();
	}

	@Autowired
	public void setFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	protected Session getSession() {
		return getFactory().getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T add(T t) {
		return (T) getHibernateTemplate().save(t);
	}

	@Override
	public void update(T t) {
		getHibernateTemplate().update(t);
	}

	@Override
	public void delete(Serializable id) {
		getHibernateTemplate().delete(load(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public T load(Serializable id) {
		return (T) getHibernateTemplate().load(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(Serializable id) {
		return (T) getHibernateTemplate().load(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return (List<T>) getHibernateTemplate().loadAll(clazz);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findSingleByHql(String hql) {
		Query query = getSession().createQuery(hql);
		return (T) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findListByHql(String hql) {
		Query query = getSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void executeUpdateHql(String hql) {
		Query query = getSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public void saveOrUpdate(T t) {
		getSession().saveOrUpdate(t);
	}

	@Override
	public int getMaxOrder(String psn) {
		String hql = "select max(o.orderNum) from " + clazz
				+ " o where o.psn=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, psn);
		return ((Long) query.uniqueResult()).intValue();
	}

	@Override
	public int getMaxOrder() {
		String hql = "select max(o.orderNum) from " + clazz + " o ";
		Query query = getSession().createQuery(hql);
		return ((Long) query.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T loadBySn(String sn) {
		String hql = "from " + clazz + " o where o.sn=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, sn);
		return (T) query.uniqueResult();
	}

}
