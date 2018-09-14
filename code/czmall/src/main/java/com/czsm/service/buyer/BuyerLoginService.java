package com.czsm.service.buyer;



import com.czsm.entity.buyer.BuyerUserInfo;
import com.czsm.entity.commons.StringMsg;

public interface BuyerLoginService {
	public boolean hasUserByAccont(String accont);

	//查询用户名是否存在
	public StringMsg usernameExise(BuyerUserInfo info);

	//登录
	public BuyerUserInfo login(BuyerUserInfo info);

	//查询买家输入的手机号码是否已存在
	public String accountTelExise(BuyerUserInfo info);
	
	//查询买家输入的邮箱号是否已存在
	public String accountEmailExise(BuyerUserInfo info);
	
	//注册
	public String signup(BuyerUserInfo info);	

	//买家忘记密码
	public String forgetPwd(BuyerUserInfo info);
	//通过手机号找用户名
	public String findUsernameByTel(String buyerTel);
	//通过邮箱号找用户名
	public String findUsernameByEmail(String buyerEmail);
	

	

	
}
