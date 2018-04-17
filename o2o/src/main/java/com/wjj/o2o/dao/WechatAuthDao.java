package com.wjj.o2o.dao;

import com.wjj.o2o.entity.WechatAuth;

public interface WechatAuthDao {
	
	/**
	 * Search the Whchat account (In this platform) by openId.
	 * @param openId
	 * @return
	 */
	WechatAuth queryWechatInfoByOpenId(String openId);
	
	/**
	 * Add Wecaht account.
	 * @param wechatAuth
	 * @return
	 */
	int insertWechatAuth(WechatAuth wechatAuth);
}
