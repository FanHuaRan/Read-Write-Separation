package com.fhr.osmonitor.models;

import java.io.Serializable;

import org.joda.time.DateTime;

/**
 * 系统信息
 * @author fhr
 * @date 2017/06/11
 */
public class OSystemInfo implements Serializable {
	private static final long serialVersionUID = 5494824275273574194L;
	
	// 内存单位
	public static final long MEMORY_UNIT = 1024L;

	// 总内存容量
	private final long totalMemory;

	// 已使用内存
	private final long usedMemory;

	// 剩余内存
	private final long freeMemory;

	// 内存使用率
	private final double memoryRatio;

	// cpu使用率
	private final double cpuRatio;
	
	// 检测时间
	private final DateTime monitTime;

	public OSystemInfo(long totalMemory, long usedMemory, long freeMemory, double memoryRatio, double cpuRatio,DateTime monitTime) {
		super();
		this.totalMemory = totalMemory;
		this.usedMemory = usedMemory;
		this.freeMemory = freeMemory;
		this.memoryRatio = memoryRatio;
		this.cpuRatio = cpuRatio;
		this.monitTime=monitTime;
	}

	public long getTotalMemory() {
		return totalMemory;
	}

	public long getUsedMemory() {
		return usedMemory;
	}

	public long getFreeMemory() {
		return freeMemory;
	}

	public double getMemoryRatio() {
		return memoryRatio;
	}

	public double getCpuRatio() {
		return cpuRatio;
	}

	@Override
	public String toString() {
		return String.format("{totalMemory：%d,usedmemory:%d,freememory:%d,memoryratio:%d,cpuratio:%d,monittime:%s}", totalMemory,
				usedMemory, freeMemory, memoryRatio, cpuRatio,monitTime);
	}
}
