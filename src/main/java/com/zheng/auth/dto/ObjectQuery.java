package com.zheng.auth.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用查询条件对象
 *
 * @author zhenglian
 * @time 2015年12月25日 上午11:46:52
 */
public abstract class ObjectQuery {
	private Integer pageNo = 1; // 当前页
	private Integer pageSize = 10; // 一页记录数

	private List<Object> params = new ArrayList<>(); // 查询条件列表
	private StringBuilder builder = new StringBuilder(); // 查询语句
	private boolean flag = false; // 查询条件是否已经加入到sql中

	/**
	 * 具体条件需要根据不同实体对象而定
	 * 
	 * @auther zhenglian
	 * @date 2015年12月25日 上午11:43:56
	 * 
	 * @param where
	 * @param param
	 */
	protected abstract void addWhere();

	public void addWhere(String where, Object param) {
		this.builder.append(" and ").append(where);
		this.params.add(param);
	}

	/**
	 * 获取拼接的查询条件sql
	 * 
	 * @auther zhenglian
	 * @date 2015年12月25日 上午11:46:32
	 * 
	 * @return
	 */
	public String getWhere() {
		if (!flag) {
			addWhere();
		}
		flag = true;
		return builder.toString();
	}
	
	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<Object> getParams() {
		return params;
	}

}
