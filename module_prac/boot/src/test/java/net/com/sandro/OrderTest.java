package net.com.sandro;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.com.sandro.order.Order;
import net.com.sandro.order.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class OrderTest {
    @Autowired MockMvc mvc;
    @Autowired ObjectMapper objectMapper;
    @Autowired OrderRepository orderRepository;

    @Test
    void createOrder() throws Exception {
        // Given
        ExternalOrderCreateReq req = ExternalOrderCreateReq.builder().price(10f).build();

        // When
        ResultActions resultActions = mvc.perform(post("/api/v1/order")
                .content(objectMapper.writeValueAsString(req))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8));

        // Then
        resultActions
                .andExpect(status().isOk())
                .andExpectAll(jsonPath("$.price").value(10f))
                .andDo(print());

        List<Order> foundOrders = orderRepository.findAll();
        assertThat(foundOrders)
                .hasSize(1)
                .extracting(Order::getPrice)
                .contains(10f);
    }
}
