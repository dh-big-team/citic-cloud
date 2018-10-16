package com.dhcc.citic.cloud.mapper;

import java.util.List;

import com.dhcc.citic.cloud.model.SysUser;

public interface SysUserMapper {
	
	int deleteByPrimaryKey(Integer userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer userId);
    
    SysUser selectByLoginName(String loginName);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    
    List<SysUser> selectByPageNumSize(SysUser sysUser);
}