package com.practice.integration;

import com.practice.confriguration.TestRedisConfiguration;
import com.practice.constant.Constant;
import com.practice.dto.ResponseDto;
import com.practice.enums.Frequency;
import com.practice.model.DashboardData;
import com.practice.model.RealTimeCurrencyExRate;
import com.practice.service.ICurrencyService;
import com.practice.service.IRefreshDataService;
import com.practice.service.ITriggerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Optional;

@SpringBootTest(classes = TestRedisConfiguration.class)
public class IntegrationTest {

    @Autowired
    private ITriggerService triggerService;

    @Autowired
    private IRefreshDataService refreshDataService;

    @Autowired
    private ICurrencyService currencyService;

    @Test
    void integrationTestFlowWithRedis() throws Exception {
        Optional<ResponseDto> responseDto = triggerService.trigger("GBP", "USD");
        Assert.isTrue(responseDto.isPresent(), "Empty");
        Assert.isTrue(Constant.SUCCESS.equalsIgnoreCase(responseDto.get().getMessage()), "Failed");
    }

    @Test
    void refresh() {
        refreshDataService.refresh("USD", "EUR");
        Optional<RealTimeCurrencyExRate> realtimeCurrencyExchangeRate =
                currencyService.findRealtimeCurrencyExRate("USD", "EUR");
        Assert.isTrue(realtimeCurrencyExchangeRate.isPresent(), "Empty");
        Assert.isTrue(realtimeCurrencyExchangeRate.get().getFromCurrencyCode().equalsIgnoreCase("USD"), "Not Matched");
        Optional<DashboardData> monthly = currencyService.findDashboardCurrencyExRate("USD", "EUR", Frequency.MONTHLY);
        Assert.isTrue(monthly.isPresent(), "Empty");
        Assert.isTrue(monthly.get().getMetaData().getFromSymbol().equalsIgnoreCase("USD"), "Not Matched");
        Optional<DashboardData> weekly = currencyService.findDashboardCurrencyExRate("USD", "EUR", Frequency.WEEKLY);
        Assert.isTrue(weekly.isPresent(), "Empty");
        Assert.isTrue(weekly.get().getMetaData().getFromSymbol().equalsIgnoreCase("USD"), "Not Matched");
        Optional<DashboardData> daily = currencyService.findDashboardCurrencyExRate("USD", "EUR", Frequency.DAILY);
        Assert.isTrue(daily.isPresent(), "Empty");
        Assert.isTrue(daily.get().getMetaData().getFromSymbol().equalsIgnoreCase("USD"), "Not Matched");
    }

}
