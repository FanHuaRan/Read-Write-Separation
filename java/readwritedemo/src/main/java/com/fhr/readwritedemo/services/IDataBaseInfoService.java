package com.fhr.readwritedemo.services;

import java.util.List;

import com.fhr.readwritedemo.models.DataBaseInfo;

/**
 * 数据库信息服务
 * @author fhr
 * @date 2017/07/29
 */
public interface IDataBaseInfoService {

	List<DataBaseInfo> getDataBaseInfos();
}
