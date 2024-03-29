package com.mxt.core.util;

import java.util.Random;

public class RandomUtils {
	public static final String RANDOMCODEKEY = "RANDOMVALIDATECODEKEY";// 放到session中的key

	private static String randString = "abcdefghigklmnopkrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";// 随机产生的字符串

	private static String letterString = "abcdefghigklmnopkrstuvwxyz";

	private static String digitString = "0123456789";
	private static Random random = new Random();

	public static int nextInt(int num) {
		return random.nextInt(num);
	}

	public static String getRandomLetter(int length) {
		StringBuilder sf = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(25);//
			sf.append(letterString.charAt(number));
		}
		return sf.toString();
	}

	/**
	 * 随机数字字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomDigit(int length) {
		StringBuilder sf = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(9);//
			sf.append(digitString.charAt(number));
		}
		return sf.toString();
	}

	/*
	 * 获取随机的字符
	 */
	public static String getRandomString(int length) {
		StringBuilder sf = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(62);// 0~61
			sf.append(randString.charAt(number));
		}
		return sf.toString();
	}

	public static void main(String[] args) {
		System.out.println(getRandomString(32));
	}
}
