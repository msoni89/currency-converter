package com.practice.utility;

import java.util.StringJoiner;

import com.practice.constant.Constant;
import com.practice.enums.Function;
import com.practice.enums.Interval;
import com.practice.enums.OutputSize;

/**
 * This is url build based on input it generate url for monthly, weekly, daily and current ex rate
 */
public class UrlBuilderUtility {

	/**
	 * This method generate create url based on input
	 * @param from
	 * @param to
	 * @param function
	 * @param interval
	 * @param outputSize
	 * @param apiUrl
	 * @param apiKey
	 * @return
	 */
	public static String generate(String from,
			String to,
			Function function,
			Interval interval,
			OutputSize outputSize,
			String apiUrl, String apiKey) {
		StringJoiner sj = new StringJoiner("&");
		append(function, from, to, sj, interval);
		sj.add(String.format(Constant.OUTPUT_SIZE, outputSize.getValue()));
		sj.add(String.format(Constant.API_KEY, apiKey));
		return new StringBuilder(apiUrl).append(sj.toString()).toString();
	}

	private static void append(Function function, String from, String to, StringJoiner sj, Interval interval) {
		switch (function) {
		case FX_INTRADAY:
			sj.add(String.format(Constant.FUNCTION, function));
			sj.add(String.format(Constant.FROM_AND_TO_SYMBOL, from, to));
			sj.add(String.format(Constant.INTERVAL, interval.getValue()));
			return;
		case CURRENCY_EXCHANGE_RATE:
			sj.add(String.format(Constant.FUNCTION, function));
			sj.add(String.format(Constant.FROM_AND_TO_CURRENCY, from, to));
			return;
		default:
			sj.add(String.format(Constant.FUNCTION, Function.FX_DAILY));
			sj.add(String.format(Constant.FROM_AND_TO_SYMBOL, from, to));
			return;
		}
	}
}
