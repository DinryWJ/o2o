package com.wjj.o2o.dao;


import java.util.List;

import com.wjj.o2o.entity.Area;



public interface AreaDao {
	/**
	 * 列出地域列表
	 * 
	 * @param areaCondition
	 * @return
	 */
	List<Area> queryArea();

}
