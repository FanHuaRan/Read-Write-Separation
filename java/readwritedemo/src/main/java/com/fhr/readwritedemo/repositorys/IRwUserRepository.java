package com.fhr.readwritedemo.repositorys;

import java.sql.SQLException;
import java.util.List;

import com.fhr.readwritedemo.models.RwUser;

/**
 * RwUser仓库接口
 * @author fhr
 * @since 2017/07/29
 */
public interface IRwUserRepository {
	List<RwUser> findAll() throws SQLException;

	RwUser findById(Object id) throws SQLException;
	
	void deleteById(Object id) throws SQLException;
	
	void update(RwUser rwUser) throws SQLException;
	
	Object save(RwUser rwUser) throws SQLException;
	
	List<RwUser> findByPage(int pageIndex,int count) throws SQLException;

}
