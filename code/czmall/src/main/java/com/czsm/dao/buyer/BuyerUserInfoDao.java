package com.czsm.dao.buyer;

import com.czsm.entity.buyer.BuyerUserInfo;

public interface BuyerUserInfoDao {

	/**
	 * 根据用户名查询用户信息
	 * @param username
	 * @return
	 */
	public BuyerUserInfo finInfoByUserName(String username);

}
