package com.wjj.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wjj.o2o.entity.Award;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AwardDaoTest {
	@Autowired
	private AwardDao awardDao;
	
	@Test
	public void testAInsertAward() {
		long shopId=4L;
		Award award1 = new Award();
		award1.setAwardName("测试1");
		award1.setAwardImg("test1");
		award1.setAwardDesc("test");
		award1.setCreateTime(new Date());
		award1.setEnableStatus(1);
		award1.setLastEditTime(new Date());
		award1.setPoint(4);
		award1.setPriority(10);
		award1.setShopId(shopId);
		int effectedNum1 = awardDao.insertAward(award1);
		assertEquals(1,effectedNum1);
		Award award2 = new Award();
		award2.setAwardName("测试2");
		award2.setAwardImg("test2");
		award2.setAwardDesc("test");
		award2.setCreateTime(new Date());
		award2.setEnableStatus(2);
		award2.setLastEditTime(new Date());
		award2.setPoint(4);
		award2.setPriority(20);
		award2.setShopId(shopId);
		int effectedNum2 = awardDao.insertAward(award2);
		assertEquals(1,effectedNum2);
	}
	
	@Test
	public void testBQueryAwardList() {
		Award award = new Award();
		List<Award> list = awardDao.queryAwardList(award, 0, 1);
		assertEquals(1, list.size());
		int count = awardDao.queryAwardCount(award);
		assertEquals(2, count);
		award.setAwardName("测试");
		list = awardDao.queryAwardList(award, 0, 3);
		assertEquals(2, list.size());
		count = awardDao.queryAwardCount(award);
		assertEquals(2, count);
	}
	@Test
	public void testCQueryAwardByAwardId() {	
		Award award = new Award();
		award.setAwardName("测试1");
		List<Award> list = awardDao.queryAwardList(award, 0, 1);
		assertEquals(1, list.size());
		Award award2 = awardDao.queryAwardById(list.get(0).getAwardId());
		assertEquals("测试1", award2.getAwardName());
	}
	@Test
	public void testDUpdateAward() {	
		Award awardCondition = new Award();
		awardCondition.setAwardName("测试1");
		List<Award> list = awardDao.queryAwardList(awardCondition, 0, 1);
		list.get(0).setAwardName("第一个测试产品");
		int effectedNum = awardDao.updateAward(list.get(0));
		assertEquals(1, effectedNum);
		Award award = awardDao.queryAwardById(list.get(0).getAwardId());
		assertEquals("第一个测试产品", award.getAwardName());
	}
	@Test
	public void testEDeleteAward() {
		Award awardCondition = new Award();
		awardCondition.setAwardName("测试");
		List<Award> list = awardDao.queryAwardList(awardCondition, 0, 5);
		for (Award award : list) {
			int effectedNum = awardDao.deleteAward(award.getAwardId(), 4L);
			assertEquals(1, effectedNum);
		}
	}
}