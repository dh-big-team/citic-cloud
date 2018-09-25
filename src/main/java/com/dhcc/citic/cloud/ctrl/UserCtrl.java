package com.dhcc.citic.cloud.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dhcc.citic.cloud.common.PageData;
import com.dhcc.citic.cloud.model.PageParam;
import com.dhcc.citic.cloud.model.SysUser;
import com.dhcc.citic.cloud.service.UserService;
import com.github.pagehelper.PageInfo;

@Controller
public class UserCtrl {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 跳转到首页
	 * @return
	 */
	@RequestMapping({"/sys/user"})
	public String index(){
		return "pages/sys/userList";
	}
	
	@ResponseBody
	@RequestMapping({"/sys/user/data"})
	public Object getUserData(){
		PageParam pageParam = new PageParam();
		pageParam.setPageNum(1);
		pageParam.setPageSize(8);
		PageInfo<SysUser> pageUser= userService.findUserPage(null,pageParam);
		return new PageData(pageUser);
	}
}
