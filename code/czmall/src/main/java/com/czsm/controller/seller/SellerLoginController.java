package com.czsm.controller.seller;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czsm.entity.buyer.BuyerUserInfo;
import com.czsm.entity.commons.StringMsg;
import com.czsm.service.buyer.BuyerLoginService;
import com.czsm.util.MD5Util;
import com.czsm.util.Constants;


/**
 * 商家登录注册相关功能
 * 
 * @author Mac(刘平) 20180804
 */
@Controller
public class SellerLoginController {
	@Autowired
	private BuyerLoginService loginService;

	/**
	 * 去商家登录页面
	 * 
	 * @param model 传值给页面
	 * @return 页面路径
	 */
	@RequestMapping("sellerToLogin")
	public String toLogin(Model model) {		
		return "seller/login";
	}

	/**
	 * 商家登录时判断用户名是否存在
	 * 
	 * @param username 传入用户名
	 * @return 返回结果  success或账号不存在
	 */
	@RequestMapping("sellerUsernameExise")
	@ResponseBody
	public StringMsg usernameExise(BuyerUserInfo info) {
		//判断是邮箱还是手机号的正则表达式
		String email = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
		String phone = "^[1][34578]\\d{9}$";
		String username=info.getUsername();
		if(username.matches(phone)) {   //手机号登录
			info.setTel(username);
			info.setEmail("");			
			return loginService.usernameExise(info);
		}else if (username.matches(email)) {  //邮箱登录
			info.setEmail(username);
			info.setTel("");
			return loginService.usernameExise(info);
		}else {  //用户名登录
			info.setEmail("");
			info.setTel("");
			return loginService.usernameExise(info);
		}
		
		
	}

	

	/**
	 * 商家登录
	 * 
	 * @param info  传入用户名和密码
	 * @param request  
	 * @return  返回一个结果  success或密码错误
	 */
	@RequestMapping("sellerLogin")
	@ResponseBody
	public String login(BuyerUserInfo info, HttpServletRequest request, HttpSession session) {
		
		System.out.println("用户尝试登陆：" + session);
		//将用户输入的密码进行加密
		String mpwd = MD5Util.EncoderByMd5(info.getPwd());
		info.setPwd(mpwd); //将加密后的密码放入info对象的密码属性中

		BuyerUserInfo uInfo = loginService.login(info); // 获取用户的信息		
			
		Integer counts = (Integer) session.getAttribute(Constants.LOGIN_COUNT_MSG);  
		String picVcode=info.getVcode();
		if (counts == null || counts == 0) {//首次登录
			if (uInfo == null) {
		   		session.setAttribute(Constants.LOGIN_COUNT_MSG, 1);  //设置登录的次数为1
				return Constants.PWD_ERROR;   //返回前端，密码错误
			} else {// 登录成功
				request.getSession().setAttribute("usinfo", uInfo);  //将登录用户的基本信息存入session中
				session.setAttribute(Constants.LOGIN_COUNT_MSG, 0);  //将登录的次数设置为0
				return Constants.SUCCESS; //返回前端，登录成功
			}
		} else if (counts >Constants.LOGIN_COUNT && picVcode==null ) {// 密码错误大于3次 ,则需要输入验证码	
			
			return Constants.INPUT_VCODE;   //将生成的验证码返回给前端
			
		} else {
			if (uInfo == null) {  //用户信息的内容为空
				session.setAttribute(Constants.LOGIN_COUNT_MSG, ++counts); //登录次数加1
				return Constants.PWD_ERROR;  //返回前端，密码错误
			} else {				
				return Constants.SUCCESS;  //返回前端登录成功
			}
		}
		
	}
	
	/**
	 * 去商家注册页面
	 * 
	 * @param model 传值给页面
	 * @return 页面路径
	 */
	@RequestMapping("sellerToSignup")
	public String toSignup(Model model) {
		return "seller/mailbox";
	}
	
