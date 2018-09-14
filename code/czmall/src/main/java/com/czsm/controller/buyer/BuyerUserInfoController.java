package com.czsm.controller.buyer;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czsm.entity.buyer.BuyerCompanyInfo;
import com.czsm.entity.buyer.BuyerUserInfo;
import com.czsm.entity.commons.StringMsg;
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
	@RequestMapping("BuyerFindInfoByUserName")
	@ResponseBody
	public BuyerUserInfo findInfoByUserName(HttpSession session) {
		BuyerUserInfo info =(BuyerUserInfo)session.getAttribute("usinfo");		
		String username=info.getUsername();		
		return userService.finInfoByUserName(username);
	}
	/**
	 * 根据买家编号修改买家个人信息
	 * @param info  传入内容
	 * @return  返回修改成功(success)或修改失败(fail)
	 */
	@RequestMapping("BuyerUpdateinfoById")
	@ResponseBody
	public StringMsg BuyerUpdateinfoById(BuyerUserInfo info ,HttpSession session){
		BuyerUserInfo buyerInfo=(BuyerUserInfo)session.getAttribute("usinfo");
		int buyerId = buyerInfo.getUserid();
		info.setUserid(buyerId);
		
//		info.setPetname("55");
//		info.setUserid(4);
//		info.setBirthday("1980-04-01");
//		info.setSex("女");
//		info.setIdentity("23165156");
//		info.setJob("yuui");
		
		String result= userService.updateInfoById(info);
		return StringMsg.setMsgs(result);
	}
	
	@RequestMapping("BuyerCompanyinfo")
	@ResponseBody
	public BuyerCompanyInfo companyInfoByUserid(HttpSession session) {
//		BuyerUserInfo info = (BuyerUserInfo)session.getAttribute("usinfo");
//		int userid=info.getUserid();
		int userid=1;
		return userService.companyInfoByUserid(userid);
	}
	
}
