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
 * 在系统上运行一段时间之后会停止运行，尚未找到原因，实在不行可以考虑使用spring周期任务来完成！！！
 * @author fhr
 * @since 2017/07/26
 */
public class OsMonitUDPServer implements AutoCloseable {
	private static final Logger logger = Logger.getLogger(OsMonitUDPServer.class);
	
	//socket请求系统信息的标识 这儿只是为了方便后面提供更多的服务
	private static final String INFO_CODE="osinfo";
	
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
		while (!datagramSocket.isClosed()) {
			try {
				// 阻塞获取客户端连接
				DatagramPacket reveivePacket = getClientDatagramPacket();
				String dataStr = new String(reveivePacket.getData(), 0, reveivePacket.getLength(), "UTF-8");
				switch (dataStr) {
					case INFO_CODE:// 系统信息服务 返回客户端系统信息
						responseSystemInfo(reveivePacket);
						break;
					default:
						break;
				}
			} catch (SocketException e) {
				// 关闭程序会关闭socket,
				// 从而会导致DatagramSocket.receive抛出socket closed异常 所以不做处理
				if (!e.getMessage().toLowerCase().equals("socket closed")) {
					e.printStackTrace();
					logger.error(e);
				}
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(e);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
		}
	}

	// 停止系统监听器和UDP通讯服务 会导致循环体try中SocketException异常的产生
	@Override
	public void close() {
		osMonitor.close();
		try {
			datagramSocket.close();
		} catch (Exception e) {
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

	// 阻塞获取客户端连接
	private DatagramPacket getClientDatagramPacket() throws IOException {
		byte[] bufferBytes = new byte[1024];//1kb的获取数据的缓冲区
		DatagramPacket reveivePacket = new DatagramPacket(bufferBytes, bufferBytes.length);
		datagramSocket.receive(reveivePacket);
		return reveivePacket;
	}

	// 返回客户端系统信息
	private void responseSystemInfo(DatagramPacket reveivePacket) throws IOException {
		byte[] infoBytes = getSystemMonitInfoBytes();
		DatagramPacket sendDataPacket = new DatagramPacket(infoBytes, infoBytes.length, reveivePacket.getAddress(),
				reveivePacket.getPort());
		datagramSocket.send(sendDataPacket);
	}
}
