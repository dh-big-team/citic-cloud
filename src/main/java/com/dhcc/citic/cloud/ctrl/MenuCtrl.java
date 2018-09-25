package com.dhcc.citic.cloud.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dhcc.citic.cloud.common.PageData;
import com.dhcc.citic.cloud.model.PageParam;
import com.dhcc.citic.cloud.model.SysMenu;
import com.dhcc.citic.cloud.service.MenuService;
import com.github.pagehelper.PageInfo;

@Controller
public class MenuCtrl {
	@Autowired
	private MenuService menuService;
	
	/**
	 * 跳转到首页
	 * @return
	 */
	@RequestMapping({"/sys/menu"})
	public String index(){
		return "pages/sys/menuList";
	}
	
	@ResponseBody
	@RequestMapping({"/sys/menu/data"})
	public Object getMenuData(int pageNum,int pageSize){
		PageParam pageParam = new PageParam();
		pageParam.setPageNum(pageNum);
		pageParam.setPageSize(pageSize);
		PageInfo<SysMenu> pageMenu= menuService.findMenuPage(null,pageParam);
		return new PageData(pageMenu);
	}
}
