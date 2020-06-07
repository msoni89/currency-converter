package com.practice.utility;

/**
 * This utility consist generateKey
 * 
 * @author Mehul Soni
 *
 */
public class KeyGeneratorUtility {

	/**
	 * This method is used for generating key based on passed from, to currency and
	 * local date time object
	 * 
	 * @param toCurrency
	 * @param fromCurrency
	 * @param dateTime
	 * @return
	 */
	public static String generateKey(String toCurrency, String fromCurrency) {
		StringBuilder builder = new StringBuilder();
		builder.append(fromCurrency);
		builder.append("to");
		builder.append(toCurrency);
		return builder.toString().toUpperCase();
	}

}
