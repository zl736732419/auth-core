package com.zheng.auth.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 权限控制的关键类，用于控制权限与主体资源之间的关系， 用来确认主体更够对那些资源进行那些操作
 *
 * @author zhenglian
 * @data 2015年12月16日 下午10:20:21
 */
@Entity
@Table(name = "t_acl")
public class ACL {

	@Id
	@GeneratedValue
	private Long id;

	/**
	 * 主体类型
	 */
	private String ptype;
	/**
	 * 资源类型
	 */
	private String rtype;
	/**
	 * 主体id
	 */
	private Long pId;
	/**
	 * 资源id
	 */
	private Long rId;
	
	/**
	 * 对方法的操作，存储的是一个4个字节的整数，其实就是0-31位的操作
	 * 0000....1011
	 * ........CRUD操作
	 * 在数据库中存储的是这些字节组成的整数值
	 */
	private Integer aclState; 

	public void setControllerType() {
		this.rtype = ControllerResource.RES_TYPE;
	}

	public void setMenuType() {
		this.rtype = MenuResource.RES_TYPE;
	}
	
	public void setUserType() {
		this.ptype = User.PRINCIPAL_TYPE;
	}
	
	public void setRoleType() {
		this.ptype = Role.PRINCIPAL_TYPE;
	}
	
	/**
	 * 在某个位置设置访问或者不能访问
	 *
	 * @author zhenglian
	 * @data 2015年12月16日 下午10:33:05
	 * @param index
	 * @param permit
	 */
	public void setPermission(int index, boolean permit) {
		//控制在0-31之间
		if(index < 0 || index > 31) {
			throw new RuntimeException("权限的位置只能在0-31位置之间");
		}
		
		this.aclState = setBit(this.aclState, index, permit);
	}
	
	/**
	 * 设置某一位上值设置为permit
	 *
	 * @author zhenglian
	 * @data 2015年12月16日 下午10:35:59
	 * @param state
	 * @param index
	 * @param permit
	 */
	private int setBit(int state, int index, boolean permit) {
		int tmp = 1;
		tmp = tmp << index;
		if(permit) {
			state |= tmp;
		}else {
			tmp = ~tmp;
			state &= tmp;
		}
		
		return state;
	}
	
	/**
	 * 检查在某个位置是否可以访问
	 *
	 * @author zhenglian
	 * @data 2015年12月16日 下午10:33:44
	 * @param index
	 * @return
	 */
	public boolean checkPermission(int index) {
		int tmp = 1 << index;
		return (this.aclState & tmp) > 0;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPtype() {
		return ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	public String getRtype() {
		return rtype;
	}

	public void setRtype(String rtype) {
		this.rtype = rtype;
	}

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public Long getrId() {
		return rId;
	}

	public void setrId(Long rId) {
		this.rId = rId;
	}

}
