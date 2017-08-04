package com.fhr.readwritedemo.core.services.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fhr.osmonitor.dtos.OSystemMonitInfo;
import com.fhr.readwritedemo.core.global.DataSourceSys;
import com.fhr.readwritedemo.core.models.DataBaseInfo;
import com.fhr.readwritedemo.core.services.IDSCommunicate;
import com.fhr.readwritedemo.core.services.IDSMonitor;

/**
 * 基于udp的数据库服务器监控器
 * 目前是使用ScheduledExecutorService来实现的
 * 后面而言考虑使用spring定时定周期任务来执行
 * @author fhr
 * @since 2017/07/31
 */
@Service
public class UdpDSMonitor implements IDSMonitor {
	private static final Logger logger = Logger.getLogger(UdpDSMonitor.class);

	// 数据库服务器上运行的检测程序的端口硬编码为了16081
	private static final int PORT = 16081;
	// 检测周期 s为单位
	private static final int PERIOD = 30;
	// 获取某个数据库服务器信息的超时时间  ms为单位
	private static final int TIMEOUT=300;
	// 内存占用率严重限度  超过此限度的服务器的权值将被设置为0 即暂不使用
	public static final double MEMORY_SERIOUS_RATE=0.95;
	// cpu占用率严重限度 超过此限度的服务器的权值将被设置为0 即暂不使用
	public static final double CPU_SERIOUS_RATE=1.0;	
	// 定时任务线程池
	private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	// 数据库服务器通讯组件
	@Autowired
	private IDSCommunicate dSCommunicate;
	// 数据库信息映射 key为host value为数据库信息对象
	private Map<String,DataBaseInfo> dataBaseInfos;
	
	@Override
	public void start() {
		// 监控信息得出的服务器权值
		Map<String,Integer> valueWeights=new HashMap<>();
		//定时间隔PERIOD秒执行任务
		executorService.scheduleAtFixedRate(() -> {
			for(Entry<String,DataBaseInfo> entry:dataBaseInfos.entrySet()){
				//获取服务器检测信息
				OSystemMonitInfo oSystemMonitInfo = dSCommunicate.getInfo(entry.getValue().getHost(), PORT,TIMEOUT);
				//获取服务器权重
				int weight=computeServerWeight(oSystemMonitInfo);
				valueWeights.put(entry.getKey(),weight);
			}
			//更新到主环境当中
			DataSourceSys.setServerWeights(valueWeights);
		},0, PERIOD, TimeUnit.SECONDS);
	}

	@Override
	public void close() throws IOException {
		// 关闭定时任务
		executorService.shutdown();
	}
	//计算服务器权重 这儿可以使用策略模式独立一个接口出来
	public Integer computeServerWeight(OSystemMonitInfo oSystemMonitInfo) {
		//监控信息为空或者处于严重状态权值就是1
		if(oSystemMonitInfo==null||inSerious(oSystemMonitInfo)){
			return 1;
		}
		//策略是求得剩余内存率和cpu的使用率 
		//累加然后乘上20取整就得到权重
		double freeMem=1-oSystemMonitInfo.getMemoryRatio();
		double freeCpu=1-oSystemMonitInfo.getCpuRatio();
		return (int)((freeMem+freeCpu)*20);
	}
	// 判断是否处于严重状态 这儿也应该独立一个接口出来
	private boolean inSerious(OSystemMonitInfo oSystemMonitInfo) {
			return oSystemMonitInfo.getCpuRatio() > CPU_SERIOUS_RATE
					|| oSystemMonitInfo.getMemoryRatio() > MEMORY_SERIOUS_RATE;
	}

	public void setDataBaseInfos(Map<String,DataBaseInfo> dataBaseInfos) {
		this.dataBaseInfos = dataBaseInfos;
	}
}
