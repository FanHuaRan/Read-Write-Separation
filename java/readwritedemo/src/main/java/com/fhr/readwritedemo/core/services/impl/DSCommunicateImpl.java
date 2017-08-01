package com.fhr.readwritedemo.core.services.impl;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fhr.readwritedemo.core.models.dto.OSystemMonitInfo;
import com.fhr.readwritedemo.core.services.IDSCommunicate;
import com.fhr.readwritedemo.utils.ObjcetSerializableUtils;
/**
 * 数据库服务器通信实现
 * @author fhr
 * @since 2017/07/31
 */
@Service
public class DSCommunicateImpl implements IDSCommunicate {
	private static final Logger logger=Logger.getLogger(DSCommunicateImpl.class);
	
	// 获取数据库服务器的检测信息
	@Override
	public OSystemMonitInfo getInfo(String host, int port) {
			try{
				 DatagramSocket client = new DatagramSocket();
				byte[] bytes=new String("osinfo").getBytes();
				DatagramPacket  datagramPacket=new DatagramPacket(bytes,bytes.length,InetAddress.getByName("localhost"), 16081);
			    client.send(datagramPacket);
			    DatagramPacket resultData=getClientDatagramPacket(client);
				return (OSystemMonitInfo) ObjcetSerializableUtils.toObject(resultData.getData());
			}catch(Exception e){
				e.printStackTrace();
				logger.error("获取数据库服务器检测信息出错",e);
				return null;
			}
		}
		
		// 阻塞获取返回的数据包
		private DatagramPacket getClientDatagramPacket(DatagramSocket datagramSocket ) throws IOException {
			byte[] bufferBytes = new byte[2048];//2kb的获取数据的缓冲区
			DatagramPacket reveivePacket = new DatagramPacket(bufferBytes, bufferBytes.length);
			datagramSocket.receive(reveivePacket);
			return reveivePacket;
		}
}