	/**
	 * 商家注册时，查询商家输入的手机号码是否已经被注册
	 * 
	 * @param info  传入商家输入的手机号码
	 * @return  返回结果  success或手机号已存在或者邮箱已存在
	 */
	@RequestMapping("sellerAccountTelExise")
	@ResponseBody	
	public StringMsg accountTelExise(BuyerUserInfo info , HttpSession session) {		
		String result= loginService.accountTelExise(info);//将手机号或邮箱号是否存在的结果返回给前端		
		return StringMsg.setMsgs(result);  //将此对象返回给前端
	}
	
	
	/**
	 * 商家注册时，查询商家输入的邮箱号是否已经被注册
	 * 
	 * @param info  传入商家输入的邮箱号
	 * @return   返回    可注册，手机号已存在或未知错误
	 */
	@RequestMapping("sellerAccountEmailExise")
	@ResponseBody
	public StringMsg accountEmailExise(BuyerUserInfo info , HttpSession session) {
		String result= loginService.accountEmailExise(info);
		return StringMsg.setMsgs(result);
	}
	
	/**
	 * 商家注册
	 * 
	 * @param info 传入商家输入的信息包括手机号码或邮箱号、密码
	 * @return  返回success或fail
	 */
	@RequestMapping("sellerSignup")
	@ResponseBody
	public StringMsg signup(BuyerUserInfo info) {
		String result=loginService.signup(info); //接收到注册的结果
		return StringMsg.setMsgs(result);//将注册的结果返回给前端
	}
	
	/**
	 * 去忘记密码界面
	 * @param model
	 * @return
	 */
	@RequestMapping("sellerToForgetPwd")
	@ResponseBody
	public String toForgetPwd(Model model) {
		return "seller/";
	}
	
	/**
	 * 忘记密码——通过手机号找到用户名
	 * @param buyerTel  传入商家输入的手机号
	 * @param session  将查询出的用户名存入session
	 * @return  返回  手机号不存在(phoneNoExise)或手机号存在(phoneExise)
	 */
	@RequestMapping("sellerForgetPwdTelBack")
	@ResponseBody	
	public StringMsg forgetTelBack(String buyerTel , HttpSession session) {		
		String result="";
		String username=loginService.findUsernameByTel(buyerTel);//根据手机号查询用户名
		if(username.equals("")) {
			result=Constants.PHONE_NO_EXISE;//商家输入的手机号不存在
		}else {
			result=Constants.PHONE_EXISE;//手机号存在，可找回密码
			session.setAttribute("forget", username); //将根据用户输入的手机号查询出的用户名存入session
		}			
		return StringMsg.setMsgs(result);  //将此对象返回给前端
	}
	
	
	/**
	 * 忘记密码——通过邮箱号找到用户名
	 * @param buyerEmail  传入商家输入的邮箱号
	 * @param session   根据邮箱号查询出用户名存入session中
	 * @return  返回邮箱号存在(emailExise)或邮箱号不存在(inputEmailError)
	 */
	@RequestMapping("sellerForgetPwdEmailBack")
	@ResponseBody
	public StringMsg forgetEmailBack(String buyerEmail , HttpSession session) {
		String result="";
		String username=loginService.findUsernameByEmail(buyerEmail);
		if(username.equals("")) {
			result=Constants.EMAIL_ERROR;//商家输入的邮箱号不存在
		}else {
			result=Constants.EMAIL_EXISE;//邮箱号存在，可找回密码
			session.setAttribute("forget", username); //将根据用户输入的邮箱号查询出的用户名存入session
		}		
		return StringMsg.setMsgs(result);
	}
	
	/**
	 * 重置密码
	 * @param info  传入商家输入的密码
	 * @param session  传入商家存储的账号
	 * @return  重置成功
	 */
	@RequestMapping("sellerForgetPwd")
	@ResponseBody
	public StringMsg forgetPwd (BuyerUserInfo info ,HttpSession session) {
		//获取商家输入的手机号或邮箱号
		String account=(String) session.getAttribute("forget");
		info.setUsername(account);
		String result =loginService.forgetPwd(info);
		return StringMsg.setMsgs(result);
	}

}
