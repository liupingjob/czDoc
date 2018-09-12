package com.czsm.service.buyer;

import com.czsm.entity.buyer.BuyerUserInfo;

public interface BuyerUserInfoService {

	//根据用户编号查询该卖家所有信息
	BuyerUserInfo finInfoByUserName(String username);
	

}
