package com.dhcc.citic.cloud.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhcc.citic.cloud.dao.SysUserMapper;
import com.dhcc.citic.cloud.model.PageParam;
import com.dhcc.citic.cloud.model.SysUser;
import com.dhcc.citic.cloud.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 用户表业务类
 * 文件名称:     UserServiceImpl.java
 * 内容摘要: 
 * @author:   Zeng Dongcheng
 * @version:  1.0  
 * @Date:     2018年6月27日下午4:38:01 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年6月27日     Zeng Dongcheng   1.0     新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   深圳市至高通信技术发展有限公司
 */
@Service("userService")
public class UserServiceImpl implements UserService{
	 @Autowired
	 private SysUserMapper userMapper;
	 
	@Override
	public PageInfo<SysUser> findUserPage(SysUser sysUser, PageParam pageParam) {
		//将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<SysUser> users =  userMapper.selectByPageNumSize(sysUser);
        return new PageInfo<SysUser>(users);
	}
	
	@Override
	public SysUser findUserByLoginName(String loginName){
		return userMapper.selectByLoginName(loginName);
	}
	
	@Override
	public boolean addUser(SysUser user) {
		return userMapper.insert(user)>0;
	}
	
	
	@Override
	public boolean updUser(SysUser user) {
		return userMapper.updateByPrimaryKey(user)>0;
	}

	
	@Override
	public SysUser getUser(Integer userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public boolean delUser(Integer userId) {
		return userMapper.deleteByPrimaryKey(userId)>0;
	}

}
