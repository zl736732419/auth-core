package com.zheng.auth.service;

import java.io.Serializable;
import java.util.List;

import com.zheng.auth.dto.ObjectQuery;
import com.zheng.auth.dto.Pager;

/**
 * 通用业务层接口
 *
 * @author zhenglian
 * @time 2015年12月25日 下午12:18:39
 * @param <T>
 */
public interface IBaseService<T> {
	/**
	 * 保存对象
	 * 
	 * @auther zhenglian
	 * @date 2015年12月25日 上午11:25:30
	 * 
	 * @param t
	 * @return
	 */
	public T save(T t);

	/**
	 * 更新对象
	 * 
	 * @auther zhenglian
	 * @date 2015年12月25日 上午11:25:46
	 * 
	 * @param t
	 */
	public void update(T t);

	/**
	 * 删除指定id的对象
	 * 
	 * @auther zhenglian
	 * @date 2015年12月25日 上午11:26:03
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * 根据id获取对象
	 * 
	 * @auther zhenglian
	 * @date 2015年12月25日 上午11:26:36
	 * 
	 * @param id
	 * @return
	 */
	public T findById(Serializable id);

	/**
	 * 根据hql查询单一结果对象
	 * 
	 * @auther zhenglian
	 * @date 2015年12月25日 上午11:26:54
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public T findSingleByHql(String hql, Object... params);

	/**
	 * 根据指定hql查询对象列表
	 * 
	 * @auther zhenglian
	 * @date 2015年12月25日 上午11:28:02
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<T> findListByHql(String hql, Object... params);

	/**
	 * 执行更新操作
	 * 
	 * @auther zhenglian
	 * @date 2015年12月25日 上午11:28:56
	 * 
	 * @param hql
	 * @param params
	 */
	public void executeUpdateHql(String hql, Object... params);

	/**
	 * 根据查询条件获取单个对象
	 * 
	 * @auther zhenglian
	 * @date 2015年12月25日 上午11:49:22
	 * 
	 * @param query
	 * @return
	 */
	public T findSingleByQuery(ObjectQuery query);

	/**
	 * 根据查询条件获取列表数据
	 * 
	 * @auther zhenglian
	 * @date 2015年12月25日 上午11:50:07
	 * 
	 * @param query
	 * @return
	 */
	public List<T> findListByQuery(ObjectQuery query);

	/**
	 * 根据查询条件获取一页的数据
	 * 
	 * @auther zhenglian
	 * @date 2015年12月25日 上午11:50:32
	 * 
	 * @param query
	 * @return
	 */
	public Pager<T> findPagerByQuery(ObjectQuery query);

	/**
	 * 根据查询条件获取记录数
	 * 
	 * @auther zhenglian
	 * @date 2015年12月25日 上午11:51:17
	 * 
	 * @param query
	 * @return
	 */
	public int getCount(ObjectQuery query);

	/**
	 * 获取所有记录条数
	 * 
	 * @auther zhenglian
	 * @date 2015年12月25日 上午11:51:46
	 * 
	 * @return
	 */
	public int getCount();
}
