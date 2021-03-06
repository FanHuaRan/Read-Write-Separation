package com.fhr.readwritedemo.core.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 自定义jdbc操作接口
 * @author fhr
 * @since 2017/08/30
 */
public interface ICustomJdbcOperations {
	/**
	 * 通过map插入或者更新记录 主键存在则更新记录，否则插入记录返回自增主键
	 * @param tableName
	 * @param fields
	 * @param pkName
	 * @param id
	 * @return 返回主键
	 * @throws SQLException
	 */
	Object insertOrUpdateRecordByMap(String tableName, Map<String, Object> fields, String pkName, Object id)
			throws SQLException;

	/**
	 * 执行插入记录的sql 返回主键
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	Object insertRecord(String sql, List<Object> params) throws SQLException;

	/**
	 * 通过map和主键修改单个记录 返回是否成功标识
	 * @param tableName
	 * @param fields
	 * @param pkName
	 * @param id
	 * @return 
	 * @throws SQLException
	 */
	boolean updateRecordByMap(String tableName, Map<String, Object> fields, String pkName, Object id)
			throws SQLException;

	/**
	 * 通过map插入记录 返回主键
	 * @param tableName
	 * @param rowValues
	 * @return
	 * @throws SQLException
	 */
	Object insertRecordByMap(String tableName, Map<String, Object> fields) throws SQLException;

	/**
	 * 检查主键是否存在
	 * @param connection
	 * @param tableName
	 * @param pkName
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	boolean recordExist(String tableName, String pkName, Object id) throws SQLException;

	/**
	 * 判断数据库是否存在表
	 * @param connection
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	boolean tableExist(String tableName) throws SQLException;

	/**
	 * 带事务执行批处理
	 * @param connection
	 * @param sqls
	 * @return
	 * @throws SQLException
	 */
	int[] executeBatchWithTran(List<String> sqls) throws SQLException;

	/**
	 * 执行批处理
	 * 
	 * @param connection
	 * @param sqls
	 * @return
	 * @throws SQLException
	 */
	int[] executeBatch(List<String> sqls) throws SQLException;

	/**
	 * 执行带参数的sql语句
	 * @param connection
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	boolean executeSQL(String sql, List<Object> params) throws SQLException;

	/**
	 * 执行不带参数的sql语句
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	boolean executeSQL(String sql) throws SQLException;

	/**
	 * 执行不带参数的多个sql语句， 中间有错误则会立即终止，且没有使用事务
	 * @param sqls
	 * @return
	 * @throws SQLException
	 */
	void executeSQLS(List<String> sqls) throws SQLException;

	/**
	 * 查找单个记录
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	Map<String, Object> findSingleResultByMap(String sql, List<Object> params) throws SQLException;

	/**
	 * 查找多个记录
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	List<Map<String, Object>> findMoreResultByMap(String sql, List<Object> params) throws SQLException;

	/**
	 * 删除记录
	 * @param tableName
	 * @param primaryKey
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	boolean deleteRecord(String tableName, String primaryKey, Object id) throws SQLException;

	/**
	 * 得到表主键名
	 * @param tbname
	 * @param con
	 * @return	
	 * @throws SQLException
	 */
	String getPrimaryKeyName(String tableName) throws SQLException;

	/**
	 * 获取表中主键最大值
	 * @param tableName
	 * @param pkName
	 * @return
	 * @throws SQLException
	 */
	int getMaxPkId(String tableName, String pkName) throws SQLException;
	/**
	 * 分页查询
	 * @param tableName
	 * @param pageIndex
	 * @param count
	 * @param ascFields
	 * @param decsFields
	 * @return
	 * @throws SQLException
	 */
	List<Map<String,Object>> findRecordByPage(String tableName,int pageIndex,int count,List<String> ascFields,List<String> decsFields) throws SQLException;

}
