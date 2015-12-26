package com.zheng.auth.dto;

import java.util.List;

/**
 * 分页对象
 *
 * @author zhenglian
 * @time 2015年12月25日 上午11:29:52
 */
public class Pager<T> {
	private Integer pageNo; // 当前页
	private Integer pageSize; // 一页显示的行数
	private Integer totalPages; // 总共页面数
	private Integer totalRows; // 总共记录数
	private List<T> rows; // 当前页查询出的数据

	public Pager() {

	}

	public Pager(Integer pageNo, Integer pageSize, Integer totalRows) {
		// 需要容错处理
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalRows = totalRows;

		this.pageSize = this.pageSize < 1 ? 1 : this.pageSize;
		this.pageNo = this.pageNo < 1 ? 1 : this.pageNo;
		this.totalPages = (this.pageSize + this.totalRows - 1) / this.pageSize;
		this.pageNo = this.pageNo > this.totalPages ? this.totalPages
				: this.pageNo;

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

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "Pager [pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", totalPages=" + totalPages + ", totalRows=" + totalRows
				+ "]";
	}

}
