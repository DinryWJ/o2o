package com.wjj.o2o.service;

import com.wjj.o2o.dto.WechatAuthExecution;
import com.wjj.o2o.entity.WechatAuth;
import com.wjj.o2o.exceptions.WechatAuthOperationException;

public interface WechatAuthService {
	/**
	 * Search the wechat account (in this platform) by openId.
	 * @param openId
	 * @return
	 */
	WechatAuth getWechatAuthByOpenId(String openId);
	
	/**
	 * register this platform's wechat account.
	 * @param wechatAuth
	 * @return
	 * @throws WechatAuthOperationException
	 */
	WechatAuthExecution register(WechatAuth wechatAuth) throws WechatAuthOperationException;
}
