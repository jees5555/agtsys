package com.github.jees5555.agtsys.domain;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

import com.github.jees5555.agtsys.util.MD5;
import com.github.jees5555.agtsys.validate.LoginValidateGroup;

public class User {
	private Long id;

	@NotEmpty(message = "{user.usercode.isempty}", groups = { LoginValidateGroup.class })
	private String usercode;

	private String username;

	@NotEmpty(message = "{user.userpassword.isempty}", groups = { LoginValidateGroup.class })
	private String userpassword;

	private Date creationtime;

	private Date lastlogintime;

	private String createdby;

	private Date lastupdatetime;

	private Integer isstart;

	private Long roleid;

	private String rolename;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode == null ? null : usercode.trim();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = (userpassword == null || userpassword.trim().equals("")) ? null
				: MD5.md5encode(userpassword.trim());
	}

	public Date getCreationtime() {
		return creationtime;
	}

	public void setCreationtime(Date creationtime) {
		this.creationtime = creationtime;
	}

	public Date getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby == null ? null : createdby.trim();
	}

	public Date getLastupdatetime() {
		return lastupdatetime;
	}

	public void setLastupdatetime(Date lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}

	public Integer getIsstart() {
		return isstart;
	}

	public void setIsstart(Integer isstart) {
		this.isstart = isstart;
	}

	public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}