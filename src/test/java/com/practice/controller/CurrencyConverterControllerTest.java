package com.practice.controller;

import com.practice.DataProviderUtility;
import com.practice.dto.CurrencyDto;
import com.practice.model.RealTimeCurrencyExRate;
import com.practice.service.ICurrencyService;
import com.practice.utility.KeyGeneratorUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CurrencyConverterController.class)
public class CurrencyConverterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICurrencyService currencyService;

    private Optional<List<CurrencyDto>> optionalCurrencyDtls;
    private Optional<RealTimeCurrencyExRate> realTimeCurrencyExRateOptional;

    @BeforeEach
    void setUp() {
        optionalCurrencyDtls = Optional.of(DataProviderUtility.getCurrencies());
        String key = KeyGeneratorUtility.generateKey("USD", "INR");
        UUID id = UUID.randomUUID();
        realTimeCurrencyExRateOptional = Optional.of(DataProviderUtility.getRealtimeExRate(id, key));
    }

    @Test
    void shouldReturnAllCurrencies() throws Exception {
        given(currencyService.getAllAvailableCurrency()).willReturn(optionalCurrencyDtls);
        this.mockMvc.perform(get("/v1/cc/")).andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(optionalCurrencyDtls.get().size())))
                .andExpect(jsonPath("$.[0].currencyCode", is(optionalCurrencyDtls.get().get(0).getCurrencyCode())));
    }

    @Test
    void shouldReturn_EmptyCurrencies() throws Exception {
        given(currencyService.getAllAvailableCurrency()).willReturn(Optional.empty());
        this.mockMvc.perform(get("/v1/cc/")).andExpect(status().is4xxClientError());
    }

    @Test
    void shouldReturnRealTimeCurrencyRate_basedOnKey() throws Exception {
        given(currencyService.findRealtimeCurrencyExRate(Mockito.anyString(), Mockito.anyString())).willReturn(realTimeCurrencyExRateOptional);
        this.mockMvc.perform(get("/v1/cc/from/{from}/to/{to}", "USD", "INR")).andExpect(status().isOk())
                .andExpect(jsonPath("$.key", is(realTimeCurrencyExRateOptional.get().getKey())));
    }

    @Test
    void shouldReturn_EmptyRealtimeExRateResponse() throws Exception {
        given(currencyService.findRealtimeCurrencyExRate(Mockito.anyString(), Mockito.anyString())).willReturn(Optional.empty());
        this.mockMvc.perform(get("/v1/cc/from/{from}/to/{to}", "USD", "INR")).andExpect(status().is4xxClientError());
    }
}
