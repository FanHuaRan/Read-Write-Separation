package com.fhr.readwritedemo.core.services;

import java.util.Map;

import com.fhr.readwritedemo.core.models.DataBaseInfo;

/**
 * 数据库初始化接口
 * @author fhr
 * @since 2017/08/02
 */
public interface IDSInitial {
	
	Map<String,DataBaseInfo>  inital();
}
