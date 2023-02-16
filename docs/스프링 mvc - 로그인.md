# 로그인

## 서블릿 HTTP 세션

서블릿은 세션 기능을 지원한다. `HttpSession`을 사용하면 된다.

## 필터, 인터셉터

- 필터는 서블릿이 제공하는 기능이다.
- 인터셉터는 스프링이 제공하는 기능이다.

필터와 인터셉터는 스프링 AOP와는 다르게 **웹**과 관련된 부가기능을 제공한다.

## 서블릿 필터

HTTP 요청이 만나게 되는 **문지기**라고 생각하면 편하다.

예를들어 로그인된 사용자만 접근할 수 있는 페이지를 만들려면 컨트롤러에서 해당 로직을 작성해서 메서드마다 적용해야 한다.

하지만 서블릿 필터를 사용하면 HTTP 요청이 들어왔을 때  컨트롤러로 요청을 보내기 전에 로그인 된 사용자인지 확인할 수 있다. 

**HTTP 요청 흐름**
```java
HTTP 요청 -> WAS -> 필터 -> 서블릿 -> 컨트롤러
```

필터에서는 적절하지 않은 요청이라고 판단하면 차단할 수 있다. 필터에서 차단 당한 요청은 더 이상 전달되지 않는다.

## 가장 간단한 필터 만들어보기

모든 HTTP 요청을 로그로 남기는 필터를 직접 만들어 본다. 순서는 다음과 같다.

1. LogFilter 생성하기
2. Filter를 Bean으로 등록하기

### 1. LogFilter 생성하기

```java
@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log filter doFilter");

        HttpServletRequest httpRequest = (HttpServletRequest) request;  // ServletRequest 는 기능이 부족하기 때문에 자식 타입인 HttpServletRequest 로 다운캐스팅한다.
        String requestURI = httpRequest.getRequestURI();                // 모든 HTTP 요청 URI 를 가져온다.

        UUID uuid = UUID.randomUUID();                                  // 각 요청을 식별하기 위해서 UUID 를 생성한다.

        try {
            log.info("REQUEST [{}][{}]", uuid, requestURI);
        chain.doFilter(request, response);      // !중요 - 다음 필터를 직접 호출해줘야 한다. 안 그러면 더 이상 진행되지 않는다.
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("RESPONSE [{}][{}]", uuid, requestURI);
        }
    }

    @Override
    public void destroy() {
        log.info("log filter destroy");
    }
}
```

### 2. Filter를 Bean으로 등록하기

```java
@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter());  // 필터 등록
        filterRegistrationBean.setOrder(1); // 필터 순서 지정
        filterRegistrationBean.addUrlPatterns("/*");    // 대상 URL 패턴을 지정한다. '/'하위의 모든 URL을 지정했다.

        return filterRegistrationBean;
    }
}
```

이제 서버를 띄우고 요청을 보내보면 로그를 확인할 수 있다.

```
2023-02-12 08:57:30.097  INFO 6090 --- [nio-8080-exec-1] c.c.c.web.filter.LogFilter               : REQUEST [a5e13123-8ae4-42a0-824c-2da7c3bd3f38][/]
2023-02-12 08:57:30.129  INFO 6090 --- [nio-8080-exec-1] c.c.c.web.filter.LogFilter               : RESPONSE [a5e13123-8ae4-42a0-824c-2da7c3bd3f38][/]
```

## 인증 체크 필터 만들기

인증이 된 사용자인지 확인하는 필터를 만들어본다. 

1. LoginCheckFilter 생성
2. 