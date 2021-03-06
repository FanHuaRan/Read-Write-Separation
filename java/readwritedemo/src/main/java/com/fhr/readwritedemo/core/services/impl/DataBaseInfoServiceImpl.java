package com.fhr.readwritedemo.core.services.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fhr.readwritedemo.core.global.GlobalConfig;
import com.fhr.readwritedemo.core.models.DataBaseInfo;
import com.fhr.readwritedemo.core.models.DataBaseType;
import com.fhr.readwritedemo.core.models.DataBaseVisitType;
import com.fhr.readwritedemo.core.services.IDataBaseInfoService;

/**
 * 数据库信息服务实现
 * @author fhr
 * @since 2017/07/29
 */
@Service
public class DataBaseInfoServiceImpl implements IDataBaseInfoService {
	private static final Logger logger = Logger.getLogger(DataBaseInfoServiceImpl.class);

	@Autowired
	private GlobalConfig gloableConfig = null;

	// 获取数据库信息 从配置文件
	@Override
	public List<DataBaseInfo> getDataBaseInfos() {
		File configFile = new File(gloableConfig.getDatabaseConfigFile());
		if (!configFile.exists()) {
			logger.error("数据库配置文件不存在");
			return null;
		}
		try {
			String dataBaseConfigStr = FileUtils.readFileToString(configFile);
			JSONArray jsonArray = JSONArray.parseArray(dataBaseConfigStr);
			List<DataBaseInfo> dataBaseInfos = new ArrayList<>();
			for (Object value : jsonArray) {
				JSONObject jsonObject = (JSONObject) value;
				String host = jsonObject.getString("host");
				String url = jsonObject.getString("url");
				String user = jsonObject.getString("user");
				String password = jsonObject.getString("password");
				DataBaseType dataBaseType = getDataBaseType(jsonObject.getInteger("databasetype"));
				DataBaseVisitType visittype = getDataBaseVisitType(jsonObject.getInteger("visittype"));
				dataBaseInfos.add(new DataBaseInfo(host, url, user, password, dataBaseType, visittype));
			}
			return dataBaseInfos;
		} catch (Exception e) {
			logger.error("数据库配置出问题");
			e.printStackTrace();
			return null;
		}
	}

	// 获取数据库类型
	private DataBaseType getDataBaseType(Integer value) {
		if (value == null || value < 1|| value > DataBaseType.values().length) {
			logger.error("数据库配置DataBaseType不合法，使用缺省数据库类型");
			return DataBaseType.MYSQL;
		}
		return DataBaseType.values()[value-1];
	}

	// 获取数据库访问类型
	private DataBaseVisitType getDataBaseVisitType(Integer value) {
		if (value == null || value < 1 || value > DataBaseVisitType.values().length) {
			logger.error("数据库配置DataBaseVisitType不合法，使用缺省数据库访问类型");
			return DataBaseVisitType.SLAVE;
		}
		return DataBaseVisitType.values()[value-1];
	}
}
