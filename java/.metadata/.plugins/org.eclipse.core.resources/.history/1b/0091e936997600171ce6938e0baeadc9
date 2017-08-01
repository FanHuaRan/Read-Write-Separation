package com.fhr.readwritedemo.core.services;

import java.sql.Connection;
import java.util.List;

import com.fhr.readwritedemo.core.models.DataBaseVisitType;

public interface IGetConnection {
	Connection getWriteConnection();
	
	Connection getReadConnection();
	
	Connection getConnection(String sql);
	
	Connection getConnection(List<String> sqls);
	
	Connection getConncetion(DataBaseVisitType visitType);
	
}
