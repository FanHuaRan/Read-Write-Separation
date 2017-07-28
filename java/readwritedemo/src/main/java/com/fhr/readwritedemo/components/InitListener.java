package com.fhr.readwritedemo.components;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * web生命周期监听器
 * @author fhr
 * @date 2017/07/12
 */
public class InitListener implements ServletContextListener {
	private static final Logger logger = Logger.getLogger(InitListener.class);


	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		//获取spring上下文
		WebApplicationContext springContext = WebApplicationContextUtils
				.getWebApplicationContext(arg0.getServletContext());
		if (springContext == null) {
			logger.error("初始化错误 未找到spring上下文");
		} /*else {
			officeToPDF = (IOfficeToPDFComponent) springContext.getBean(BaseOFOfficeToPDFComponent.class);
			if (officeToPDF != null) {
				new Thread(() -> {
					try {
						officeToPDF.start();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}).start();
			} else {
				logger.error("初始化错误 未找到office服务组件");
			}
		}*/
	}

}
