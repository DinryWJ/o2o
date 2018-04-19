package com.wjj.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wjj.o2o.entity.UserAwardMap;

public interface UserAwardMapDao {

	/**
	 * 根据传入的查询条件分页返回用户兑换奖品记录的列表信息。
	 * 
	 * @param userAwardCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<UserAwardMap> queryUserAwardMapList(@Param("awardCondition") UserAwardMap userAwardCondition,
			@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

	int queryUserAwardMapCount(@Param("awardCondition") UserAwardMap userAwardCondition);

	UserAwardMap queryUserAwardMapById(long userAwardId);

	int insertUserAwardMap(UserAwardMap userAwardMap);

	int updateUserAwardMap(UserAwardMap userAwardMap);

}
