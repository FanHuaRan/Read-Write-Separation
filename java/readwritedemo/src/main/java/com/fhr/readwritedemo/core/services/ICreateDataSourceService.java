package com.fhr.readwritedemo.core.services;

import javax.sql.DataSource;

import com.fhr.readwritedemo.core.models.DataBaseInfo;

/**
 * 数据库连接池创建接口
 * @author fhr
 * @date 2017/07/29
 */
public interface ICreateDataSourceService {
	
	 DataSource createDataSource(DataBaseInfo dataBaseInfo);
	
}