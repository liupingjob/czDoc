package com.czsm.dao.buyer;

import com.czsm.entity.buyer.BuyerCompanyInfo;
import com.czsm.entity.buyer.BuyerUserInfo;

public interface BuyerUserInfoDao {

	/**
	 * 根据用户名查询用户信息
	 * @param username
	 * @return
	 */
	public BuyerUserInfo finInfoByUserName(String username);

	/**
	 * 根据用户编号修改买家信息
	 * @param info
	 * @return
	 */
	public int updateInfoById(BuyerUserInfo info);

	/**
	 * 根据买家编号，查询买家
	 * @param userid
	 * @return
	 */
	public BuyerCompanyInfo companyInfoByUserid(int userid);

}
