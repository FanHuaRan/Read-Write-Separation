package com.fhr.osmonitor.components;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import org.apache.log4j.Logger;

import com.fhr.osmonitor.dtos.OSystemMonitInfo;
import com.fhr.osmonitor.models.OSystemInfo;
import com.fhr.osmonitor.utils.ObjcetSerializableUtils;

/**
 * 操作系统检测UDP服务器
 * 
 * @author fhr
 * @date 2017/07/26
 */
public class OsMonitUDPServer {
	private static final Logger logger = Logger.getLogger(OsMonitUDPServer.class);

	// 检测器
	private final OsMonitor osMonitor;

	// 用于收发数据报的UDP socket
	private final DatagramSocket datagramSocket;

	public OsMonitUDPServer(OsMonitor osMonitor) throws SocketException {
		this.osMonitor = osMonitor;
		// 实例化运行在MonitorConfig.MONIT_SOCKET_PORT端口之上的UDP SOCKET
		this.datagramSocket = new DatagramSocket(MonitorConfig.MONIT_SOCKET_PORT);
	}

	// 开启运行 首先运行监听器 然后死循环接受客户端socket数据
	public void start() {
		osMonitor.start();
		while (true) {
			try {
				byte[] receiveBytes = new byte[1024];
				DatagramPacket reveivePacket = new DatagramPacket(receiveBytes, receiveBytes.length);
				datagramSocket.receive(reveivePacket);
				byte[] infoBytes = getSystemMonitInfoBytes();
				DatagramPacket sendDataPacket = new DatagramPacket(infoBytes, infoBytes.length,
						reveivePacket.getAddress(), reveivePacket.getPort());
				datagramSocket.send(sendDataPacket);
			} catch (IOException e) {
				logger.error(e);
			}
		}
	}

	// 获取系统信息序列化的bytes数组
	private byte[] getSystemMonitInfoBytes() throws IOException {
		OSystemMonitInfo oSystemMonitInfo = getOSystemMonitInfo();
		return ObjcetSerializableUtils.toByteArray(oSystemMonitInfo);
	}

	// 获取系统信息
	private OSystemMonitInfo getOSystemMonitInfo() {
		OSystemInfo oSystemInfo = osMonitor.getOSystemInfo();
		return new OSystemMonitInfo(oSystemInfo.getMemoryRatio(), oSystemInfo.getCpuRatio(),
				oSystemInfo.getMonitTime());
	}
}
