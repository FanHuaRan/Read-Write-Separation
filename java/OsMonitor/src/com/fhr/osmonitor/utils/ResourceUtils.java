package com.fhr.osmonitor.utils;

import java.io.InputStream;

/**
 * 资源辅助类
 * @author ASUS
 * @date 2017/07/26
 */
public class ResourceUtils {
	/**
	 * 获取classpath下的资源文件 打包jar后获取有问题
	 * @param relativeFileName
	 * @return
	 */
//	public static String getAbsoluteFileName(String relativeFileName){
//		String absoluteFile = Thread.currentThread().getContextClassLoader().getResource(relativeFileName)
//				.getFile();//monitorconfig.properties
//		return absoluteFile;
//	}
	/**
	 * 获取资源文件流  打包jar后一切正常
	 * @param relativeFileName
	 * @return
	 */
	public static InputStream getAbsoluteFile(String relativeFileName){
		return ResourceUtils.class.getClassLoader().getResourceAsStream(relativeFileName);
	}
}
