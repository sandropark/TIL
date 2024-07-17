package com.sandro.docs.member;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @AutoConfigureMockMvc  // MockMvc를 자동으로 설정해서 주입해준다.
// @AutoConfigureRestDocs  //  RestDocumentationResultHandler 자동으로 설정해서 주입해준다.
//@Import(value = {RestDocsConfig.class})                 // 커스텀한 RestDocumentationResultHandler 사용
@ExtendWith(RestDocumentationExtension.class) // When using JUnit5
@SpringBootTest
class MemberApiTest {

//    @Autowired
//    RestDocumentationResultHandler restDocs;

    MockMvc mvc;

    /**
     * MockMvc 커스텀
     */
    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation)
                        .operationPreprocessors()
                        .withRequestDefaults(prettyPrint())
                        .withResponseDefaults(prettyPrint())
                )
                .alwaysDo(print())
//                .alwaysDo(restDocs) // 스니펫 생성을 스프링부트에게 맡기기 위해서 사용
                .build();
    }

    @DisplayName("회원 ID로 회원을 조회한다.")
    @Test
    void getMembers() throws Exception {
        mvc.perform(RestDocumentationRequestBuilders.get("/api/members/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
//                .andDo(restDocs.document()) // 이렇게만 하면 스프링부트가 자동으로 스니펫을 생성해준다.
                .andDo(
//                        restDocs.document(
                        MockMvcRestDocumentationWrapper.document(
                                "{method-name}",
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("ID"),
                                fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("이름")

                        )))
        ;
    }

}