package com.fhr.osmonitor.models;

import java.io.Serializable;

import org.joda.time.DateTime;

/**
 * 系统检测信息
 * @author fhr
 * @date 2017/07/25
 */
public class OSystemMonitorInfo implements Serializable{
	
	private static final long serialVersionUID = 2114525443130295160L;
	
		// 内存使用率
		private final double memoryRatio;

		// cpu使用率
		private final double cpuRatio;
		
		// 检测时间
		private final DateTime monitTime;

		public OSystemMonitorInfo(double memoryRatio, double cpuRatio,DateTime monitTime) {
			super();
			this.memoryRatio = memoryRatio;
			this.cpuRatio = cpuRatio;
			this.monitTime=monitTime;
		}

		public double getMemoryRatio() {
			return memoryRatio;
		}

		public double getCpuRatio() {
			return cpuRatio;
		}

		public DateTime getMonitTime() {
			return monitTime;
		}
		
	
}
