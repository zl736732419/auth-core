package com.zheng.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户实体
 *
 * @author zhenglian
 * @data 2015年12月15日 下午11:12:44
 */
@Entity
@Table(name = "t_user")
public class User implements Pricipal {
	public static final String PRINCIPAL_TYPE="user";
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private String password;
	private String nickname;
	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
