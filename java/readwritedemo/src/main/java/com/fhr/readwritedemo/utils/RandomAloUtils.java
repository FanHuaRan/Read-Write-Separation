package com.fhr.readwritedemo.utils;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

/**
 * 随机算法辅助工具
 * @author fhr
 * @since 2017/08/01
 */
public class RandomAloUtils {
	/**
	 * 加权随机 source的key为值 value为权
	 * @param source
	 * @return
	 */
	public static String weightedRandom(Map<String,Integer> source) {
		if(source == null || source.isEmpty()) {
			return null;  
		}
		//先求和
		Integer sum=source.entrySet().stream().mapToInt(p->p.getValue()).sum();
        //取得初始随机数
		Integer rand = new Random().nextInt(sum) + 1;  
		// 依次将初始随机数递减source中的值 直到随机数小于0
		Set<Entry<String,Integer>> entrySet=source.entrySet();
        for(Map.Entry<String, Integer> entry :entrySet){  
            rand -= entry.getValue();  
            if(rand <=0){  
                return entry.getKey();  
            }  
        }  
        return null; 
	}
}
