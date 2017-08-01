package com.fhr.osmonitor.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 配置文件读取封装
 * 
 * @author fhr
 * @date 2017/05/23
 */
public class PropertiesUtils {
	/**
	 * 获取配置文件
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public static Properties getPropertiesObj(String fileName) throws IOException {
		try (InputStream in = new BufferedInputStream(new FileInputStream(fileName));
				BufferedReader bf = new BufferedReader(new InputStreamReader(in))) {
			Properties prop = new Properties();
			prop.load(bf);
			return prop;
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(fileName + "不存在");
		}
	}

	public static Properties getPropertiesObj(InputStream inputStream) throws IOException {
		try (BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream))) {
			Properties prop = new Properties();
			prop.load(bf);
			return prop;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据key读取对应的value
	 * 
	 * @param prop
	 * @param key
	 * @return
	 */
	public static String getProperty(Properties prop, String key) {
		return prop.getProperty(key);
	}

	/**
	 * 获取所有配置属性
	 * 
	 * @param prop
	 * @return
	 */
	public static Map<String, String> getAllProperty(Properties prop) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<?> en = prop.propertyNames();
		while (en.hasMoreElements()) {
			String key = (String) en.nextElement();
			String Property = prop.getProperty(key);
			map.put(key, Property);
		}
		return map;
	}
}
