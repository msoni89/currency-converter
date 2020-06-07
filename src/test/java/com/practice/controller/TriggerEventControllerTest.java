package com.practice.controller;

import com.practice.confriguration.TestRedisConfiguration;
import com.practice.dto.ResponseDto;
import com.practice.service.ITriggerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TriggerEventController.class)
public class TriggerEventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ITriggerService triggerService;

    @Test
    void shouldReturn_SuccessMessage() throws Exception {
        ResponseDto dto = new ResponseDto("Success", 200);
        Optional<ResponseDto> optional = Optional.of(dto);
        given(triggerService.trigger(Mockito.anyString(), Mockito.anyString())).willReturn(optional);
        this.mockMvc.perform(put("/v1/rates/from/{from}/to/{to}", "USD", "INR"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(optional.get().getMessage())));

    }
}
