package com.fhr.readwritedemo.core.services;

import java.util.List;

import com.fhr.readwritedemo.core.models.DataBaseInfo;

/**
 * 数据库信息服务
 * @author fhr
 * @since 2017/07/29
 */
public interface IDataBaseInfoService {
	
	/**
	 * 获取所有的数据库信息
	 * @return
	 */
	List<DataBaseInfo> getDataBaseInfos();
}
