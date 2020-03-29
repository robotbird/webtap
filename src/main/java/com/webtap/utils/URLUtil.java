package com.webtap.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

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

    //十六进制下数字到字符的映射数组
    private final static String[] hexDigits = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};

    /**把inputString加密*/
    public static String md5(String inputStr){
        return encodeByMD5(inputStr);
    }

    /**
     * 验证输入的密码是否正确
     * @param password 真正的密码（加密后的真密码）
     * @param inputString 输入的字符串
     * @return 验证结果，boolean类型
     */
    public static boolean authenticatePassword(String password,String inputString){
        if(password.equals(encodeByMD5(inputString))){
            return true;
        }else{
            return false;
        }
    }

    /**对字符串进行MD5编码*/
    private static String encodeByMD5(String originString){
        if (originString!=null) {
            try {
                //创建具有指定算法名称的信息摘要
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                //使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
                byte[] results = md5.digest(originString.getBytes());
                //将得到的字节数组变成字符串返回
                String result = byteArrayToHexString(results);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 轮换字节数组为十六进制字符串
     * @param b 字节数组
     * @return 十六进制字符串
     *
     */
    private static String byteArrayToHexString(byte[] b){
        StringBuffer resultSb = new StringBuffer();
        for(int i=0;i<b.length;i++){
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    //将一个字节转化成十六进制形式的字符串
    private static String byteToHexString(byte b){
        int n = b;
        if(n<0)
            n=256+n;
        int d1 = n/16;
        int d2 = n%16;
        return hexDigits[d1] + hexDigits[d2];
    }


    public static String[] getShortUrl(String url) {
        // 可以自定义生成 MD5 加密字符传前的混合 KEY
        String key = "webtap";
        // 要使用生成 URL 的字符
        String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
                "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"
        };
        // 对传入网址进行 MD5 加密
        String sMD5EncryptResult = (md5(key + url));
        String hex = sMD5EncryptResult;
        String[] resUrl = new String[4];
        //得到 4组短链接字符串
        for (int i = 0; i < 4; i++) {
            // 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
            String sTempSubString = hex.substring(i * 8, i * 8 + 8);
            // 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用 long ，则会越界
            long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
            String outChars = "";
            //循环获得每组6位的字符串
            for (int j = 0; j < 6; j++) {
                // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引(具体需要看chars数组的长度   以防下标溢出，注意起点为0)
                long index = 0x0000003D & lHexLong;
                // 把取得的字符相加
                outChars += chars[(int) index];
                // 每次循环按位右移 5 位
                lHexLong = lHexLong >> 5;
            }
            // 把字符串存入对应索引的输出数组
            resUrl[i] = outChars;
        }
        return resUrl;
    }




    public static void main(String[] args) {
        String sLongUrl = "1";
        for (String shortUrl : getShortUrl(sLongUrl)) {
            System.out.println(shortUrl);
        }
    }
}
