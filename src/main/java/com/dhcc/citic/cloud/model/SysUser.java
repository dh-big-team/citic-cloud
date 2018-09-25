package com.dhcc.citic.cloud.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SysUser implements Serializable{
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Integer userId;


	private String userName;


	private String loginName;


	private String loginPwd;


	private Integer deptId;


	private String userStatus;


	private String pwdSalt;


	private String createUser;


	private Date createDate;


	private String updUser;


	private Date updDate;

	private List<SysMenu> menuList;
	
	private String token;

	
	
    public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getPwdSalt() {
		return pwdSalt;
	}

	public void setPwdSalt(String pwdSalt) {
		this.pwdSalt = pwdSalt;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdUser() {
		return updUser;
	}

	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}

	public Date getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public List<SysMenu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<SysMenu> menuList) {
		this.menuList = menuList;
	}

	
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
    public boolean equals(Object obj) {
        if (obj instanceof SysUser) {
            SysUser user= (SysUser) obj;
            return loginName.equals(user.getLoginName());
        }
        return false;
    }
    
    @Override
    public int hashCode() {//重写hashCode方法
        return userId.hashCode();
    }
}