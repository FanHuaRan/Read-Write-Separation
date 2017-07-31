package com.fhr.readwritedemo.core.services;

import com.fhr.readwritedemo.dtomodels.OSystemMonitInfo;

/**
 * 数据库服务器通信接口
 * @author fhr
 * @date
 */
public interface IDSCommunicate {
	//获取操作系统信息
	OSystemMonitInfo getInfo(String host,int port);
}
