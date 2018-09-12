package com.czsm.service.impl.buyer;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.czsm.dao.buyer.BuyerUserInfoDao;
import com.czsm.entity.buyer.BuyerUserInfo;
import com.czsm.service.buyer.BuyerUserInfoService;

@Service("userService")
public class BuyerUserInfoServiceImpl implements BuyerUserInfoService {
	@Resource
	private BuyerUserInfoDao userInfoDao;

	/**
	 * 根据用户名查询用户
	 */
	@Override
	public BuyerUserInfo finInfoByUserName(String username) {
		
		return userInfoDao.finInfoByUserName(username);
	}

	
}
