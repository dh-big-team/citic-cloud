package com.dhcc.citic.cloud.ctrl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.citic.cloud.common.BaseResult;
import com.dhcc.citic.cloud.config.EnumConfig.ErrCode;
import com.dhcc.citic.cloud.config.EnumConfig.RetCode;
import com.dhcc.citic.cloud.config.EnumConfig.UserStatus;
import com.dhcc.citic.cloud.model.SysMenu;
import com.dhcc.citic.cloud.model.SysUser;
import com.dhcc.citic.cloud.service.MenuService;
import com.dhcc.citic.cloud.service.UserService;
import com.dhcc.citic.cloud.utils.CommonUtil;

@RestController
@RequestMapping(value = "/api")
public class ApiCtrl extends BaseCtrl{
	
		
	private static final Logger LOG = LoggerFactory.getLogger(ApiCtrl.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;
	
	
	/**
	 * 用户登录
	 * @param jsonParam
	 * @return
	 */
	@RequestMapping(value = "/user/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public BaseResult userLogin(@RequestBody JSONObject jsonParam){
		LOG.info("------------recv param={}",jsonParam);
		//查找用户信息
		SysUser user = userService.findUserByLoginName(jsonParam.getString("username"));
		if(user==null){
			return new BaseResult(RetCode.VALID_ERR,ErrCode.VALID_NAMEPWD_ERR);
		}
		//密码校验
		String encrpyPwd = CommonUtil.encrypUserPwd(jsonParam.getString("password"), user.getPwdSalt());
		if(!encrpyPwd.equals(user.getLoginPwd())){
			return new BaseResult(RetCode.VALID_ERR,ErrCode.VALID_NAMEPWD_ERR);
		}
		//用户状态校验
		if(user.getUserStatus().equals(UserStatus.InActive)){
			return BaseResult.getDesignErr(RetCode.BUSI_ERR, ErrCode.COM_BUSI_ERR, "该用户状态异常");
		}
		//获取用户菜单列表
		List<SysMenu> menuList = menuService.findMenuByUserId(user.getUserId());
		SysMenu rootMenu = new SysMenu();
		rootMenu.setMenuId(0);
		menuService.parseUserMenu(rootMenu, menuList);
		user.setMenuList(rootMenu.getChildList());
		user.setToken(user.getLoginName());
		//将用户信息保存至session
		super.setSession("apiuser", user);
		//校验通过后返回用户信息
		return new BaseResult(user);
	}
}
