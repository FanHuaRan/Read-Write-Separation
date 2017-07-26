package com.fhr.osmonitor.main;

import java.io.IOException;
import java.net.SocketException;

import org.apache.log4j.Logger;

import com.fhr.osmonitor.components.OsMonitUDPServer;
import com.fhr.osmonitor.components.OsMonitor;

/**
 * 操作系统信息监听服务程序
 * 
 * @author fhr
 * @date 2017/07/26
 */
public class Program {
	private static final Logger logger = Logger.getLogger(Program.class);

	public static void main(String[] args) throws SocketException {
		try (OsMonitor osMonitor = new OsMonitor();
				OsMonitUDPServer osMonitUDPServer = new OsMonitUDPServer(osMonitor)) {
			Thread monitThread=new Thread(() -> {
				logger.info("UDP系统信息检测服务开始运行");
				osMonitUDPServer.start();
				logger.info("UDP系统信息检测服务结束运行");
			});
			monitThread.start();
			Thread.sleep(1500);
			promptScreenBlock(monitThread);
		} catch (SocketException e) {
			e.printStackTrace();
			logger.error("UDP系统信息检测服务运行出错");
			return;
		} catch (IOException e) {

		} catch (Exception e) {

		}
	}

	// 屏幕提示 并控制主线程阻塞 和退出
	private static void promptScreenBlock(Thread monitThread) throws IOException {
		System.out.println("请输入q退出程序");
		while ((char) System.in.read() != 'q') {}
		try{
			monitThread.interrupt();
		}catch(Exception e){}
	}
}
