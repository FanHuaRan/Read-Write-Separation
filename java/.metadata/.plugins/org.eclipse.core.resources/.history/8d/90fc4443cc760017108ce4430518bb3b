package com.fhr.osmonitor.components;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.fhr.osmonitor.utils.PropertiesUtils;
import com.fhr.osmonitor.utils.ResourceUtils;

/**
 * 监听配置对象
 * @author fhr
 * @date 2017/07/26
 */
public class MonitorConfig {
	private static final Logger logger = Logger.getLogger(MonitorConfig.class);

	// 配置文件路径
	private static final String CONFIG_NAME = "monitorconfig.properties";

	// 监听间隔 以秒为单位 缺省为1s
	public static final int MONIT_INTERVAL;

	// 端口 缺省为14080
	public static final int MONIT_SOCKET_PORT;

	// 内存占用率警告限度 缺省90%
	public static final double MEMORY_WARING_RATE;

	// 内存占用率严重限度 缺省95%
	public static final double MEMORY_SERIOUS_RATE;

	// cpu占用率警告限度 缺省90%
	public static final double CPU_WARING_RATE;

	// cpu占用率严重限度 缺省95%
	public static final double CPU_SERIOUS_RATE;

	// 从配置文件和缺省值中初始化常量值
	static {
		int interval = 1;
		int port = 14080;
		double memWarn = 0.9d;
		double memSer = 0.95d;
		double cpuWarn = 0.9d;
		double cpuSer = 0.95d;
		try {
			Properties properties = PropertiesUtils.getPropertiesObj(ResourceUtils.getAbsoluteFileName(CONFIG_NAME));
			String intervalStr = PropertiesUtils.getProperty(properties, "monit.interval");
			if (StringUtils.isNotEmpty(intervalStr) && StringUtils.isNumeric(intervalStr)) {
				interval = Integer.valueOf(intervalStr);
			}
			String sokertPortStr = StringUtils.trim(PropertiesUtils.getProperty(properties, "monit.socketport"));
			if (StringUtils.isNumeric(sokertPortStr)) {
				port = Integer.valueOf(sokertPortStr);
			}
			String memWarnStr = StringUtils.trim(PropertiesUtils.getProperty(properties, "monit.memwarn"));
			if (StringUtils.isNumeric(memWarnStr)) {
				memWarn = Double.valueOf(memWarnStr);
			}
			String memSerStr = StringUtils.trim(PropertiesUtils.getProperty(properties, "monit.memser"));
			if (StringUtils.isNumeric(memSerStr)) {
				memSer = Double.valueOf(memSerStr);
			}
			String cpuWarnStr = StringUtils.trim(PropertiesUtils.getProperty(properties, "monit.cpuwarn"));
			if (StringUtils.isNumeric(cpuWarnStr)) {
				cpuWarn = Double.valueOf(cpuWarnStr);
			}
			String cpuSerStr = StringUtils.trim(PropertiesUtils.getProperty(properties, "monit.socketport"));
			if (StringUtils.isNumeric(cpuSerStr)) {
				cpuSer = Double.valueOf(cpuSerStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("配置文件读取错误", e);
		}
		MONIT_INTERVAL = interval;
		MONIT_SOCKET_PORT = port;
		MEMORY_WARING_RATE = memWarn;
		MEMORY_SERIOUS_RATE = memSer;
		CPU_WARING_RATE = cpuWarn;
		CPU_SERIOUS_RATE = cpuSer;
	}
}
