package com.fhr.readwritedemo.core.services.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fhr.readwritedemo.core.services.IGetConnection;
import com.fhr.readwritedemo.core.services.ICustomJdbcOperations;
import com.fhr.readwritedemo.core.services.IPageQueryCreateStrategy;
import com.fhr.readwritedemo.utils.JdbcTool;

/**
 * 读写分离的jdbctemplate的实现
 * @author fhr
 * @since 2017/08/30
 */
@Service
public class ReadWriteJdbcTemplate implements ICustomJdbcOperations {
	//分页查询语句生成器
	@Autowired
	private IPageQueryCreateStrategy pageQueryCreateStrategy=null;
	//连接池管理组件
	@Autowired
	private IGetConnection connectionGetService = null;

	@Override
	public Object insertOrUpdateRecordByMap(String tableName, Map<String, Object> fields, String pkName, Object id)
			throws SQLException {
		try (Connection connection = connectionGetService.getWriteConnection()) {
			return JdbcTool.insertOrUpdateRecordByMap(connection, tableName, fields, pkName, id);
		}
	}

	@Override
	public Object insertRecord(String sql, List<Object> params) throws SQLException {
		try (Connection connection = connectionGetService.getWriteConnection()) {
			return JdbcTool.insertRecord(connection, sql, params);
		}
	}

	@Override
	public boolean updateRecordByMap(String tableName, Map<String, Object> fields, String pkName, Object id)
			throws SQLException {
		try (Connection connection = connectionGetService.getWriteConnection()) {
			return JdbcTool.updateRecordByMap(connection, tableName, fields, pkName, id);
		}
	}

	@Override
	public Object insertRecordByMap(String tableName, Map<String, Object> fields) throws SQLException {
		try (Connection connection = connectionGetService.getWriteConnection()) {
			return JdbcTool.insertRecordByMap(connection, tableName, fields);
		}
	}

	@Override
	public boolean recordExist(String tableName, String pkName, Object id) throws SQLException {
		try (Connection connection = connectionGetService.getReadConnection()) {
			return JdbcTool.recordExist(connection, tableName, pkName, id);
		}
	}

	@Override
	public boolean tableExist(String tableName) throws SQLException {
		try (Connection connection = connectionGetService.getReadConnection()) {
			return JdbcTool.tableExist(connection, tableName);
		}
	}

	@Override
	public int[] executeBatchWithTran(List<String> sqls) throws SQLException {
		try (Connection connection = connectionGetService.getConnection(sqls)) {
			return JdbcTool.executeBatchWithTran(connection, sqls);
		}
	}

	@Override
	public int[] executeBatch(List<String> sqls) throws SQLException {
		try (Connection connection = connectionGetService.getConnection(sqls)) {
			return JdbcTool.executeBatch(connection, sqls);
		}
	}

	@Override
	public boolean executeSQL(String sql, List<Object> params) throws SQLException {
		try (Connection connection = connectionGetService.getConnection(sql)) {
			return JdbcTool.executeSQL(connection, sql, params);
		}
	}

	@Override
	public boolean executeSQL(String sql) throws SQLException {
		try (Connection connection = connectionGetService.getConnection(sql)) {
			return JdbcTool.executeSQL(connection, sql);
		}
	}

	@Override
	public void executeSQLS(List<String> sqls) throws SQLException {
		try (Connection connection = connectionGetService.getConnection(sqls)) {
			JdbcTool.executeSQLS(connection, sqls);
		}
	}

	@Override
	public Map<String, Object> findSingleResultByMap(String sql, List<Object> params) throws SQLException {
		try (Connection connection = connectionGetService.getReadConnection()) {
			return JdbcTool.findSingleResultByMap(connection, sql, params);
		}
	}

	@Override
	public List<Map<String, Object>> findMoreResultByMap(String sql, List<Object> params) throws SQLException {
		try (Connection connection = connectionGetService.getReadConnection()) {
			return JdbcTool.findMoreResultByMap(connection, sql, params);
		}
	}

	@Override
	public boolean deleteRecord(String tableName, String primaryKey, Object id) throws SQLException {
		try (Connection connection = connectionGetService.getWriteConnection()) {
			return JdbcTool.deleteRecord(connection, tableName, primaryKey, id);
		}
	}

	@Override
	public String getPrimaryKeyName(String tableName) throws SQLException {
		try (Connection connection = connectionGetService.getReadConnection()) {
			return JdbcTool.getPrimaryKeyName(connection, tableName);
		}
	}

	@Override
	public int getMaxPkId(String tableName, String pkName) throws SQLException {
		try (Connection connection = connectionGetService.getReadConnection()) {
			return JdbcTool.getMaxPkId(connection, tableName, pkName);
		}
	}

	@Override
	public List<Map<String, Object>> findRecordByPage(String tableName,int pageIndex,int count,List<String> ascFields,List<String> decsFields) throws SQLException {
		try(Connection connection=connectionGetService.getReadConnection()){
			String sql=pageQueryCreateStrategy.createSimpleQuery(tableName, pageIndex, count, ascFields, decsFields);
			return JdbcTool.findMoreResultByMap(connection, sql, null);
		}
	}

}
