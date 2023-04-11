스프링 환경에서 외부 API를 편하게 호출할 수 있는 라이브러리다. 

## FeignClient로 Integration Test하기

`@SpringBootTest`로 통합테스트를 진행할 경우 port 설정을 해야 한다.

1. `application.yml`에 `server.port: {원하는 port}`로 설정한다.
2. Client의 url에 `http://localhost:{원하는 port}`로 설정한다. port는 application.yml에서 설정한 port와 동일하게 맞춘다.
3.  Test Class에 붙은 `@SpringBootTest` 옵션을 설정한다. `(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)` 이렇게 설정해야 application.yml에 설정한 port가 적용된다.