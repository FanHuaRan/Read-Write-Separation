package com.fhr.readwritedemo.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fhr.readwritedemo.core.models.DataBaseInfo;
import com.fhr.readwritedemo.core.models.DataBaseModel;
import com.fhr.readwritedemo.core.models.DataBaseVisitType;
import com.fhr.readwritedemo.core.services.ICreateDataSource;
import com.fhr.readwritedemo.core.services.IDataBaseModelService;
/**
 * 数据库模型服务实现
 * @author fhr
 * @since 2017/07/29
 */
@Service
public class DataBaseModelServiceImpl implements IDataBaseModelService{
	private static final Logger logger=Logger.getLogger(DataBaseModelServiceImpl.class);
	
	@Autowired
	private ICreateDataSource createDataSourceService=null;
	
	/**
	 * 获取数据库模型集合并且同时创建数据源
	 */
	@Override
	public 	List<DataBaseModel> getDataBaseModels(List<DataBaseInfo> dataBaseInfos){
		List<DataBaseModel> dataBaseModels=new ArrayList<>();
		for(DataBaseInfo dataBaseInfo:dataBaseInfos){
			DataSource dataSource=createDataSourceService.create(dataBaseInfo);
			boolean isMaster=dataBaseInfo.getDataBaseVisitType()==DataBaseVisitType.MASTER;
			dataBaseModels.add(new DataBaseModel(dataBaseInfo, dataSource,isMaster));
		}
		return dataBaseModels;
	}

}
