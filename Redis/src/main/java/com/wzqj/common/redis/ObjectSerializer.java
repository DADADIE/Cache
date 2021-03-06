package com.wzqj.common.redis;

import java.io.*;

public class ObjectSerializer {

	public static byte[] serialize(Object obj) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			return bos.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (oos != null)
					oos.close();
			} catch (IOException e) {
			}
		}
	}

	public static Object deSerialize(byte[] buf) {
		ByteArrayInputStream bos = new ByteArrayInputStream(buf);
		try {
			ObjectInputStream ios = new ObjectInputStream(bos);
			return ios.readObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				bos.close();
			} catch (IOException e) {
			}
		}
	}
}
