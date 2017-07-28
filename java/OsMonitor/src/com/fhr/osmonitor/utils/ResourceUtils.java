package com.fhr.osmonitor.utils;

/**
 * 资源辅助类
 * @author ASUS
 * @date 2017/07/26
 */
public class ResourceUtils {
	/**
	 * 获取classpath下的资源文件
	 * @param relativeFileName
	 * @return
	 */
	public static String getAbsoluteFileName(String relativeFileName){
		String absoluteFile = Thread.currentThread().getContextClassLoader().getResource(relativeFileName)
				.getFile();//monitorconfig.properties
		return absoluteFile;
	}
}
