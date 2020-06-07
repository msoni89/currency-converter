package com.practice;

import com.practice.dto.CurrencyDto;
import com.practice.enums.Frequency;
import com.practice.model.DashboardData;
import com.practice.model.RealTimeCurrencyExRate;
import com.practice.model.TimeSeries;
import com.practice.utility.KeyGeneratorUtility;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataProviderUtility {

    public static final String URL_CURRENCY_RATE_CONST = "https://localhost:8080/query?function=CURRENCY_EXCHANGE_RATE&from_currency=USD&to_currency=INR&outputsize=compact&apikey=XZMNXASAJKDIUEWIE";
    public static String URL_DAILY_CURRENCY_RATE_CONST = "https://localhost:8080/query?function=FX_INTRADAY&from_symbol=USD&to_symbol=INR&interval=5min&outputsize=compact&apikey=XZMNXASAJKDIUEWIE";
    public static String URL_MONTHLY_CURRENCY_RATE_CONST = "https://localhost:8080/query?function=FX_DAILY&from_symbol=USD&to_symbol=INR&outputsize=full&apikey=XZMNXASAJKDIUEWIE";
    public static String URL_WEEKLY_CURRENCY_RATE_CONST = "https://localhost:8080/query?function=FX_INTRADAY&from_symbol=USD&to_symbol=INR&interval=60min&outputsize=compact&apikey=XZMNXASAJKDIUEWIE";

    public static DashboardData getDashboardData(UUID id, String key) {
        DashboardData dashboardData = new DashboardData();
        dashboardData.setId(id);
        dashboardData.setKey(key);
        dashboardData.setFrequency(Frequency.MONTHLY);
        TimeSeries timeSeries = new TimeSeries();
        timeSeries.setClose(new BigDecimal(10.0));
        timeSeries.setOpen(new BigDecimal(7.0));
        timeSeries.setLow(new BigDecimal(8.0));
        timeSeries.setHigh(new BigDecimal(16.0));
        List<TimeSeries> series = new ArrayList<>();
        series.add(timeSeries);
        dashboardData.setTimeSeries(series);
        return dashboardData;
    }

    public static RealTimeCurrencyExRate getRealtimeExRate(UUID id, String key) {
        RealTimeCurrencyExRate realTimeCurrencyExRate = new RealTimeCurrencyExRate();
        realTimeCurrencyExRate.setId(id);
        realTimeCurrencyExRate.setKey(key);
        return realTimeCurrencyExRate;
    }

    public static List<CurrencyDto> getCurrencies() {
        CurrencyDto currencyDto = new CurrencyDto(
                "United State Dollar",
                "USD",
                "$");
        List<CurrencyDto> currencyDtos = new ArrayList<CurrencyDto>();
        currencyDtos.add(currencyDto);
        return currencyDtos;
    }
}
