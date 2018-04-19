package com.wjj.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wjj.o2o.entity.Award;

public interface AwardDao {

	/**
	 * 分页查询奖品列表。
	 * @param awardCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<Award> queryAwardList(@Param("awardCondition") Award awardCondition, @Param("rowIndex") int rowIndex,
			@Param("pageSize") int pageSize);
	
	/**
	 * 配合queryAwardList查询总数。
	 * @param awardCondition
	 * @return
	 */
	int queryAwardCount(@Param("awardCondition") Award awardCondition);
	
	/**
	 * 根据id查询奖品。
	 * @param awardId
	 * @return
	 */
	Award queryAwardById(long awardId);

	/**
	 * 插入奖品信息。
	 * @param award
	 * @return
	 */
	int insertAward(Award award);
	
	/**
	 * 更新奖品信息。
	 * @param award
	 * @return
	 */
	int updateAward(Award award);

	/**
	 * 删除奖品信息。
	 * @param awardId
	 * @param shopId
	 * @return
	 */
	int deleteAward(@Param("awardId") long awardId,@Param("shopId")long shopId);
}
