package com.dadao.core.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * 加密工具
 */
public class MyDigestUtils {

	public static void main(String[] args) {
		System.out.println(shaDigestForPasswrod("123456"));
	}

	public static String encode64(String str) {
		try {
			return Base64.encodeBase64URLSafeString(str.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
		}
		return Base64.encodeBase64URLSafeString(str.getBytes());
	}

	public static String decode64(String str) {
		try {
			return new String(Base64.decodeBase64(str), "utf-8");
		} catch (UnsupportedEncodingException e) {
		}
		return new String(Base64.decodeBase64(str));
	}

	/**
	 * 加密密码
	 * 
	 * @param str
	 * @param key
	 * @return
	 */
	public static String shaDigestForPasswrod(String str, String key) {
		return DigestUtils.shaHex(str + key);
	}

	public static String shaDigestForPasswrod(String str) {
		return DigestUtils.shaHex(str + "eason");
	}
}
