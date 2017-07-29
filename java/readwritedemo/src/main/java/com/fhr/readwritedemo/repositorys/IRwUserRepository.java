package com.fhr.readwritedemo.repositorys;

import java.util.List;

import com.fhr.readwritedemo.models.RwUser;

/**
 * RwUser仓库接口
 * @author fhr
 * @date 2017/07/29
 */
public interface IRwUserRepository {
	List<RwUser> findAll();

	RwUser findById(Object id);
	
	void deleteById(Object id);
	
	void update(RwUser rwUserInfo);
	
	Object save(RwUser rwUserInfo);
	
	List<RwUser> findByPage(int pageIndex,int count);

}
