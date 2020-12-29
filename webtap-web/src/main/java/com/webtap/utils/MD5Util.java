package com.webtap.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;


public class MD5Util {

	public static String encrypt(String dataStr) {
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(dataStr.getBytes(StandardCharsets.UTF_8));
			byte s[] = m.digest();
			String result = "";
			for (int i = 0; i < s.length; i++) {
				result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00)
						.substring(6);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

}
