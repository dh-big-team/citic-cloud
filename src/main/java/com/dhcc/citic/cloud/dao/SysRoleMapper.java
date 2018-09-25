package com.dhcc.citic.cloud.dao;

import java.util.List;

import com.dhcc.citic.cloud.model.SysRole;

public interface SysRoleMapper {

    int deleteByPrimaryKey(Integer roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    List<SysRole> selectRoles(SysRole sysRole);

    SysRole selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}