package com.fhr.osmonitor.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * 对象序列化和拷贝工具
 * @author fhr
 * @since 2017/07/26
 */
public class ObjcetSerializableUtils {
	/**
	 * 对象序列化为数组
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray(Object obj) throws IOException {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(bos)) {
			oos.writeObject(obj);
			oos.flush();
			byte[] bytes = bos.toByteArray();
			return bytes;
		}
	}

	/**
	 * 字节反序列化为对象
	 * @param bytes
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object toObject(byte[] bytes) throws ClassNotFoundException, IOException {
		try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
				ObjectInputStream ois = new ObjectInputStream(bis)) {
			Object obj = ois.readObject();
			return obj;
		}
	}
	/**
	 * 通过序列化克隆对象
	 * @param obj
	 * @return
	 */
	public static Object cloneObject(Object obj) {
		if (obj != null) {
			try {
				byte[] bytes = toByteArray(obj);
				Object cloneObj = toObject(bytes);
				return cloneObj;
			} catch (Exception e) {
			}
		}
		return null;
	}
	//对序列化克隆的一次泛型包装
	@SuppressWarnings("unchecked")
	public static <T> T cloneObject2(T obj){
		return (T)cloneObject(obj);
	}
}
