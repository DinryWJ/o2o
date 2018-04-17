package com.wjj.o2o.dao;

import com.wjj.o2o.entity.PersonInfo;

public interface PersonInfoDao {
	/**
	 * Search User By userId
	 * @param userId
	 * @return
	 */
	PersonInfo queryPersonInfoById(long userId);
	
	/**
	 * Insert user info
	 * @param personInfo
	 * @return
	 */
	int insertPersonInfo(PersonInfo personInfo);
}
