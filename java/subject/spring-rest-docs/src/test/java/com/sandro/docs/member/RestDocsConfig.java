package com.sandro.docs.member;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

@TestConfiguration
public class RestDocsConfig {

    @Bean
    public RestDocumentationResultHandler write() {
        /**
         *  테스트 결과를 어떻게 처리할지 커스텀한다.
         */
        return MockMvcRestDocumentation.document(
                "{class-name}/{method-name}", // 스니펫이 생성되는 경로 지정
                preprocessRequest(
                        prettyPrint(), // 요청 Body를 포맷팅해서 출력
                        modifyUris().scheme("https").host("docs.api.com").removePort()  // 문서의 uri를 "https://docs.api.com"으로 변경
                ),
                preprocessResponse(prettyPrint())   // 응답 Body를 포맷팅해서 출력
        );
    }
}
