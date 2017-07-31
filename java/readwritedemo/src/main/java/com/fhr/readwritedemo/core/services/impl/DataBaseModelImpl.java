package com.fhr.readwritedemo.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fhr.readwritedemo.core.models.DataBaseInfo;
import com.fhr.readwritedemo.core.models.DataBaseModel;
import com.fhr.readwritedemo.core.services.ICreateDataSourceService;
import com.fhr.readwritedemo.core.services.IDataBaseModelService;
/**
 * 数据库模型服务实现
 * @author fhr
 * @date 2017/07/29
 */
@Service
public class DataBaseModelImpl implements IDataBaseModelService{
	private static final Logger logger=Logger.getLogger(DataBaseModelImpl.class);
	@Autowired
	private ICreateDataSourceService createDataSourceService=null;
	
	//获取数据库模型集合
	@Override
	public 	List<DataBaseModel> getDataBaseModels(List<DataBaseInfo> dataBaseInfos){
		List<DataBaseModel> dataBaseModels=new ArrayList<>();
		for(DataBaseInfo dataBaseInfo:dataBaseInfos){
			DataSource dataSource=createDataSourceService.createDataSource(dataBaseInfo);
			dataBaseModels.add(new DataBaseModel(dataBaseInfo, dataSource));
		}
		return dataBaseModels;
	}

}
