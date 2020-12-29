package com.webtap.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {
	
	public static List<String> getAtUser(String str){
		Pattern p = Pattern.compile("(?<=@).*?(?= )");
		Matcher m = p.matcher(str);
		List<String> result=new ArrayList<String>();
		while(m.find()){
			if(StringUtils.isNoneBlank(m.group().trim())){
				result.add(m.group().trim());
			}
		}
		return result;
	}

	/**
	 * 深度拷贝 List
	 * @param src
	 * @param <T>
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(byteOut);
		out.writeObject(src);

		ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
		ObjectInputStream in = new ObjectInputStream(byteIn);
		@SuppressWarnings("unchecked")
		List<T> dest = (List<T>) in.readObject();
		return dest;
	}

}
