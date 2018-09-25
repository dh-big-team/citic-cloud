package com.dhcc.citic.cloud.service;


import java.util.List;

import com.dhcc.citic.cloud.model.PageParam;
import com.dhcc.citic.cloud.model.SysMenu;
import com.github.pagehelper.PageInfo;


public interface MenuService {
	public List<SysMenu> findMenuByUserId(Integer userId);
	public PageInfo<SysMenu> findMenuPage(SysMenu sysMenu, PageParam pageParam);
	
	public void parseUserMenu(SysMenu rootMenu,List<SysMenu> menuList);
}
