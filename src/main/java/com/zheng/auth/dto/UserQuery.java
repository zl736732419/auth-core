package com.zheng.auth.dto;

import org.apache.commons.lang3.StringUtils;

/**
 * 用户查询对象
 *
 * @author zhenglian
 * @time 2015年12月25日 下午2:32:22
 */
public class UserQuery extends ObjectQuery {
	private String username;
	private String nickname;
	private Integer status;

	@Override
	protected void addWhere() {
		if(!StringUtils.isBlank(username)) {
			addWhere("o.username like ?", "%" + username + "%");
		}
		
		if(!StringUtils.isBlank(nickname)) {
			addWhere("o.nickname like ? ", "%" + nickname + "%");
		}
		
		if(status != null) {
			addWhere("o.status = ?", status);
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
