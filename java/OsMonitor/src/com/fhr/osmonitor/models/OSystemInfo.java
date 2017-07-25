package com.fhr.osmonitor.models;

import java.io.Serializable;

/**
 *  系统信息
 * @author fhr
 * @date 2017/06/11
 */
public class OSystemInfo implements Serializable {
	private static final long serialVersionUID = 5494824275273574194L;
	// 内存单位
	public static final long MEMORYUNIT = 1024L;

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

	public OSystemInfo(long totalMemory, long usedMemory, long freeMemory, double memoryRatio, double cpuRatio) {
		super();
		this.totalMemory = totalMemory;
		this.usedMemory = usedMemory;
		this.freeMemory = freeMemory;
		this.memoryRatio = memoryRatio;
		this.cpuRatio = cpuRatio;
	}

	public static long getMemoryunit() {
		return MEMORYUNIT;
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
		return String.format("内存占用率：" + memoryRatio + "  cpu占用率：" + cpuRatio);
	}
}
