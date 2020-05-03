package com.webtap.utils;

import com.webtap.comm.Const;

/**
 * @author robotbird
 * @version 1.0
 * @website http://webtap.cn
 * @date 2020-05-03 14:48
 **/
public class PasswordUtil {
    /**
     * AES 加密
     * @param password
     *         未加密的密码
     * @param salt
     *         盐值，默认使用用户名就可
     * @return
     * @throws Exception
     */
    public static String encrypt(String password, String salt) throws Exception {
        return AesUtil.encrypt(MD5Util.encrypt(salt + Const.PASSWORD_KEY), password);
    }

    /**
     * AES 解密
     * @param encryptPassword
     *         加密后的密码
     * @param salt
     *         盐值，默认使用用户名就可
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptPassword, String salt) throws Exception {
        return AesUtil.decrypt(MD5Util.encrypt(salt + Const.PASSWORD_KEY), encryptPassword);
    }
}
