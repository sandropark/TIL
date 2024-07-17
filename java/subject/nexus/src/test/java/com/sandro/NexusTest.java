package com.sandro;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Disabled
@SpringBootTest
class NexusTest {

    @SpringBootApplication(scanBasePackages = "net.huray.platform.horn")
    static class TestContext {
    }

    @Autowired
    HornRestClient hornRestClient;

    @Autowired
    HornClient hornClient;

    @Test
    void smsPush() throws Exception {
        String endPoint = "010-4378-3313";   // SMS를 받을 휴대폰 번호 입력. 예) 010-1234-1234
        hornClient.asyncPush(getSmsRequestedMessageRequestDto(endPoint));
    }

    private RequestedMessageRequestDto getSmsRequestedMessageRequestDto(String endPoint) {
        RequestedMessageRequestDto requestedMessageRequestDto = new RequestedMessageRequestDto();
        requestedMessageRequestDto.setEndpoints(List.of(getEndpoint(endPoint)));
        requestedMessageRequestDto.setMessage(getMessage());
        return requestedMessageRequestDto;
    }

    private EmbeddedEndpoint getEndpoint(String endPoint) {
        EmbeddedEndpoint endpoint = new EmbeddedEndpoint();
        endpoint.setUserId("user1");
        endpoint.setEndpoint(endPoint);
        endpoint.setType(EndpointType.SMS);
        return endpoint;
    }

    private EmbeddedMessage getMessage() {
        EmbeddedMessage message = new EmbeddedMessage();
        message.setTitle("SMS 발송 테스트");
        message.setContent("안녕하세요!");
        return message;
    }


}