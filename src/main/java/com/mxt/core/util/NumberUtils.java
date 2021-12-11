package com.mxt.core.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberUtils {
	/**
	 * 保留两位小数
	 * 
	 * @param num
	 * @return
	 */
	public static double formatTwoDecimalsToStr(double num) {
		BigDecimal bg = new BigDecimal(num);
		double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}

	public static float formatTwoDecimals(float num) {
		BigDecimal bg = new BigDecimal(num);
		float f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
		return f1;
	}

	public static String formatTwoDecimalsToStr(float num) {
		DecimalFormat nf = new DecimalFormat();
		nf.setMaximumFractionDigits(2);
		return nf.format(num);
	}

	public static String formatDecimalsToStr(Number num, int digits) {
		DecimalFormat nf = new DecimalFormat();
		nf.setMaximumFractionDigits(digits);
		return nf.format(num);
	}

	public static void main(String[] args) {
		System.out.println(formatDecimalsToStr(2.16d, 1));
	}
}
