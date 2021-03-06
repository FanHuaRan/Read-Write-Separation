package com.fhr.readwritedemo.core.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.fhr.readwritedemo.core.models.DataBaseVisitType;
/**
 * 数据库连接获取接口
 * @author fhr
 * @since 2017/08/01
 */
public interface IGetConnection {
	Connection getWriteConnection() throws SQLException;
	
	Connection getReadConnection() throws SQLException;
	
	Connection getConnection(String sql) throws SQLException;
	
	Connection getConnection(List<String> sqls) throws SQLException;
	
	Connection getConncetion(DataBaseVisitType visitType) throws SQLException;
	
}
