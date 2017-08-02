package com.fhr.readwritedemo.core.models;

import javax.sql.DataSource;

/**
 * 数据库模型
 * @author fhr
 * @date 2017/07/28
 */
public class DataBaseModel implements java.io.Serializable {
	private static final long serialVersionUID = 4540473938989375233L;
	//数据库信息
	private final DataBaseInfo dataBaseInfo;
	//数据库连接池
	private final DataSource dataSource;
	//是否是主库  缺省false
	private final boolean isMaster;
	
	public DataBaseModel(DataBaseInfo dataBaseInfo, DataSource dataSource, boolean isMaster) {
		super();
		this.dataBaseInfo = dataBaseInfo;
		this.dataSource = dataSource;
		this.isMaster = isMaster;
	}

	public DataBaseModel(DataBaseInfo dataBaseInfo, DataSource dataSource) {
		this(dataBaseInfo, dataSource, false);
	}
	public DataBaseInfo getDataBaseInfo() {
		return dataBaseInfo;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public boolean isMaster() {
		return isMaster;
	}
	
	@Override
	public String toString(){
		return String.format("{databaseInfo:{%s},datasource:%s,isMaster:%b}",dataBaseInfo,dataSource,isMaster);
	}
	
	
}
