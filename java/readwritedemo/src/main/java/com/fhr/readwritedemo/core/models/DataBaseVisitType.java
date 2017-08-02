package com.fhr.readwritedemo.core.models;
/**
 * 数据库访问类型
 * @author fhr
 * @since 2017/07/28 
 */
public enum DataBaseVisitType {
	MASTER,//主库 用于写
	SLAVE//从库 用于读
}
