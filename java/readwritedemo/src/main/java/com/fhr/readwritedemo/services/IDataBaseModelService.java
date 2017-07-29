package com.fhr.readwritedemo.services;

import java.util.List;

import com.fhr.readwritedemo.models.DataBaseInfo;
import com.fhr.readwritedemo.models.DataBaseModel;

/**
 * 数据库初始化服务
 * @author fhr
 * @date
 */
public interface IDataBaseModelService {
	/**
	 * 通过数据库信息对象获取对应的数据库模型
	 * @param dataBaseInfos
	 * @return
	 */
	List<DataBaseModel> getDataBaseModels(List<DataBaseInfo> dataBaseInfos);
}
