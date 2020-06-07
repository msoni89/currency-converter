package com.practice.utility;

import com.practice.DataProviderUtility;
import com.practice.enums.Function;
import com.practice.enums.Interval;
import com.practice.enums.OutputSize;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class UrlBuilderUtilityTest {

    private String apiUrl = "https://localhost:8080/query?";
    private String apiKey = "XZMNXASAJKDIUEWIE";

    @Test
    void generateUrl() {
        String urlCurrentRate = UrlBuilderUtility.generate("USD", "INR", Function.CURRENCY_EXCHANGE_RATE, Interval.DAILY,
                OutputSize.COMPACT, apiUrl, apiKey);
        Assert.isTrue(DataProviderUtility.URL_CURRENCY_RATE_CONST.equalsIgnoreCase(urlCurrentRate), "Not matched");
        String urlDailyCurrencyRate = UrlBuilderUtility.generate("USD", "INR", Function.FX_INTRADAY, Interval._5Min, OutputSize.COMPACT,
                apiUrl, apiKey);
        Assert.isTrue(DataProviderUtility.URL_DAILY_CURRENCY_RATE_CONST.equalsIgnoreCase(urlDailyCurrencyRate), "Not matched");
        String urlMonthlyCurrency = UrlBuilderUtility.generate("USD", "INR", Function.FX_DAILY, Interval.DAILY, OutputSize.FULL,
                apiUrl, apiKey);
        Assert.isTrue(DataProviderUtility.URL_MONTHLY_CURRENCY_RATE_CONST.equalsIgnoreCase(urlMonthlyCurrency), "Not matched");
        String urlWeeklyCurrency = UrlBuilderUtility.generate("USD", "INR", Function.FX_INTRADAY, Interval._60Min, OutputSize.COMPACT,
                apiUrl, apiKey);
        Assert.isTrue(DataProviderUtility.URL_WEEKLY_CURRENCY_RATE_CONST.equalsIgnoreCase(urlWeeklyCurrency), "Not matched");
    }
}
