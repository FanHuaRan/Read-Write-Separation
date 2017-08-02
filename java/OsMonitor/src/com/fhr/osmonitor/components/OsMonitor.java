package com.fhr.osmonitor.components;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.fhr.osmonitor.event.SeriousEvent;
import com.fhr.osmonitor.event.SeriousListener;
import com.fhr.osmonitor.event.SeriousListeners;
import com.fhr.osmonitor.event.WarinEvent;
import com.fhr.osmonitor.event.WarinListener;
import com.fhr.osmonitor.event.WarinListeners;
import com.fhr.osmonitor.models.OSystemInfo;
import com.fhr.osmonitor.utils.SigarOSUtils;

/**
 * 操作系统监视器
 * 带事件驱动 ....如果以后有功能扩展的话
 * 注意：感觉有时会莫名停止？？
 * @author fhr
 * @since 2017/07/26
 */
public class OsMonitor implements AutoCloseable {
	private static final Logger logger = Logger.getLogger(OsMonitor.class);

	// 当前时间段内的操作系统信息
	private volatile OSystemInfo oSystemInfo = null;

	// 严重监听器集合
	private SeriousListeners seriousListeners = null;

	// 警告监听器集合
	private WarinListeners warinListeners = null;

	// 定时周期任务线程池
	private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

	// 开始监视操作系统 定周期任务 间隔MONIT_INTERVAL秒执行
	public void start() {
		executorService.scheduleAtFixedRate(() -> {
			oSystemInfo = SigarOSUtils.getOSystemInfo();// 获取操作系统信息对象
			logger.info(oSystemInfo); // 记录日志
			eventDeal();// 事件处理
		}, 0, MonitorConfig.MONIT_INTERVAL, TimeUnit.SECONDS);
	}

	// 停止监视
	@Override
	public void close() {
		if (!executorService.isShutdown()) {
			try {
				executorService.shutdown();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 获取当前的操作系统信息
	public OSystemInfo getOSystemInfo() {
		return oSystemInfo;
	}

	// 判断是否处于警告状态
	private boolean inWarin(OSystemInfo oSystemInfo) {
		return oSystemInfo.getCpuRatio() > MonitorConfig.CPU_WARING_RATE
				|| oSystemInfo.getMemoryRatio() > MonitorConfig.MEMORY_WARING_RATE;
	}

	// 判断是否处于严重状态
	private boolean inSerious(OSystemInfo oSystemInfo) {
		return oSystemInfo.getCpuRatio() > MonitorConfig.CPU_SERIOUS_RATE
				|| oSystemInfo.getMemoryRatio() > MonitorConfig.MEMORY_SERIOUS_RATE;
	}

	// 处理事件
	private void eventDeal() {
		if (inSerious(oSystemInfo)) {//触发严重事件
			seriousListeners.fire(new SeriousEvent(oSystemInfo));
			return;
		}
		if (inWarin(oSystemInfo)) {//触发警告事件
			warinListeners.fire(new WarinEvent(oSystemInfo));
		}
	}

	public void addSeriousListener(SeriousListener seriousListener) {
		this.seriousListeners.add(seriousListener);
	}

	public void removeSeriousListener(SeriousListener seriousListener) {
		this.seriousListeners.remove(seriousListener);
	}

	public void addWarinListener(WarinListener warinListener) {
		this.warinListeners.add(warinListener);
	}

	public void removeWarinListener(WarinListener warinListener) {
		this.warinListeners.remove(warinListener);
	}

}
