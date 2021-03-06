package com.fhr.readwritedemo.components;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fhr.readwritedemo.core.services.IDSInitial;
import com.fhr.readwritedemo.core.services.IDSMonitor;
import com.fhr.readwritedemo.core.services.impl.DSInitialImpl;
import com.fhr.readwritedemo.core.services.impl.UdpDSMonitor;

/**
 * web生命周期监听器
 * @author fhr
 * @since 2017/07/12
 */
public class InitListener implements ServletContextListener {
	private static final Logger logger = Logger.getLogger(InitListener.class);

	// 数据库服务器监控器
	private IDSMonitor dsMonitor = null;
	
	private IDSInitial dsInitial=null;
	
	// web结束
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//关闭监控
		try {
			dsMonitor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//web初始化
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// 获取spring上下文
		WebApplicationContext springContext = WebApplicationContextUtils
				.getWebApplicationContext(arg0.getServletContext());
		if (springContext == null) {
			logger.error("初始化错误 未找到spring上下文");
		} else {
			//数据库初始化工具
			dsInitial=(IDSInitial)springContext.getBean(DSInitialImpl.class);
			// 获取数据库服务器监控器
			dsMonitor = (IDSMonitor) springContext.getBean(UdpDSMonitor.class);
			dsMonitor.setDataBaseInfos(dsInitial.inital());
			dsMonitor.start();
//			if (dsMonitor != null) {
//				// 运行监控程序
//				dsMonitor.start();
//			} else {
//				logger.error("初始化错误 未找到monitor服务组件");
//			}
		}
	}

}
