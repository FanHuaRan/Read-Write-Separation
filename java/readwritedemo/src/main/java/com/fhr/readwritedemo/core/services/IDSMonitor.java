package com.fhr.readwritedemo.core.services;

import java.io.Closeable;
import java.util.Map;

import com.fhr.readwritedemo.core.models.DataBaseInfo;

/**
 * 数据库服务器集群监控
 * @author fhr
 * @since 2017/07/31
 */
public interface IDSMonitor extends Closeable {
	
	// 开始监控
	void start();
	
	//设置监控对象
	void setDataBaseInfos(Map<String,DataBaseInfo> dataBaseInfos);
}
