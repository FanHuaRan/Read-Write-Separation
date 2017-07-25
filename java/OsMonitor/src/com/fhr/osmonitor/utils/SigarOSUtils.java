package com.fhr.osmonitor.utils;

import org.apache.log4j.Logger;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import com.fhr.osmonitor.models.OSystemInfo;


/**
 * 通过sigar库实现内存、cpu信息读取
 * @author fhr
 * @date 2017/06/11 
 */
public class SigarOSUtils{
	/**
	 * 日志组件
	 */
	private static final Logger logger = Logger.getLogger(SigarOSUtils.class);
	/**
	 * sigar组件  是个工厂对象，可以用于获取内存对象、cpu对象、硬盘对象等等
	 */
	private static final Sigar sigar = new Sigar();
	
	/**
	 * 内存、cpu信息读取
	 */
	public static OSystemInfo getOSystemInfo() {
		Mem mem = null;
		CpuPerc cpuPerc=null;
		try {
			mem = sigar.getMem();
			cpuPerc = sigar.getCpuPerc();
		} catch (SigarException e) {
			logger.error("sigar获取内存和cpu对象出错",e);
			return null;
		}
		// 总内存容量
		long totalMemory = mem.getTotal() / OSystemInfo.MEMORYUNIT;
		// 已使用内存
		long usedMemory = mem.getUsed() / OSystemInfo.MEMORYUNIT;
		// 剩余内存
		long freeMemory = mem.getFree() / OSystemInfo.MEMORYUNIT;
		// 内存使用率
		double memoryRatio = usedMemory /(double) totalMemory;
		// cpu使用率=1-cpu空闲率
		double cpuRatio = 1-cpuPerc.getIdle();
		return new OSystemInfo(totalMemory, usedMemory, freeMemory, memoryRatio, cpuRatio);
	}

}
