package com.fhr.readwritedemo.core.global;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.fhr.readwritedemo.utils.RandomAloUtils;

/**
 * 数据库环境 个人感觉这儿最好全是静态
 * @author fhr
 * @since 2017/07/29
 */
public class DataSourceSys {
	// 用于写数据的数据源
	private static DataSource WriteDataSource;

	// 用于读数据的数据源
	private static Collection<DataSource> ReadDataSources;

	// key为host value为DataSource
	private static Map<String, DataSource> HostReadDataSourcesMap;
	
	// 数据库服务器权重 暂时使用volatile关键字保证线程安全 
	// 每次检测完之后重新计算替换ServerWeights 
	private static volatile Map<String, Integer> ServerWeights;
	
	// 通过算法计算出合适的数据库源
	public static DataSource getReadDataSource(){
		//加权随机算法计算出随机的数据库服务器
	 	String correctHost=RandomAloUtils.weightedRandom(ServerWeights);
	 	return HostReadDataSourcesMap.get(correctHost);
	}
	
	//getters setters
	public static DataSource getWriteDataSource() {
		return WriteDataSource;
	}

	public static void setWriteDataSource(DataSource writeDataSource) {
		WriteDataSource = writeDataSource;
	}

	public static Collection<DataSource> getReadDataSources() {
		return ReadDataSources;
	}

	public static void setReadDataSources(Collection<DataSource> readDataSources) {
		ReadDataSources = readDataSources;
	}

	public static Map<String, DataSource> getHostReadDataSourcesMap() {
		return HostReadDataSourcesMap;
	}

	public static void setHostReadDataSourcesMap(Map<String, DataSource> hostReadDataSourcesMap) {
		HostReadDataSourcesMap = hostReadDataSourcesMap;
	}

	public static Map<String, Integer> getServerWeights() {
		return ServerWeights;
	}

	public static void setServerWeights(Map<String, Integer> serverWeights) {
		ServerWeights = serverWeights;
	}
	
	
	
}
