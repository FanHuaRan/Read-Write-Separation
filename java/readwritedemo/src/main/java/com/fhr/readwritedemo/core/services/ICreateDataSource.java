package com.fhr.readwritedemo.core.services;

import javax.sql.DataSource;

import com.fhr.readwritedemo.core.models.DataBaseInfo;

/**
 * 数据库连接池创建接口
 * @author fhr
 * @since 2017/07/29
 */
public interface ICreateDataSource {
	
	 /**
	  * 通 DataBaseInfo创建数据库连接池
	  * @param dataBaseInfo
	  * @return
	  */
	 DataSource create(DataBaseInfo dataBaseInfo);
	
}
