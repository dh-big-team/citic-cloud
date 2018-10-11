package com.dhcc.citic.cloud.ctrl;

import java.util.HashMap;
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
import com.dhcc.citic.cloud.model.TmpSecret;
import com.dhcc.citic.cloud.req.ApiRequest;
import com.dhcc.citic.cloud.service.MenuService;
import com.dhcc.citic.cloud.service.TmpSecretService;
import com.dhcc.citic.cloud.service.UserService;
import com.dhcc.citic.cloud.utils.CommonUtil;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.cvm.v20170312.CvmClient;
import com.tencentcloudapi.cvm.v20170312.models.DescribeZonesRequest;
import com.tencentcloudapi.cvm.v20170312.models.DescribeZonesResponse;

@RestController
@RequestMapping(value = "/api",produces = "application/json;charset=UTF-8")
public class ApiCtrl extends BaseCtrl{
	
		
	private static final Logger LOG = LoggerFactory.getLogger(ApiCtrl.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private TmpSecretService tmpSecretService;
	
	
	/**
	 * 用户登录
	 * @param jsonParam
	 * @return
	 */
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
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
	
	/**
	 * 测试腾讯API（基于sdk3.0自带模式）
	 * @param jsonParam
	 * @return
	 * @throws TencentCloudSDKException 
	 */
	@RequestMapping(value = "/DescribeZones")
	public BaseResult describeZones() throws TencentCloudSDKException{
        // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey
        Credential cred = new Credential("AKIDiKk3EIdzpcJp8Gr4mKpBQTKzSirprDh0", "GPSJMH0kMtHT1TZZPI5jCXlttiL0Yw9b");

        // 实例化要请求产品(以cvm为例)的client对象
        CvmClient client = new CvmClient(cred, "ap-guangzhou");

        // 实例化一个请求对象
        DescribeZonesRequest req = new DescribeZonesRequest();

        // 通过client对象调用想要访问的接口，需要传入请求对象
        DescribeZonesResponse resp = client.DescribeZones(req);
        
        // 输出json格式的字符串回包
       return new BaseResult(resp);
	}
	
	/**
	 * 测试腾讯APi（基于sdk3.0封装的通用模式）
	 * @return
	 * @throws TencentCloudSDKException 
	 */
	@RequestMapping(value = "/DescribeRegions")
	public BaseResult test2(String uin) throws TencentCloudSDKException{
		TmpSecret tmpSecret = tmpSecretService.getTmpSecret(uin);
		Credential cred = new Credential(tmpSecret.getTmpSecretId(), tmpSecret.getTmpSecretKey(),tmpSecret.getSessionToken());
		ApiRequest req = new ApiRequest("cvm.tencentcloudapi.com","2017-03-12", cred, "ap-guangzhou");
		JSONObject rep = req.recvResponseRequest(new HashMap<String,String>(), "DescribeRegions");
		return new BaseResult(rep);
	}
}
