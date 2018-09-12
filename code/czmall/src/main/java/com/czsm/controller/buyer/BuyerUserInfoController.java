package com.czsm.controller.buyer;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czsm.entity.buyer.BuyerUserInfo;
import com.czsm.service.buyer.BuyerUserInfoService;

/**
 * 买家用户信息
 * @author Administrator
 *
 */
@Controller
public class BuyerUserInfoController {

	@Autowired
	private BuyerUserInfoService  userService;
	
	/**
	 * 查询买家个人信息
	 * @param session  传入登录的账号
	 * @return  返回买家类，包含买家信息
	 */
	@RequestMapping("findInfoByUserName")
	@ResponseBody
	public BuyerUserInfo findInfoByUserName(HttpSession session) {
		
		BuyerUserInfo info =(BuyerUserInfo)session.getAttribute("usinfo");		
		String username=info.getUsername();
		return userService.finInfoByUserName(username);

	}
}
