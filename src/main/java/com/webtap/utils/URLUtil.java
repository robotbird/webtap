package com.webtap.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class URLUtil {

	public static Logger logger =  LoggerFactory.getLogger(HtmlUtil.class);
    
	public static synchronized boolean isConnect(String urlStr) {  
        int counts = 0;  
        if (urlStr == null || urlStr.length() <= 0) {                         
            return false;                   
        }  
        while (counts < 3) {  
            try {  
            	URL url = new URL(urlStr);  
                HttpURLConnection   con = (HttpURLConnection) url.openConnection();  
                int state = con.getResponseCode();  
                if (state == 200) {  
                   return true;
                }  
                break;  
            }catch (Exception ex) {  
                counts++;   
                continue;  
            }  
        }  
        return false;  
    }  
	
	public static String getDomainUrl(String urlStr){
		String domainUrl=urlStr;
		try {
		     URL url = new URL(urlStr);
		     domainUrl=url.getProtocol()+"://"+url.getAuthority();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("getDomainUrl is erro,url :"+urlStr, e);
		}
		return domainUrl;
	}
	
	
	public static String getHost(String urlStr){
		String host=urlStr;
		try {
		     URL url = new URL(urlStr);
		     host=url.getHost();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("getHost is erro,url :"+urlStr, e);
		}
		return host;
	}

	public static String getURLContent(String urlStr) {

		//请求的url
		URL url = null;

		//建立的http链接
		HttpURLConnection httpConn = null;

		//请求的输入流
		BufferedReader in = null;

		//输入流的缓冲
		StringBuffer sb = new StringBuffer();

		try{
			url = new URL(urlStr);

			in = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8") );

			String str = null;

			//一行一行进行读入
			while((str = in.readLine()) != null) {
				sb.append( str );
			}
		} catch (Exception ex) {

		} finally{
			try{
				if(in!=null) {
					in.close(); //关闭流
				}
			}catch(IOException ex) {

			}
		}
		String result =sb.toString();
		return result;
	}
	
	
	public static void main(String[] args) {
		System.out.println(getDomainUrl("http://common.cnblogs.com/favicon.ico"));
	}

}
