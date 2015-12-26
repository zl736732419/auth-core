package com.zheng.auth.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 公共数据接口，定义通用的一些操作
 *
 * @author zhenglian
 * @data 2015年12月24日 下午9:37:04
 * @param <T>
 */
public interface IBaseDao<T> {

	/**
	 * 添加对象
	 *
	 * @author zhenglian
	 * @data 2015年12月24日 下午9:37:41
	 * @param t
	 * @return
	 */
	public T add(T t);

	/**
	 * 更新对象
	 *
	 * @author zhenglian
	 * @data 2015年12月24日 下午9:37:56
	 * @param t
	 */
	public void update(T t);

	/**
	 * 删除对象
	 *
	 * @author zhenglian
	 * @data 2015年12月24日 下午9:38:20
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * 根据id加载对象
	 *
	 * @author zhenglian
	 * @data 2015年12月24日 下午9:38:39
	 * @param id
	 * @return
	 */
	public T load(Serializable id);
	
	/**
	 * 根据id查找对象
	 *
	 * @author zhenglian
	 * @data 2015年12月24日 下午9:55:37
	 * @param id
	 * @return
	 */
	public T findById(Serializable id);
	
	/**
	 * 查询所有对象
	 *
	 * @author zhenglian
	 * @data 2015年12月24日 下午9:55:59
	 * @return
	 */
	public List<T> findAll();
	
	/**
	 * 根据指定hql加载单一对象
	 *
	 * @author zhenglian
	 * @data 2015年12月24日 下午9:56:25
	 * @param hql
	 * @return
	 */
	public T findSingleByHql(String hql);
	
	/**
	 * 根据hql加载多条数据
	 *
	 * @author zhenglian
	 * @data 2015年12月24日 下午9:57:33
	 * @param hql
	 * @return
	 */
	public List<T> findListByHql(String hql);
	
	/**
	 * 执行指定的hql语句，没有返回值
	 *
	 * @author zhenglian
	 * @data 2015年12月24日 下午9:57:45
	 * @param hql
	 */
	public void executeUpdateHql(String hql);
	
	/**
	 * 保存或者更新对象
	 *
	 * @author zhenglian
	 * @data 2015年12月24日 下午10:02:06
	 * @param t
	 */
	public void saveOrUpdate(T t);
	
	/**
	 * 获取父节点下最大排序号
	 *
	 * @author zhenglian
	 * @data 2015年12月24日 下午10:10:25
	 * @param psn
	 * @return
	 */
	public int getMaxOrder(String psn);
	
	/**
	 * 获取同级最大排序号，没有父节点
	 *
	 * @author zhenglian
	 * @data 2015年12月24日 下午10:10:53
	 * @return
	 */
	public int getMaxOrder();
	
	/**
	 * 根据sn查找对象
	 *
	 * @author zhenglian
	 * @data 2015年12月24日 下午10:11:29
	 * @param sn
	 * @return
	 */
	public T loadBySn(String sn);
	
	
	
	
}
