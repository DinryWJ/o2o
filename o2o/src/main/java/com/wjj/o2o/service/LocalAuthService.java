package com.wjj.o2o.service;

import com.wjj.o2o.dto.LocalAuthExecution;
import com.wjj.o2o.entity.LocalAuth;
import com.wjj.o2o.exceptions.LocalAuthOperationException;

public interface LocalAuthService {
	/**
	 * 通过username password获取本地账号信息。
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	LocalAuth getLocalAuthByUsernameAndPwd(String username, String password);

	/**
	 * 通过userId获取本地账号。
	 * 
	 * @param userId
	 * @return
	 */
	LocalAuth getLocalAuthByUserId(long userId);

	/**
	 * 绑定微信，生成本地账号。
	 * 
	 * @param localAuth
	 * @return
	 * @throws LocalAuthException
	 */
	LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException;

	/**
	 * 修改平台密码。
	 * @param userId
	 * @param username
	 * @param password
	 * @param newPassword
	 * @return
	 * @throws LocalAuthOperationException
	 */
	LocalAuthExecution modifyLocalAuth(Long userId, String username, String password, String newPassword) throws LocalAuthOperationException;
}
