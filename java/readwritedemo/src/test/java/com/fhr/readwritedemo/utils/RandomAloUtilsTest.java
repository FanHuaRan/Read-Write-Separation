package com.fhr.readwritedemo.utils;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class RandomAloUtilsTest {

	public static Map<String, Integer> servers = new HashMap<String, Integer>();

	static {
		servers.put("192.168.20.101", 1);
		servers.put("192.168.20.102", 2);
		servers.put("192.168.20.103", 3);
		servers.put("192.168.20.104", 10);
	}

	@Test
	public void testWeightedRandom() {
		int[]counter=new int[4];
		for(int i=0;i<2000;i++){
			String result=RandomAloUtils.weightedRandom(servers);
			int index=Integer.valueOf(result.substring(13, 14))-1;
			counter[index]++;
		}
		for(int i=0;i<4;i++){
			System.out.println(String.format("%d :%d",(i+1),counter[i]));
		}
	}

}
