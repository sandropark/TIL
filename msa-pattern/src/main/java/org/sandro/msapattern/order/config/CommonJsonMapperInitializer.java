package org.sandro.msapattern.order.config;

import io.eventuate.common.json.mapper.JSonMapper;
import jakarta.annotation.PostConstruct;

public class CommonJsonMapperInitializer {

    @PostConstruct
    public void initialize() {
        registerMoneyModule();
    }

    public static void registerMoneyModule() {
        JSonMapper.objectMapper.registerModule(new MoneyModule());
    }
}