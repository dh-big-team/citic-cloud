package com.dhcc.citic.cloud.service;
import com.dhcc.citic.cloud.model.PageParam;
import com.dhcc.citic.cloud.model.SysUser;
import com.github.pagehelper.PageInfo;

public interface UserService {
	
	public PageInfo<SysUser> findUserPage(SysUser sysUser,PageParam pageParam);
	
	public SysUser findUserByLoginName(String loginName);
	
	public boolean addUser(SysUser user);
	
	public SysUser getUser(Integer userId);
	
	public boolean updUser(SysUser user);
	
	public boolean delUser(Integer userId);
}
