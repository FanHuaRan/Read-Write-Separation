package com.fhr.readwritedemo.core.services.impl;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.fhr.readwritedemo.core.services.IDSCommunicate;
import com.fhr.readwritedemo.core.utils.ObjcetSerializableUtils;
import com.fhr.readwritedemo.dtomodels.OSystemMonitInfo;
/**
 * 数据库服务器通信实现
 * @author fhr
 * @date
 */
public class DSCommunicateImpl implements IDSCommunicate {

	@Override
	public OSystemMonitInfo getInfo(String host, int port) {
			try{
				 DatagramSocket client = new DatagramSocket();
				byte[] bytes=new String("osinfo").getBytes();
				DatagramPacket  datagramPacket=new DatagramPacket(bytes,bytes.length,InetAddress.getByName("localhost"), 16081);
			    client.send(datagramPacket);
			    DatagramPacket resultData=getClientDatagramPacket(client);
				String dataStr = new String(resultData.getData(), 0, resultData.getLength(), "UTF-8");
				System.out.println(dataStr);
				OSystemMonitInfo monitInfo=(OSystemMonitInfo) ObjcetSerializableUtils.toObject(bytes);
			}catch(Exception e){
			}
			return null;
		}
		
		// 阻塞获取客户端连接
		private DatagramPacket getClientDatagramPacket(DatagramSocket datagramSocket ) throws IOException {
			byte[] bufferBytes = new byte[2048];//2kb的获取数据的缓冲区
			DatagramPacket reveivePacket = new DatagramPacket(bufferBytes, bufferBytes.length);
			datagramSocket.receive(reveivePacket);
			return reveivePacket;
		}
}
