package com.zheng.auth.service;

import java.util.List;

import com.zheng.auth.domain.ControllerOper;
import com.zheng.auth.domain.ControllerResource;

public interface IControllerResService extends IBaseService<ControllerResource>{
	/**
	 * 将资源添加到父节点上
	 * 
	 * @auther zhenglian
	 * @date 2015年12月25日 下午4:14:52
	 * 
	 * @param cr
	 * @param psn
	 */
	public void addResource(ControllerResource cr, String psn);

	/**
	 * 遍历父节点下的所有子资源
	 * 
	 * @auther zhenglian
	 * @date 2015年12月25日 下午4:15:50
	 * 
	 * @param pid
	 * @return
	 */
	public List<ControllerResource> listByParent(Integer pid);

	/**
	 * 将指定操作与controllerResource关联
	 * 
	 * @auther zhenglian
	 * @date 2015年12月25日 下午4:18:39
	 * 
	 * @param oper
	 * @param rsn
	 */
	public void addOper(ControllerOper oper, String rsn);

	/**
	 * 更新某一个操作
	 * 
	 * @auther zhenglian
	 * @date 2015年12月25日 下午4:19:11
	 * 
	 * @param oper
	 */
	public void updateOper(ControllerOper oper);

	/**
	 * 删除某一个操作
	 * 
	 * @auther zhenglian
	 * @date 2015年12月25日 下午4:19:29
	 * 
	 * @param operId
	 */
	public void deleteOper(Integer operId);

	/**
	 * 根据某一个controllerResource查询该controller所包含的所有操作
	 * 
	 * @auther zhenglian
	 * @date 2015年12月25日 下午4:20:08
	 * 
	 * @param rid
	 * @return
	 */
	public List<ControllerOper> listOperByRes(Integer rid);

	/**
	 * 根据id加载某一个操作
	 * 
	 * @auther zhenglian
	 * @date 2015年12月25日 下午4:21:01
	 * 
	 * @param operId
	 * @return
	 */
	public ControllerOper loadOperById(Integer operId);

	/**
	 * 根据sn查询某一个操作
	 * 
	 * @auther zhenglian
	 * @date 2015年12月25日 下午4:21:25
	 * 
	 * @param sn
	 * @return
	 */
	public ControllerOper loadOperBySn(String sn);
}
