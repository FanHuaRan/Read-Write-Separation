package com.fhr.osmonitor.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.junit.Test;

import com.fhr.osmonitor.dtos.OSystemMonitInfo;
import com.fhr.osmonitor.utils.ObjcetSerializableUtils;

public class TestUdpServer {

	@Test
	public void test() throws IOException, ClassNotFoundException {
	    DatagramSocket client = new DatagramSocket();
		byte[] bytes=new String("osinfo").getBytes();
		DatagramPacket  datagramPacket=new DatagramPacket(bytes,bytes.length,InetAddress.getByName("localhost"), 16081);
	    client.send(datagramPacket);
	    DatagramPacket resultData=getClientDatagramPacket(client);
		String dataStr = new String(resultData.getData(), 0, resultData.getLength(), "UTF-8");
		System.out.println(dataStr);
		OSystemMonitInfo monitInfo=(OSystemMonitInfo) ObjcetSerializableUtils.toObject(bytes);
		System.out.println(monitInfo.getCpuRatio());
	}
	
	// 阻塞获取客户端连接
	private DatagramPacket getClientDatagramPacket(DatagramSocket datagramSocket ) throws IOException {
		byte[] bufferBytes = new byte[2048];//2kb的获取数据的缓冲区
		DatagramPacket reveivePacket = new DatagramPacket(bufferBytes, bufferBytes.length);
		datagramSocket.receive(reveivePacket);
		return reveivePacket;
	}
}
