# Spring Rest Docs + Swagger UI 사용하기

## 설정

### 1. Build.gradle

```groovy
buildscript {
    ext {
        set('epagesRestDocsApiSpecVersion', "0.16.4")                
        set('staticsDir', file('src/main/resources/static'))    // openapi 가 생성될 위치
    }
}

plugins {
    id 'com.epages.restdocs-api-spec' version "$epagesRestDocsApiSpecVersion"  // Rest Docs -> Open API 3 스팩으로 출력하는 Gradle task가 포함
}

dependencies {
    testImplementation 'org.springframework.restdocs:spring-restdocs-mockmvc'                      // Rest Docs
    testImplementation "com.epages:restdocs-api-spec-mockmvc:${epagesRestDocsApiSpecVersion}"      // Rest Docs -> Open API 3 Spec
}

openapi3 {  // openapi3로 Opnen API 3 스팩을 만들 때 필요한 부가정보

    // Swagger로 API 테스트 시 요청을 보낼 서버 uri. 1개만 지정할 때는 server를. 각각 다른 환경을 지정하고 싶다면 servers를 사용하면 된다.
    server = 'http://localhost:8080'
		servers = [
            { url = "http://localhost:8080" },          // local
            { url = "http://dev-my.net" },    // dev
            { url = "http://my.net" }         // prd
    ]

    title = 'MY API'
    description = 'My API description'
    version = '0.1.0'
    format = 'yaml'
    outputDirectory = "$staticsDir/docs"  // openapi3 파일이 해당 위치에 생성되어야 빌드 시 다른 정적 파일들과 함께 빌드된다.
}


// ---

// 만약 빌드시 openapi3가 생성되지 않는다면 아래 테스크 설정. bootJar 실행 전, openapi3 를 실행하도록 한다. 배포 시 Github Actions를 이용한다면 빌드 전 openapi3 테스크를 수행하게 해도 된다.
bootJar {
    dependsOn 'openapi3'
}
```

### 2. Swagger UI 정적 파일

1. https://github.com/swagger-api/swagger-ui/releases/latest
2. `Source Code` 다운로드
3. 압축 해제 후 `dist` 하위 파일을 프로젝트의 `static/docs` 로 복붙한다. (필수만 있으면 된다.)
    - 필수
        - favicon-*
        - index.*
        - swagger-initializer.js
        - swagger-ui.css
        - swagger-ui-bundle.js
        - swagger-ui-standalone-preset.js

### 3. 테스트 코드 작성

```java
@ExtendWith(RestDocumentationExtension.class)  // 필수
@SpringBootTest
class ControllerTest {
    
    MockMvc mvc;
    
    // 빈으로 등록해도 무방
    // adoc 생성 시 디렉토리 설정 및 JSON 포맷팅 설정
    RestDocumentationResultHandler restDocs = MockMvcRestDocumentationWrapper.document(
             "{class-name}/{method-name}",
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint())
    );

    
    // MockMvc를 살짝 커스텀해야 한다. 
    @BeforeEach
    void setUp(WebApplicationContext context, RestDocumentationContextProvider provider) {
        this.mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(documentationConfiguration(provider))
                            .alwaysDo(restDocs)
                            .alwaysDo(print())
                .build();
    }

    @DisplayName("ID로 사용자를 조회한다.")
    @Test
    void getUser() throws Exception {
        // 기존의 MockMvcRequestBuilders 대신 RestDocumentationRequestBuilders 를 사용한다. API는 동일.
        ResultActions result = mvc.perform(RestDocumentationRequestBuilders  
                .get("/api/v1/users/{userId}", userId)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
        );

        result
                .andExpect(status().isOk())
                .andDo(restDocs.document(
                               ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("User")
                                                .summary("사용자 조회")
                                                .description("ID로 사용자를 조회한다.")
                                                .pathParameters(parameterWithName("userId").description("사용자 ID"))
                                                .responseSchema(Schema.schema("UserResponse"))
                                                .responseFields(
                                                        fieldWithPath("name").description("이름").type(JsonFieldType.STRING),
                                                        fieldWithPath("age").description("나이").type(JsonFieldType.NUMBER).optional()
                                                )
                                                .build()
                                )
                        )
                )
        ;
    }
}
```

- 테스트 작성 후 openapi3 테스크를 실행하면 테스트가 수행되고 `build/resources/main/static/docs/` 에 openapi3.yml을 포함한 swaggerUI 정적 파일들이 생성된다.
- 서버를 띄우고 `/docs/index.html`로 접속해보면 SwaggerUI를 확인할 수 있다. 
- 자세한 사용방법은 https://github.com/ePages-de/restdocs-api-spec를 참고한다.
