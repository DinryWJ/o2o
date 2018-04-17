package com.wjj.o2o.service;

import java.io.IOException;
import java.util.List;

import com.wjj.o2o.entity.HeadLine;

public interface HeadLineService {
	public static final String HLLISTKEY = "headlinelist";
	
	List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException;
} 
