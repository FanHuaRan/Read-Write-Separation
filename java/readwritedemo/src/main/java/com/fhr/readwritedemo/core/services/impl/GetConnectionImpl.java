package com.fhr.readwritedemo.core.services.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fhr.readwritedemo.core.global.DataSourceSys;
import com.fhr.readwritedemo.core.models.DataBaseVisitType;
import com.fhr.readwritedemo.core.services.IGetConnection;

/**
 * 数据库连接获取服务
 * @author fhr
 * @since 2017/08/01
 */
@Service
public class GetConnectionImpl implements IGetConnection {

	@Override
	public Connection getWriteConnection() throws SQLException {
		return DataSourceSys.getWriteDataSource().getConnection();
	}

	@Override
	public Connection getReadConnection() throws SQLException {
		return DataSourceSys.getReadDataSource().getConnection();
	}

	@Override
	public Connection getConnection(String sql) throws SQLException {
		if (isWrite(sql)) {
			return getWriteConnection();
		}
		return getReadConnection();
	}

	@Override
	public Connection getConnection(List<String> sqls) throws SQLException {
		if (isWrite(sqls)) {
			return getWriteConnection();
		}
		return getReadConnection();
	}

	@Override
	public Connection getConncetion(DataBaseVisitType visitType) throws SQLException {
		if (visitType == DataBaseVisitType.MASTER) {
			return getWriteConnection();
		}
		return getReadConnection();
	}

	// 根据sql判断是否是写操作
	private final boolean isWrite(String sql) {
		for (String writeFlag : SQL_WRITE_FLAGS) {
			if (sql.contains(writeFlag)) {
				return true;
			}
		}
		return false;
	}

	// 根据sql语句集合判断是否是写操作
	private final boolean isWrite(List<String> sqls) {
		for (String sql : sqls) {
			if (isWrite(sql)) {
				return true;
			}
		}
		return false;
	}

	// 写操作的关键词
	private static final String[] SQL_WRITE_FLAGS = { "update", "delete", "insert", "call" };
}
