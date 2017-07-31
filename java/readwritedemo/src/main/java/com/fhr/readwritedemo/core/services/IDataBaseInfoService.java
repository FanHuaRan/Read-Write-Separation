package com.fhr.readwritedemo.core.services;

import java.util.List;

import com.fhr.readwritedemo.core.models.DataBaseInfo;

/**
 * 数据库信息服务
 * @author fhr
 * @date 2017/07/29
 */
public interface IDataBaseInfoService {

	List<DataBaseInfo> getDataBaseInfos();
}
