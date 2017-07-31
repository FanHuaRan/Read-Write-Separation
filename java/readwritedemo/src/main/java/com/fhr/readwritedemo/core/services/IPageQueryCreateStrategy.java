package com.fhr.readwritedemo.core.services;

import java.util.List;

/**
 * 分页查询语句生成策略
 * @author fhr
 * @date
 */
public interface IPageQueryCreateStrategy {
	/**
	 * 创建简单的分页查询语句
	 * @param tableName
	 * @param pageIndex
	 * @param count
	 * @param aesFields
	 * @param desFields
	 * @return
	 */
	String createSimpleQuery(String tableName,int pageIndex,int count,List<String> ascFields,List<String> decsFields);
}
