package com.fhr.readwritedemo.services;

import java.util.List;

import com.fhr.readwritedemo.dtomodels.RwUserInfo;

/**
 * RwUserInfo服务
 * @author fhr
 * @date 2017/07/29
 */ 
public interface IRwUserInfoService {
	
	List<RwUserInfo> findAll();
	RwUserInfo findById(Object id);
	
	void deleteById(Object id);
	
	void update(RwUserInfo rwUserInfo);
	
	Object save(RwUserInfo rwUserInfo);
	
	List<RwUserInfo> findByPage(int pageIndex,int count);
}
