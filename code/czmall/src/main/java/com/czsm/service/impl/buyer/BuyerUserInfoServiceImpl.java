package com.czsm.service.impl.buyer;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.czsm.dao.buyer.BuyerUserInfoDao;
import com.czsm.entity.buyer.BuyerCompanyInfo;
import com.czsm.entity.buyer.BuyerUserInfo;
import com.czsm.service.buyer.BuyerUserInfoService;
import com.czsm.util.Constants;

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

	/**
	 * 根据买家编号修改买家信息
	 */
	@Override
	public String updateInfoById(BuyerUserInfo info) {
		int row=userInfoDao.updateInfoById(info);
		if(row>0) {
			return Constants.SUCCESS;
		}else {
			return Constants.FAIL;
		}
		
	}

	/**
	 * 显示买家公司信息
	 */
	@Override
	public BuyerCompanyInfo companyInfoByUserid(int userid) {
		
		return userInfoDao.companyInfoByUserid(userid);
	}

	
}
