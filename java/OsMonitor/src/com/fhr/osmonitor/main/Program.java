package com.fhr.osmonitor.main;

import java.net.SocketException;

import org.apache.log4j.Logger;

import com.fhr.osmonitor.components.OsMonitUDPServer;
import com.fhr.osmonitor.components.OsMonitor;
/**
 * 操作系统信息监听服务程序
 * @author fhr
 * @date 2017/07/26
 */
public class Program {
	private static final Logger logger=Logger.getLogger(Program.class);
	
	public static void main(String []args) throws SocketException{
		OsMonitor osMonitor=new OsMonitor();
		OsMonitUDPServer osMonitUDPServer=null;
	    try { 
			 osMonitUDPServer=new OsMonitUDPServer(osMonitor);
		} catch (SocketException e) {
			e.printStackTrace();
			logger.error("初始化UDP系统信息检测服务器出错");
			return;
		}
	    logger.info("UDP系统信息检测服务开始运行");
	    osMonitUDPServer.start();
	    logger.info("UDP系统信息检测服务结束运行");
	}
	
}
