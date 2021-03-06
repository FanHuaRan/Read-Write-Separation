package com.fhr.readwritedemo.core.services.impl;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import com.fhr.readwritedemo.core.models.DataBaseInfo;
import com.fhr.readwritedemo.core.models.DataBaseType;
import com.fhr.readwritedemo.core.services.ICreateDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 数据源创建实现
 * @author fhr
 * @since 2017/07/29
 */
@Service
public class CreateDataSourceImpl implements ICreateDataSource {
	// 连接池最大连接数
	private static final int MAX_POOL_SIZE = 50;
	// 连接池初始连接数
	private static final int INITIAL_POOL_SIZE = 2;
	// 连接池最小连接数
	private static final int MIN_POOL_SIZE = 1;
	// 每个连接的最大Statements数
	private static final int MAX_STATEMENTS = 50;
	// 连接池的最大空闲连接
	private static final int MAX_IDLE_TIME = 60;
	// SQLSERVER驱动名称
	private static final String MSSQL_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	// MySQL
	private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	// Oracle
	private static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";

	// 创建数据源 数据库相关
	@Override
	public DataSource create(DataBaseInfo dataBaseInfo) {
		String driverClass = getDriverClass(dataBaseInfo.getDataBaseType());
		String jdbcUrl = getJdbcUrl(dataBaseInfo.getDataBaseType(), dataBaseInfo.getUrl());
		return createDataSource(driverClass, jdbcUrl, dataBaseInfo.getUser(), dataBaseInfo.getPassword());
	}

	// 获取驱动类 数据库相关
	public String getDriverClass(DataBaseType dataBaseType) {
		if (dataBaseType == DataBaseType.MSSQL) {
			return MSSQL_DRIVER;
		} else if (dataBaseType == DataBaseType.MYSQL) {
			return MYSQL_DRIVER;
		}
		return ORACLE_DRIVER;
	}

	// 获取jdbc连接字符串 数据库相关
	public static String getJdbcUrl(DataBaseType dataBaseType, String url) {
		if (dataBaseType == DataBaseType.MSSQL) {
			return String.format("jdbc:sqlserver://%s", url);
		} else if (dataBaseType == DataBaseType.MYSQL) {
			return String.format("jdbc:mysql://%s", url);
		}
		return String.format("jdbc:oracle:thin:@%s", url);
	}

	// 创建数据库连接池
	private DataSource createDataSource(String driverClass, String url, String userName, String password) {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass(driverClass);
		} catch (Exception e) {
		}
		dataSource.setJdbcUrl(url);
		dataSource.setUser(userName);
		dataSource.setPassword(password);
		dataSource.setMaxPoolSize(MAX_POOL_SIZE);
		dataSource.setInitialPoolSize(INITIAL_POOL_SIZE);
		dataSource.setMinPoolSize(MIN_POOL_SIZE);
		dataSource.setMaxStatements(MAX_STATEMENTS);
		dataSource.setMaxIdleTime(MAX_IDLE_TIME);
		return dataSource;
	}
}
