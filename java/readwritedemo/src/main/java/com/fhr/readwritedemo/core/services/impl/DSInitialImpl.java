package com.fhr.readwritedemo.core.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fhr.readwritedemo.core.global.DataSourceSys;
import com.fhr.readwritedemo.core.models.DataBaseInfo;
import com.fhr.readwritedemo.core.models.DataBaseModel;
import com.fhr.readwritedemo.core.models.DataBaseVisitType;
import com.fhr.readwritedemo.core.services.IDSInitial;
import com.fhr.readwritedemo.core.services.IDataBaseInfoService;
import com.fhr.readwritedemo.core.services.IDataBaseModelService;

/**
 * 数据库初始化实现
 * @author fhr
 * @since 2017/08/02
 */
@Service
public class DSInitialImpl implements IDSInitial {
	@Autowired
	private IDataBaseModelService dataBaseModelService = null;
	@Autowired
	private IDataBaseInfoService dataBaseInfoService = null;

	@Override
	public Map<String, DataBaseInfo> inital() {
		// 获取所有的数据库信息
		List<DataBaseInfo> dataBaseInfos = dataBaseInfoService.getDataBaseInfos();
		// 根据所有的数据库信息获取所有的数据库模型，内含连接池
		List<DataBaseModel> dataBaseModels = dataBaseModelService.getDataBaseModels(dataBaseInfos);
		// 获取写数据模型
		DataBaseModel writeDataModel = dataBaseModels.stream().filter(p -> p.isMaster()).findFirst().get();
		// 获取读数据源
		Map<String, DataSource> readDataSourcesMap = dataBaseModels.stream().filter(p -> !p.isMaster())
				.collect(Collectors.toMap(p -> p.getDataBaseInfo().getHost(), DataBaseModel::getDataSource));
		// 获取读数据的数据信息
		Map<String, DataBaseInfo> readDataBaseInfos = dataBaseInfos.stream()
				.filter(p -> p.getDataBaseVisitType() == DataBaseVisitType.SLAVE)
				.collect(Collectors.toMap(DataBaseInfo::getHost, p -> p));
		// 初始化权重
		final Map<String, Integer> intialWeight = new HashMap<>();
		readDataBaseInfos.values().stream().forEach(p -> intialWeight.put(p.getHost(), 1));
		// 添加到全局变量当中
		DataSourceSys.setWriteDataSource(writeDataModel.getDataSource());// 写数据源
		DataSourceSys.setReadDataSources(readDataSourcesMap.values());// 读数据源
		DataSourceSys.setHostReadDataSourcesMap(readDataSourcesMap); // 读数据源映射
		DataSourceSys.setServerWeights(intialWeight); // 初始化权重
		return readDataBaseInfos;
	}
}
