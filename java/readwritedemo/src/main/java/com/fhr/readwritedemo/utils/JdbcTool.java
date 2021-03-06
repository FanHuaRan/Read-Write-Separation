package com.fhr.readwritedemo.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * jdbc简单工具
 * @author fhr
 * @since 2017/06/12
 */
public class JdbcTool {
	/**
	 * 通过map插入或者更新记录 主键存在则更新记录，否则插入记录返回自增主键
	 * @param connection
	 * @param tableName
	 * @param fields
	 * @param pkName
	 * @param id
	 * @return 返回主键
	 * @throws SQLException
	 */
	public static Object insertOrUpdateRecordByMap(Connection connection,String tableName,Map<String,Object> fields,String pkName,Object id) throws SQLException{
		//id不为null且通过id查询记录存在则更新记录
		if(id!=null&&recordExist(connection, tableName, pkName, id)){
			updateRecordByMap(connection, tableName, fields, pkName, id);
			return id;
		}else{//插入记录
			return insertRecordByMap(connection, tableName, fields);
		}
	}
	/**
	 * 执行插入记录的sql 返回主键
	 * @param connection
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static Object insertRecord(Connection connection,String sql, List<Object> params) throws SQLException {
		try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			if (params != null && !params.isEmpty()) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(i + 1, params.get(i));
				}
			}
			pstmt.execute();
			try (ResultSet resultSet = pstmt.getGeneratedKeys()) {
				Object key = null;
				if (resultSet.next()) {
					key = resultSet.getObject(1);
				}
				return key;
			}
		}
	}
	/**
	 * 通过map和主键修改单个记录 返回是否成功标识
	 * @param connection
	 * @param tableName
	 * @param fields
	 * @param pkName
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public static boolean updateRecordByMap(Connection connection,String tableName, Map<String, Object> fields, String pkName, Object id)
			throws SQLException {
		//字段名集合
		Set<String> filedNames=fields.keySet();
		//更新记录的sql语句
		String sql = createUpdateSqlByMap(tableName, pkName, filedNames);
		try(PreparedStatement pstmt = connection.prepareStatement(sql)) {
			//设置参数
			int index=1;
			for(String fieldName :filedNames){
				pstmt.setObject(index++, fields.get(fieldName));
			}
			pstmt.setObject(index, id);
			int resultCount = pstmt.executeUpdate();
			//受影响行数>0返回true
			return resultCount > 0 ? true : false;
		}
	}

	/**
	 * 通过map插入记录 返回主键
	 * @param connection
	 * @param tableName
	 * @param rowValues
	 * @return
	 * @throws SQLException
	 */
	public static Object insertRecordByMap(Connection connection,String tableName, Map<String, Object> fields) throws SQLException {
		// 字段名集合
		Set<String> filedNames = fields.keySet();
		// 插入记录的sql语句
		String sql = createInsertSqlByMap(tableName, filedNames);
		try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			// 设置参数
			int index = 1;
			for (String fieldName : filedNames) {
				pstmt.setObject(index++, fields.get(fieldName));
			}
			pstmt.execute();
			// 获取主键
			try (ResultSet resultSet = pstmt.getGeneratedKeys()) {
				Object key = null;
				if (resultSet.next()) {
					key = resultSet.getObject(1);
				}
				return key;
			}
		}
	}
	/**
	 * 检查主键是否存在
	 * @param connection
	 * @param tableName
	 * @param pkName
	 * @param id
	 * @return 
	 * @throws SQLException
	 */
	public static boolean recordExist(Connection connection,String tableName,String pkName,Object id) throws SQLException{
		try (PreparedStatement pstmt = connection.prepareStatement(String.format("select %s from %s where %s =? ", pkName, tableName, pkName))) {
			pstmt.setObject(1, id);
			try (ResultSet resultSet = pstmt.executeQuery()) {
				return resultSet.next();
			}
		}
	}
	
	/**
	 * 判断数据库是否存在表
	 * @param connection
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public static boolean tableExist(Connection connection,String tableName) throws SQLException{
		try (ResultSet resultSet = connection.getMetaData().getTables(null, null, tableName, null);) {
			return resultSet.next();
		}
	}
	
	/**
	 * 判断数据库是否支持批处理 
	 * @param connection
	 * @return
	 */
    public static boolean supportBatch(Connection connection) {
		try {
			// 得到数据库的元数据
			DatabaseMetaData md = connection.getMetaData();
			return md.supportsBatchUpdates();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
    }
	
	/**
	 * 带事务执行批处理
	 * @param connection
	 * @param sqls
	 * @return
	 * @throws SQLException
	 */
	public static int[] executeBatchWithTran(Connection connection,List<String> sqls) throws SQLException{
//		Statement statement = null;
//		try {
//			//设置不自动提交
//			connection.setAutoCommit(false);
//			statement = connection.createStatement();
//			for (String sql : sqls) {
//				statement.addBatch(sql);
//			}
//			// 一次执行多条SQL语句
//			int[] count = statement.executeBatch();
//			return count;
//		} finally {
//			releaseStatement(statement);
//		}
		return null;
	}
	/**
	 * 执行批处理
	 * @param connection
	 * @param sqls
	 * @return
	 * @throws SQLException
	 */
	public static int[] executeBatch(Connection connection,List<String> sqls) throws SQLException{
		try (Statement statement = connection.createStatement()) {
			for (String sql : sqls) {
				statement.addBatch(sql);
			}
			// 一次执行多条SQL语句
			int[] count = statement.executeBatch();
			return count;
		}
	}
	
	/**
	 * 执行带参数的sql语句
	 * @param connection
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static boolean executeSQL(Connection connection,String sql, List<Object> params) throws SQLException {
		try (PreparedStatement pstmt = createNormalPreparedStatement(sql, params, connection)) {
			return pstmt.execute();
		}
	}
	/**
	 * 执行不带参数的sql语句
	 * @param connection
	 * @param sql
	 * @return
	 * @throws SQLException 
	 */
	public static boolean executeSQL(Connection connection,String sql) throws SQLException{
		try(Statement statement=connection.createStatement()){
			return statement.execute(sql);
		}
	}
	/**
	 * 执行不带参数的多个sql语句，
	 * 中间有错误则会立即终止，且没有使用事务
	 * @param connection
	 * @param sqls
	 * @return
	 * @throws SQLException 
	 */
	public static void executeSQLS(Connection connection,List<String> sqls) throws SQLException{
		try(Statement statement=connection.createStatement()){
			for(String sql:sqls){
				statement.execute(sql);
			}
		}
	}
	/**
	 * 查找单个记录
	 * @param connection
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static Map<String, Object> findSingleResultByMap(Connection connection,String sql, List<Object> params) throws SQLException {
		try (PreparedStatement pstmt = createNormalPreparedStatement(sql, params, connection);
			ResultSet resultSet = pstmt.executeQuery()) {
			Map<String, Object> map = null;
			ResultSetMetaData metaData = resultSet.getMetaData();
			int col_len = metaData.getColumnCount();
			while (resultSet.next()) {
				map = getSingleHashMap(resultSet, metaData, col_len);
				break;
			}
			return map;
		}
	}

	/**
	 * 查找多个记录
	 * @param connection
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static List<Map<String, Object>> findMoreResultByMap(Connection connection,String sql, List<Object> params) throws SQLException {
		try (PreparedStatement pstmt = createNormalPreparedStatement(sql, params, connection);
			ResultSet resultSet = pstmt.executeQuery()) {
			ResultSetMetaData metaData = resultSet.getMetaData();
			int cols_len = metaData.getColumnCount();
			List<Map<String, Object>> list = new ArrayList<>();
			while (resultSet.next()) {
				list.add(getSingleHashMap(resultSet, metaData, cols_len));
			}
			return list;
		}
	}
	/**
	 * 删除记录
	 * @param connection
	 * @param tableName
	 * @param primaryKey
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public static boolean deleteRecord(Connection connection,String tableName, String primaryKey, Object id) throws SQLException {
		String sql = createNormalDeleteSql(tableName, primaryKey);
		try(PreparedStatement pstmt=connection.prepareStatement(sql);) {
			pstmt.setObject(1, id);
			int resultCount = pstmt.executeUpdate();
			return resultCount > 0 ? true : false;
		}
	}
	
	/**
	 * 得到表主键名
	 * @param connection
	 * @param tbname
	 * @return
	 * @throws SQLException
	 */
	public static String getPrimaryKeyName(Connection connection,String tableName) throws SQLException {
		// 元数据信息
		DatabaseMetaData dmd = connection.getMetaData();
		// 得到主键
		try (ResultSet pkResultSet = dmd.getPrimaryKeys(null, null, tableName)) {
			// 得到主键字段名称
			if (pkResultSet.next()) {
				return pkResultSet.getString(4);// 为什么是get(4)
			} else {
				return null;
			}
		}
	}
	/**
	 * 获取表中主键最大值
	 * @param connection
	 * @param tableName
	 * @param pkName
	 * @return
	 * @throws SQLException 
	 */
	public static int getMaxPkId(Connection connection,String tableName,String pkName) throws SQLException{
		String sql = String.format("select max(%s) as %s from %s, args)", pkName, pkName, tableName);
		int maxId=0;
		try (Statement sm = connection.createStatement(); 
			  ResultSet resultSet = sm.executeQuery(sql);) {
			if (resultSet.next()) {
				maxId=resultSet.getInt(pkName);
			}
		}
		return maxId;
	}
	/**
	 * 执行简单查询 这儿就不能释放资源了
	 * @param connection
	 * @param sql
	 * @return
	 */
	public static ResultSet simpleQuery(Connection connection,String sql){
		try{
			PreparedStatement statement = connection.prepareStatement(sql);
			return 	statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/***************************** 私有方法 *******************************/
	/**
	 * 根据filedNames创建插入sql
	 * @param tableName
	 * @param filedNames
	 * @return
	 */
	private static String createInsertSqlByMap(String tableName, Set<String> filedNames) {
		StringBuilder builder = new StringBuilder("insert  into " + tableName);
		// 使用lambda表达式拼接:(字段名1，字段名2)
		builder.append(filedNames.stream().collect(Collectors.joining(",", " ( ", " ) values")));
		// 使用lambda表达式拼接:(?，?)
		builder.append(filedNames.stream().map(p -> "?").collect(Collectors.joining(",", "(", ")")));
		return builder.toString();
	}

	/**
	 * 根据tablename、主键字段名和filedNames创建修改sql
	 * @param tableName
	 * @param primaryKey
	 * @param keys
	 * @return
	 */
	private static String createUpdateSqlByMap(String tableName, String pkName, Set<String> fieldNames) {
		StringBuilder builder = new StringBuilder();
		builder.append("update  " + tableName + " set  ");
		// 使用lambda表达式拼接：字段名=？
		builder.append(fieldNames.stream().collect(Collectors.joining("=?,", " ", "=?")));
		builder.append(" where " + pkName + "=?");
		return builder.toString();
	}
		
	/**
	 * 根据sql和参数数组创建PreparedStatement
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	private static PreparedStatement createNormalPreparedStatement(String sql, List<Object> params,
			Connection connection) throws SQLException {
		PreparedStatement pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(i + 1, params.get(i));
			}
		}
		return pstmt;
	}

	/**
	 * 创建删除sql
	 * @param tableName
	 * @param primaryKey
	 * @return
	 */
	private static String createNormalDeleteSql(String tableName, String primaryKey) {
		StringBuilder builder = new StringBuilder("delete from " + tableName + " ");
		builder.append(" where " + primaryKey + "=?");
		String sql = builder.toString();
		return sql;
	}

	/**
	 * 获取一个map 也就是一行
	 * @param resultSet
	 * @param metaData
	 * @param col_len
	 * @return
	 * @throws SQLException
	 */
	private static Map<String, Object> getSingleHashMap(ResultSet resultSet, ResultSetMetaData metaData, int col_len)
			throws SQLException {
		Map<String, Object> map = new HashMap<>();
		for (int i = 0; i < col_len; i++) {
			String cols_name = metaData.getColumnName(i + 1);
			Object cols_value = resultSet.getObject(cols_name);
			map.put(cols_name, cols_value);
		}
		return map;
	}
	
	/**
	 * 显示释放资源 
	 * @param resources
	 */
	private static void relaseResources(AutoCloseable ...resources) {
		for(AutoCloseable resource :resources){
			try {
				if (resource != null) {
					resource.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
