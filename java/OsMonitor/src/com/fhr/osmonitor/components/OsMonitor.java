package com.fhr.osmonitor.components;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.fhr.osmonitor.models.OSystemInfo;
import com.fhr.osmonitor.utils.SigarOSUtils;
/**
 * 操作系统监视器 
 * @author fhr
 * @date 2017/07/25
 */
public class OsMonitor {
	// 监视器监视周期 s为单位
	private static final long PERIOD = 1;
	// 日志组件
	private final Logger logger = Logger.getLogger(OsMonitor.class);
	// 定时周期任务线程池
	private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
	// 开始监视操作系统
	public void start() {
		//定周期任务  间隔PERIOD秒执行
		executorService.scheduleAtFixedRate(() -> {
			//获取操作系统信息对象
			OSystemInfo oSystemInfo = SigarOSUtils.getOSystemInfo();
			//记录日志
			logger.info(oSystemInfo.toString());
		}, 0, PERIOD, TimeUnit.SECONDS);
		//记录日志
		
		//记录内存 
	}
	/**
	 * 停止监视器
	 */
	public void stop() {
		if (!executorService.isShutdown()) {
			executorService.shutdown();
		}
	}
	/**
	 * 警告处理方法
	 * @param oSystemInfo
	 */
	public void WarinHander(OSystemInfo oSystemInfo) {
		// nothing
	}
	/**
	 * 严重处理方法
	 * @param oSystemInfo
	 */
	public void SeriousHandler(OSystemInfo oSystemInfo) {
		// nothing
	}
}
