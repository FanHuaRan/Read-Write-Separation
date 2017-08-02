package com.fhr.readwritedemo.core.models;

import java.text.MessageFormat;

/**
 * 数据库服务器信息
 * @author fhr
 * @date 2017/07/28
 */
public class DataBaseInfo implements java.io.Serializable {
	private static final long serialVersionUID = 8611633278205294077L;
	// 数据库服务器的地址
	private final String host;
	// 数据库访问地址
	private final String url;
	// 用户名
	private final String user;
	// 密码
	private final String password;
	// 数据库类型 缺省为mysql
	private final DataBaseType dataBaseType; 
	// 数据库访问类型 主库或从库 缺省为从库
	private final DataBaseVisitType dataBaseVisitType;

	public DataBaseInfo(String host,String url, String user, String password, DataBaseType dataBaseType,DataBaseVisitType dataBaseVisitType) {
		super();
		this.host=host;
		this.url = url;
		this.user = user;
		this.password = password;
		this.dataBaseType=dataBaseType;
		this.dataBaseVisitType = dataBaseVisitType;
	}

	public DataBaseInfo(String host,String url, String user, String password,DataBaseType dataBaseType) {
		this(host,url, user, password, dataBaseType,DataBaseVisitType.MASTER);
	}
	
	public String getHost() {
		return host;
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
	
	public DataBaseType getDataBaseType() {
		return dataBaseType;
	}

	public DataBaseVisitType getDataBaseVisitType() {
		return dataBaseVisitType;
	}
	
	@Override
	public String toString(){
		return MessageFormat.format("{url:{0},user:{1},password:{2},databasetype:{3},visittype:{4}}", url,user,password,dataBaseType,dataBaseVisitType);
	}

}
