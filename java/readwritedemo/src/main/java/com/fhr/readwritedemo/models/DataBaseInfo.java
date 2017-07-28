package com.fhr.readwritedemo.models;

import javax.sql.rowset.JdbcRowSet;

/**
 * 数据库模型
 * 
 * @author fhr
 * @date 2017/07/28
 */
public class DataBaseInfo implements java.io.Serializable {
	private static final long serialVersionUID = 8611633278205294077L;

	// 数据库路径
	private final String url;
	// 用户名
	private final String user;
	// 密码
	private final String password;
	// 数据库类型 主或从 缺省为从
	private final DataBaseVisitType dataBaseVisitType;

	public DataBaseInfo(String url, String user, String password, DataBaseVisitType dataBaseVisitType) {
		super();
		this.url = url;
		this.user = user;
		this.password = password;
		this.dataBaseVisitType = dataBaseVisitType;
	}

	public DataBaseInfo(String url, String user, String password) {
		this(url, user, password, DataBaseVisitType.MASTER);
	}

	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public DataBaseVisitType getDataBaseVisitType() {
		return dataBaseVisitType;
	}
	
	@Override
	public String toString(){
		return String.format("{url:%s,user:%s,password:%s,visittype:%d}",url,user,password,dataBaseVisitType);
	}

}
