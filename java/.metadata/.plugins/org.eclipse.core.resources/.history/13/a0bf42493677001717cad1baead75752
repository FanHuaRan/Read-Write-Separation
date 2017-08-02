package com.fhr.readwritedemo.core.global;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 全局配置
 * @author fhr
 * @date 2017/07/29
 */
@Component
public class GlobalConfig {
	// 数据库信息配置文件的相对路径
	private static final String RELATIVE_DATABASE_CONFIG_FILE = "databases.json";
	// web根目录
	public final String webRoot;
	// 数据库配置文件路径
	public final String databaseConfigFile;
	// servlet上下文
	private ServletContext servletContext;
	@Autowired
	public GlobalConfig(ServletContext servletContext){
		this.servletContext=servletContext;
		webRoot = servletContext.getRealPath("/WEB-INF/classes/databases.json");
		databaseConfigFile = webRoot + "/" + RELATIVE_DATABASE_CONFIG_FILE;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public String getWebRoot() {
		return webRoot;
	}

	public String getDatabaseConfigFile() {
		return databaseConfigFile;
	}

}
