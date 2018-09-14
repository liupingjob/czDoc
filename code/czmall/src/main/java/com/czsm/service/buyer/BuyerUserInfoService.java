package com.czsm.service.buyer;

import com.czsm.entity.buyer.BuyerCompanyInfo;
import com.czsm.entity.buyer.BuyerUserInfo;



public interface BuyerUserInfoService {

	//根据用户编号查询该买家所有信息
	BuyerUserInfo finInfoByUserName(String username);

	//根据用户编号修改买家所有信息
	String updateInfoById(BuyerUserInfo info);

	//显示买家公司信息
	BuyerCompanyInfo companyInfoByUserid(int userid);
	

}
