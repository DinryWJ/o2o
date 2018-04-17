package com.wjj.o2o.service;

import com.wjj.o2o.entity.PersonInfo;

public interface PersonInfoService {
	/**
	 * get PersonInfo message according to userId.
	 * @param userId
	 * @return
	 */
	PersonInfo getPersonInfoById(Long userId);
}
