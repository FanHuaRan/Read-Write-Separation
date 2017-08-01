package com.fhr.readwritedemo.core.services.impl;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fhr.readwritedemo.core.models.DataBaseInfo;
import com.fhr.readwritedemo.core.models.dto.OSystemMonitInfo;
import com.fhr.readwritedemo.core.services.IDSCommunicate;
import com.fhr.readwritedemo.core.services.IDSMonitor;

/**
 * 基于udp的数据库服务器监控器
 * 
 * @author fhr
 * @since 2017/07/31
 */
@Service
public class UdpDSMonitor implements IDSMonitor {
	private static final Logger logger = Logger.getLogger(UdpDSMonitor.class);

	// 数据库服务器上运行的检测程序的端口硬编码为了16081
	private static final int PORT = 16081;
	// 检测周期
	private static final int PERIOD = 30;

	private List<DataBaseInfo> dataBaseInfos;

	// 定时任务线程池
	private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

	// 数据库服务器通讯组件
	@Autowired
	private IDSCommunicate dSCommunicate;

	@Override
	public void start() {
		executorService.scheduleAtFixedRate(() -> {
			for (final DataBaseInfo dataBaseInfo : dataBaseInfos) {
				OSystemMonitInfo oSystemMonitInfo = dSCommunicate.getInfo(dataBaseInfo.getHost(), PORT);
			}
			// 如何回调
		}, PERIOD, 0, TimeUnit.SECONDS);
	}

	@Override
	public void close() throws IOException {
		// 关闭定时任务
		executorService.shutdown();
	}

}
