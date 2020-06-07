package com.practice.service.impl;

import com.google.gson.Gson;
import com.practice.confriguration.ApplicationProperties;
import com.practice.constant.Constant;
import com.practice.dto.RealtimeCurrencyExchangeRate;
import com.practice.dto.RealtimeData;
import com.practice.enums.Frequency;
import com.practice.enums.Function;
import com.practice.enums.Interval;
import com.practice.enums.OutputSize;
import com.practice.model.DashboardData;
import com.practice.model.MetaData;
import com.practice.model.RealTimeCurrencyExRate;
import com.practice.model.TimeSeries;
import com.practice.repository.ICurrencyRepository;
import com.practice.repository.IDashboardRepository;
import com.practice.service.IRefreshDataService;
import com.practice.utility.DateFormatterUtility;
import com.practice.utility.KeyGeneratorUtility;
import com.practice.utility.UrlBuilderUtility;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class RefreshDataServiceImpl implements IRefreshDataService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RefreshDataServiceImpl.class);

    @Autowired
    private ApplicationProperties properties;

    @Autowired
    private ICurrencyRepository currencyRepository;

    @Autowired
    private IDashboardRepository dashboardRepository;

    @Autowired
    private Gson gson;

    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    @Override
    public void refresh(String from, String to) {
        LOGGER.info("Entered, Refresh request received from {} to {}", from, to);
        dashboardRepository.deleteByKey(KeyGeneratorUtility.generateKey(to, from));
        Runnable task1 = getCurrentRate(from, to);
        Runnable task2 = getDailyCurrencyRate(from, to);
        Runnable task3 = getMonthlyCurrencyData(from, to);
        Runnable task4 = getWeeklyCurrencyData(from, to);
        try {
            executorService.submit(task1).get();
            executorService.submit(task2).get();
            executorService.submit(task3).get();
            executorService.submit(task4).get();
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private Runnable getCurrentRate(String from, String to) {
        LOGGER.info("Entered, Currency refresh request received from {} to {}", from, to);
        return () -> {
            String url = UrlBuilderUtility.generate(from, to, Function.CURRENCY_EXCHANGE_RATE, Interval.DAILY,
                    OutputSize.COMPACT, properties.getApiUrl(), properties.getApiKey());
            Optional<String> response = sendGet(url);
            if (!response.isPresent()) {
                return;
            }

            Optional<RealtimeCurrencyExchangeRate> optional = parseRealtimeCurrentRate(response.get());
            if (!optional.isPresent())
                return;

            Optional<RealTimeCurrencyExRate> exRate = fillCurrencyRate(optional.get());
            if (!exRate.isPresent())
                return;
            currencyRepository.save(exRate.get());
            LOGGER.info("loaded: Current Rates");
        };
    }

    private Runnable getDailyCurrencyRate(String from, String to) {
        LOGGER.info("Entered, Daily refresh request received from {} to {}", from, to);
        return () -> {
            String url = UrlBuilderUtility.generate(from, to, Function.FX_INTRADAY, Interval._5Min, OutputSize.COMPACT,
                    properties.getApiUrl(), properties.getApiKey());
            Optional<String> response = sendGet(url);
            if (!response.isPresent()) {
                return;
            }
            Optional<DashboardData> data = parseHistoryData(response.get(), Interval._5Min,
                    KeyGeneratorUtility.generateKey(to, from), Frequency.DAILY);

            if (!data.isPresent())
                return;

            dashboardRepository.save(data.get());
            LOGGER.info("loaded: Daily Rates");
        };
    }

    private Runnable getMonthlyCurrencyData(String from, String to) {
        LOGGER.info("Entered, Monthly refresh request received from {} to {}", from, to);
        return () -> {
            String url = UrlBuilderUtility.generate(from, to, Function.FX_DAILY, Interval.DAILY, OutputSize.FULL,
                    properties.getApiUrl(), properties.getApiKey());
            Optional<String> response = sendGet(url);
            if (!response.isPresent()) {
                return;
            }
            Optional<DashboardData> data = parseHistoryData(response.get(), Interval.DAILY,
                    KeyGeneratorUtility.generateKey(to, from), Frequency.MONTHLY);
            if (!data.isPresent())
                return;

            dashboardRepository.save(data.get());
            LOGGER.info("loaded: Monthly Rates");
        };
    }

    private Runnable getWeeklyCurrencyData(String from, String to) {
        LOGGER.info("Entered, Weekly refresh request received from {} to {}", from, to);
        return () -> {
            String url = UrlBuilderUtility.generate(from, to, Function.FX_INTRADAY, Interval._60Min, OutputSize.COMPACT,
                    properties.getApiUrl(), properties.getApiKey());
            Optional<String> response = sendGet(url);
            if (!response.isPresent()) {
                return;
            }
            Optional<DashboardData> data = parseHistoryData(response.get(), Interval._60Min,
                    KeyGeneratorUtility.generateKey(to, from), Frequency.WEEKLY);
            if (!data.isPresent())
                return;

            dashboardRepository.save(data.get());
            LOGGER.info("loaded: Weekly Rates");
        };
    }

    private Optional<RealTimeCurrencyExRate> fillCurrencyRate(RealtimeCurrencyExchangeRate currencyExchangeRate) {
        LOGGER.info("Entered");
        RealtimeData data = currencyExchangeRate.getRealtimeData();

        if (data == null)
            return Optional.empty();

        RealTimeCurrencyExRate currency = new RealTimeCurrencyExRate();
        currency.setAskPrice(new BigDecimal(data.getAskPrice()));
        currency.setBidPrice(new BigDecimal(data.getBidPrice()));
        currency.setExchangeRate(new BigDecimal(data.getExchangeRate()));
        currency.setFromCurrencyCode(data.getFromCurrencyCode());
        currency.setFromCurrencyName(data.getFromCurrencyName());
        currency.setLastRefreshed(DateFormatterUtility.convertStringToDateTime(data.getLastRefreshed()));
        currency.setTimeZone(data.getTimeZone());
        currency.setToCurrencyCode(data.getToCurrencyCode());
        currency.setId(UUID.randomUUID());
        currency.setToCurrencyName(data.getToCurrencyName());
        currency.setFetchedTime(LocalDateTime.now());
        currency.setKey(KeyGeneratorUtility.generateKey(data.getToCurrencyCode(), data.getFromCurrencyCode()));
        return Optional.of(currency);
    }

    private Optional<RealtimeCurrencyExchangeRate> parseRealtimeCurrentRate(String response) {
        LOGGER.info("Entered");
        return Optional.ofNullable(gson.fromJson(response, RealtimeCurrencyExchangeRate.class));
    }

    private Optional<DashboardData> parseHistoryData(String response,
                                                     Interval interval,
                                                     String key,
                                                     Frequency frequency) {
        LOGGER.info("Entered");
        try {
            JSONParser parser = new JSONParser(response);
            Map<String, Object> map = parser.parseObject();
            DashboardData dashBoardData = new DashboardData();
            MetaData metaData = setMetadata(map, frequency);
            dashBoardData.setMetaData(metaData);
            dashBoardData.setId(UUID.randomUUID());
            dashBoardData.setKey(key);
            dashBoardData.setFrequency(frequency);
            setTimeSeries(interval, map, dashBoardData, frequency);
            return Optional.of(dashBoardData);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
            return Optional.empty();
        }
    }

    private void setTimeSeries(Interval interval, Map<String, Object> map, DashboardData dashBoardData, Frequency frequency) {
        LOGGER.info("Entered");
        Map<String, Map<String, String>> timeSeries = (Map<String, Map<String, String>>) map
                .get(String.format(Constant.FILTER_SERIES_STRING, interval.getValue()));
        if(timeSeries  == null)
            return;
        for (Entry<String, Map<String, String>> o : timeSeries.entrySet()) {
            TimeSeries series = new TimeSeries();
            for (Entry<String, String> e : o.getValue().entrySet()) {
                if (e.getKey().contains("open")) {
                    series.setOpen(new BigDecimal(e.getValue()));
                }
                if (e.getKey().contains("high")) {
                    series.setHigh(new BigDecimal(e.getValue()));
                }
                if (e.getKey().contains("low")) {
                    series.setLow(new BigDecimal(e.getValue()));
                }
                if (e.getKey().contains("close")) {
                    series.setClose(new BigDecimal(e.getValue()));
                }
            }
            if (frequency == Frequency.MONTHLY)
                series.setDateTime(DateFormatterUtility.convertStringToDate(o.getKey()));
            else
                series.setDateTime(DateFormatterUtility.convertStringToDateTime(o.getKey()));
            List<TimeSeries> ts = dashBoardData.getTimeSeries();
            if (CollectionUtils.isEmpty(ts)) {
                ts = new ArrayList<TimeSeries>();
                dashBoardData.setTimeSeries(ts);
            }
            dashBoardData.getTimeSeries().add(series);
        }
    }

    private MetaData setMetadata(Map<String, Object> map, Frequency frequency) {
        LOGGER.info("Entered");
        MetaData metaData = new MetaData();
        Map<String, String> metaDataMap = (Map<String, String>) map.get("Meta Data");
        if (metaDataMap == null)
            return null;
        for (Entry<String, String> e : metaDataMap.entrySet()) {
            if (e.getKey().contains("Information")) {
                metaData.setInformation(e.getValue());
            }

            if (e.getKey().contains("From Symbol")) {
                metaData.setFromSymbol(e.getValue());
            }

            if (e.getKey().contains("To Symbol")) {
                metaData.setToSymbol(e.getValue());
            }

            if (e.getKey().contains("Last Refreshed")) {
                metaData.setLastRefreshed(DateFormatterUtility.convertStringToDateTime(e.getValue()));
            }

            if (e.getKey().contains("Interval")) {
                metaData.setInterval(Arrays.stream(Interval.values()).filter(c -> c.getValue().equals(e.getValue()))
                        .findFirst().orElse(null));
            }

            if (e.getKey().contains("Output Size")) {
                metaData.setOutputSize(Arrays.stream(OutputSize.values()).filter(c -> c.getValue().equals(e.getValue()))
                        .findFirst().orElse(null));
            }
            if (e.getKey().contains("Time Zone")) {
                metaData.setTimeZone(e.getValue());
            }
        }
        return metaData;
    }

}
