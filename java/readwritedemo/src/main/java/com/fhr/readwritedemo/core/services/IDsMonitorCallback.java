package com.fhr.readwritedemo.core.services;

import java.util.List;

import com.fhr.osmonitor.dtos.OSystemMonitInfo;

/**
 * 数据库服务器检测回调函数接口
 * @author fhr
 * @since 2017/07/31
 */
public interface IDsMonitorCallback {
	// 回调函数
	void callCack(List<OSystemMonitInfo> oSystemMonitInfos);
}
