package com.fhr.osmonitor.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * 对象序列化工具
 * @author fhr
 * @date 2017/07/26
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
}
