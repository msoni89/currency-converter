package com.practice.repository;

import com.practice.DataProviderUtility;
import com.practice.confriguration.TestRedisConfiguration;
import com.practice.enums.Frequency;
import com.practice.model.DashboardData;
import com.practice.model.RealTimeCurrencyExRate;
import com.practice.utility.KeyGeneratorUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest(classes = TestRedisConfiguration.class)
public class CurrencyRepositoryIntegrationTest {

    @Autowired
    private ICurrencyRepository currencyRepository;

    @Autowired
    private IDashboardRepository dashboardRepository;

    @Test
    public void shouldSaveCurrency_toRedis() {
        String key = KeyGeneratorUtility.generateKey("USD", "INR");
        UUID id = UUID.randomUUID();
        RealTimeCurrencyExRate saved = currencyRepository.save(DataProviderUtility.getRealtimeExRate(id, key));
        Assert.notNull(saved, "Message Not Saved");
        Optional<RealTimeCurrencyExRate> realTimeCurrencyExRateOptional = currencyRepository.findFirstByKeyOrderByFetchedTimeDesc(key);
        Assert.isTrue(realTimeCurrencyExRateOptional.isPresent(), "Empty Result");
        Assert.isTrue(id.equals(realTimeCurrencyExRateOptional.get().getId()), "Not Matched");
    }

    @Test
    public void shouldSaveDashboardData_toRedis() {
        UUID id = UUID.randomUUID();
        String key  = KeyGeneratorUtility.generateKey("USD", "INR");
        DashboardData saved = dashboardRepository.save(DataProviderUtility.getDashboardData(id, key));
        Assert.notNull(saved, "Message Not Saved Successfully");
        Optional<DashboardData> optionalDashboardData = dashboardRepository.findFirstByKeyAndFrequency(key, Frequency.MONTHLY);
        Assert.isTrue(optionalDashboardData.isPresent(), "Not Present");
        Assert.isTrue(id.equals(optionalDashboardData.get().getId()), "Not Matched");
        Assert.noNullElements(optionalDashboardData.get().getTimeSeries(), "Empty");
        Assert.isTrue(optionalDashboardData.get().getTimeSeries().get(0).getClose().doubleValue() == 10.0, "Not Matched");
    }
}
