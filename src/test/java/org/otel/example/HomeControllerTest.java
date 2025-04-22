package org.otel.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class HomeControllerTest {

    @Autowired MockMvc mockMvc;

    @Test
    void shouldCallHomeController() throws Exception {

        mockMvc.perform(get("/api/home"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("Hello, OpenTelemetry!")));
    }

}