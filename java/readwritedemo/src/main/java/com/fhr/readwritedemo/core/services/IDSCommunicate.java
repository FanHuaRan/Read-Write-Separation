package com.fhr.readwritedemo.core.services;

import com.fhr.osmonitor.dtos.OSystemMonitInfo;

/**
 * 数据库服务器通信接口
 * @author fhr
 * @since 2017/08/01
 */
public interface IDSCommunicate {
	/**
	 * 获取操作系统信息
	 * @param host
	 * @param port
	 * @param timeout
	 * @return
	 */
	OSystemMonitInfo getInfo(String host,int port,int timeout);
}
