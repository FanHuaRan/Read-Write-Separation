package com.fhr.readwritedemo.core.global;

import java.util.List;

import javax.sql.DataSource;

/** 
 * 数据库环境 个人感觉这儿最好全是静态
 * @author fhr
 * @date 2017/07/29
 */ 
public class DataSourceSys {
	// 用于写数据的数据源
	public  static DataSource  writeDataSource;
	
	// 用于读数据的数据源
	public  static List<DataSource> readDataSources;
}
