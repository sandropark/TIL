package com.sandro.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TestController2.class)
class TestController2Test {

    @Autowired MockMvc mvc;

    @Test
    void test() throws Exception {
        mvc.perform(get("/api/v2/hello"))
                .andDo(print())
                .andExpect(status().isOk());
    }


}