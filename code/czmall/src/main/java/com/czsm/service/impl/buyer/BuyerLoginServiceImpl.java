package com.czsm.service.impl.buyer;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.czsm.dao.buyer.BuyerLoginDao;
import com.czsm.entity.buyer.BuyerUserInfo;
import com.czsm.entity.commons.StringMsg;
import com.czsm.service.buyer.BuyerLoginService;
import com.czsm.util.MD5Util;
import com.czsm.util.Constants;


@Service("LoginService")
public class BuyerLoginServiceImpl implements BuyerLoginService {
	@Resource
	private BuyerLoginDao loginDao;

	@Override
	public boolean hasUserByAccont(String accont) {
		System.out.println("测试service   " + accont);
		BuyerUserInfo info = loginDao.hasUserByAccont("ss");
		System.out.println("用户信息：" + info.getUsername() + "========" + info.getUserid());
		List<BuyerUserInfo> lists = loginDao.findAllUser();
		for (BuyerUserInfo user : lists) {
			System.out.println(user.getUsername() + "---------" + user.getUserid());
		}
		return false;
	}

	/**
	 * 买家登录判断用户名是否存在
	 */
	@Override
	public StringMsg usernameExise(BuyerUserInfo info) {
		String msg = Constants.ACCOUNT_EXIST; // 返回给controller层的结果 用户名存在
		String result = loginDao.usernameExise(info);
		
		if (result == null) { // 查询的结果为空
			msg = Constants.ACCOUNT_NO_EXIST; // 返回“用户名不存在”
		}
		return StringMsg.setMsgs(msg);
	}

	/**
	 * 登录
	 */
	@Override
	public BuyerUserInfo login(BuyerUserInfo info) {
		//判断是邮箱还是手机号的正则表达式
		String email = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
		String phone = "^[1][34578]\\d{9}$";
		String username=info.getUsername();
		
		if(username.matches(phone)) {   //手机号登录
			info.setTel(username);						
			return loginDao.findUserByTel(info);
		}else if (username.matches(email)) {  //邮箱登录
			
			info.setEmail(username);			
			return loginDao.findUserByEmail(info);
		}else {  //用户名登录
			
			return loginDao.findUserByUsername(info);// 查出该用户的所有信息
		}
		
		
		
	}

	/**
	 * 查询买家输入的手机号码是否已存在
	 */
	@Override	
	public String accountTelExise(BuyerUserInfo info) {		
		String phone=info.getTel();  //获取用户输入的手机号码		
		String result="";
		try {
			result = loginDao.phoneExise(phone);
			if(result==null) {
				return Constants.CAN_REGISTER;   //可注册
			}else {
				return Constants.PHONE_EXISE;  //手机号码已存在
			}	
		} catch (Exception e) {			
			e.printStackTrace();
			return Constants.UNKNOWN_ERROR;
		}
			
	}
	
	/**
	 * 查询买家输入的邮箱号是否已存在
	 */
	@Override
	public String accountEmailExise(BuyerUserInfo info) {
		String mail=info.getEmail();  //获取用户输入的邮箱
		String result=loginDao.EmailExise(mail); //返回查询的结果
		if(result==null) {
			return Constants.CAN_REGISTER;
		}else {
			return Constants.EMAIL_EXISE; //邮箱号已存在
		}
		
	}

	/**
	 * 注册
	 */
	@Override
	public String signup(BuyerUserInfo info) {
		// 将用户输入的密码进行MD5加密处理
		String enPwd = MD5Util.EncoderByMd5(info.getPwd());
		info.setPwd(enPwd); // 将密码进行MD5加密

		if (info.getTel() == null) { // 如果用户电话号码为空，将用户名设置为邮箱
			info.setUsername(info.getEmail());
			info.setTel("");

		} else { // 电话号码不为空，用户名为手机号码
			info.setUsername(info.getTel());
			info.setEmail("");
		}
		try {
			int row = loginDao.signup(info); // 返回影响的行数
			if (row > 0) {
				return Constants.SUCCESS; // 注册成功
			} else {
				return Constants.FAIL; // 添加失败
			} 
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.UNKNOWN_ERROR;
		}

	}

	
	/**
	 * 买家忘记密码
	 */
	@Override
	public String forgetPwd(BuyerUserInfo info) {
		String result="";
		String password=MD5Util.EncoderByMd5(info.getPwd());
		
		info.setPwd(password);
		int row = loginDao.forgetPwd(info);
		if(row>0) {
			result=Constants.RESET_SUCCESS;
		}else {
			result=Constants.RESET_FAIL;
		}
		return result;
	}

	/**
	 * 通过手机号找用户名
	 */
	@Override
	public String findUsernameByTel(String buyerTel) {
		
		return loginDao.findUsernameByTel(buyerTel);
	}

	/**
	 * 通过邮箱号找用户名
	 */
	@Override
	public String findUsernameByEmail(String buyerEmail) {
		
		return loginDao.findUsernameByEmail(buyerEmail);
	}

}
