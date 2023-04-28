# Redis

Remote dictionary server의 줄임말이다. In Memory DB이기 때문에 **메모리 상**에 데이터를 저장한다. Single Thread를 사용한다.

자주 접근하고 자주 바뀌지 않는 데이터를 메모리에 올려두고 데이터에 접근하는 시간을 줄이고자 사용한다.

Redis는 여러 자료구조를 지원한다. 

### In Memory라면 그냥 Java의 Map(자료구조)를 사용해서 관리해도 되는데 왜 Redis를 사용할까?

서버를 여러 대 사용할 경우 서버마다 별도의 메모리를 사용하기 때문에 효율이 떨어지게 된다. Redis는 동기화를 제공한다. 

### 왜 사용할까? 

로컬에서 캐싱하면 세션 같이 여러 서버에서 하나의 자원을 공유해야 하는 경우에는 방법이 없기 때문에 Redis를 사용한다. 

### 주의점

- Single Thread 서버이기 때문에 **시간 복잡도**를 고려해야 한다.
  - O(n)의 명령어의 경우 사용하지 않는 것이 좋다.

- In Memory DB이기 때문에 가상메모리 같은 OS 지식이 필요하다.

## 설치

Homebrew를 이용해서 설치하면 된다.

## 실행

`brew services start redis`

# Redis 역직렬화 예외 발생 시 해결 방법

## 문제 상황

`UserDetails`를 구현한 객체의 필드를 추가한 다음 배포하는 경우 `SerializationException`이 발생한다. 

<details>
<summary>예외 보기</summary>
<div markdown="1">

```
2023-04-28 13:37:53.862 ERROR 20661 --- [nio-8084-exec-1] o.a.c.c.C.[Tomcat].[localhost]           : Exception Processing ErrorPage[errorCode=0, location=/error]

org.springframework.data.redis.serializer.SerializationException: Cannot deserialize; nested exception is org.springframework.core.serializer.support.SerializationFailedException: Failed to deserialize payload. Is the byte array a result of corresponding serialization for DefaultDeserializer?; nested exception is java.io.InvalidClassException: net.huray.himom.core.data.dto.admin.BoDetailDto; local class incompatible: stream classdesc serialVersionUID = -47294078824655423, local class serialVersionUID = 4678237296459558567
	at org.springframework.data.redis.serializer.JdkSerializationRedisSerializer.deserialize(JdkSerializationRedisSerializer.java:84) ~[spring-data-redis-2.7.0.jar:2.7.0]
	at org.springframework.data.redis.core.AbstractOperations.deserializeHashValue(AbstractOperations.java:380) ~[spring-data-redis-2.7.0.jar:2.7.0]
	at org.springframework.data.redis.core.AbstractOperations.deserializeHashMap(AbstractOperations.java:324) ~[spring-data-redis-2.7.0.jar:2.7.0]
	at org.springframework.data.redis.core.DefaultHashOperations.entries(DefaultHashOperations.java:309) ~[spring-data-redis-2.7.0.jar:2.7.0]
	at org.springframework.data.redis.core.DefaultBoundHashOperations.entries(DefaultBoundHashOperations.java:223) ~[spring-data-redis-2.7.0.jar:2.7.0]
	at org.springframework.session.data.redis.RedisIndexedSessionRepository.getSession(RedisIndexedSessionRepository.java:457) ~[spring-session-data-redis-2.7.0.jar:2.7.0]
	at org.springframework.session.data.redis.RedisIndexedSessionRepository.findById(RedisIndexedSessionRepository.java:429) ~[spring-session-data-redis-2.7.0.jar:2.7.0]
	at org.springframework.session.data.redis.RedisIndexedSessionRepository.findById(RedisIndexedSessionRepository.java:251) ~[spring-session-data-redis-2.7.0.jar:2.7.0]
	at net.huray.himom.admin.repository.InvalidClassExceptionSafeRepository.findById(InvalidClassExceptionSafeRepository.java:26) ~[classes/:na]
	at org.springframework.session.web.http.SessionRepositoryFilter$SessionRepositoryRequestWrapper.getRequestedSession(SessionRepositoryFilter.java:356) ~[spring-session-core-2.7.0.jar:2.7.0]
	at org.springframework.session.web.http.SessionRepositoryFilter$SessionRepositoryRequestWrapper.getSession(SessionRepositoryFilter.java:290) ~[spring-session-core-2.7.0.jar:2.7.0]
	at org.springframework.session.web.http.SessionRepositoryFilter$SessionRepositoryRequestWrapper.getSession(SessionRepositoryFilter.java:193) ~[spring-session-core-2.7.0.jar:2.7.0]
	at javax.servlet.http.HttpServletRequestWrapper.getSession(HttpServletRequestWrapper.java:244) ~[tomcat-embed-core-9.0.63.jar:4.0.FR]
	at org.springframework.security.web.session.ConcurrentSessionFilter.doFilter(ConcurrentSessionFilter.java:130) ~[spring-security-web-5.7.1.jar:5.7.1]
	at org.springframework.security.web.session.ConcurrentSessionFilter.doFilter(ConcurrentSessionFilter.java:125) ~[spring-security-web-5.7.1.jar:5.7.1]
	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336) ~[spring-security-web-5.7.1.jar:5.7.1]
	at org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter.doFilter(AbstractAuthenticationProcessingFilter.java:223) ~[spring-security-web-5.7.1.jar:5.7.1]
	at org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter.doFilter(AbstractAuthenticationProcessingFilter.java:217) ~[spring-security-web-5.7.1.jar:5.7.1]
	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336) ~[spring-security-web-5.7.1.jar:5.7.1]
	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:103) ~[spring-security-web-5.7.1.jar:5.7.1]
	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:89) ~[spring-security-web-5.7.1.jar:5.7.1]
	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336) ~[spring-security-web-5.7.1.jar:5.7.1]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:102) ~[spring-web-5.3.20.jar:5.3.20]
	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336) ~[spring-security-web-5.7.1.jar:5.7.1]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:102) ~[spring-web-5.3.20.jar:5.3.20]
	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336) ~[spring-security-web-5.7.1.jar:5.7.1]
	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:89) ~[spring-security-web-5.7.1.jar:5.7.1]
	at org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:82) ~[spring-security-web-5.7.1.jar:5.7.1]
	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336) ~[spring-security-web-5.7.1.jar:5.7.1]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:102) ~[spring-web-5.3.20.jar:5.3.20]
	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336) ~[spring-security-web-5.7.1.jar:5.7.1]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:102) ~[spring-web-5.3.20.jar:5.3.20]
	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336) ~[spring-security-web-5.7.1.jar:5.7.1]
	at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:211) ~[spring-security-web-5.7.1.jar:5.7.1]
	at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:183) ~[spring-security-web-5.7.1.jar:5.7.1]
	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:354) ~[spring-web-5.3.20.jar:5.3.20]
	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:267) ~[spring-web-5.3.20.jar:5.3.20]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100) ~[spring-web-5.3.20.jar:5.3.20]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117) ~[spring-web-5.3.20.jar:5.3.20]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:102) ~[spring-web-5.3.20.jar:5.3.20]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at org.springframework.session.web.http.SessionRepositoryFilter.doFilterInternal(SessionRepositoryFilter.java:142) ~[spring-session-core-2.7.0.jar:2.7.0]
	at org.springframework.session.web.http.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:82) ~[spring-session-core-2.7.0.jar:2.7.0]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:102) ~[spring-web-5.3.20.jar:5.3.20]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at org.apache.catalina.core.ApplicationDispatcher.invoke(ApplicationDispatcher.java:711) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at org.apache.catalina.core.ApplicationDispatcher.processRequest(ApplicationDispatcher.java:461) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at org.apache.catalina.core.ApplicationDispatcher.doForward(ApplicationDispatcher.java:385) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at org.apache.catalina.core.ApplicationDispatcher.forward(ApplicationDispatcher.java:313) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at org.apache.catalina.core.StandardHostValve.custom(StandardHostValve.java:403) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at org.apache.catalina.core.StandardHostValve.status(StandardHostValve.java:249) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at org.apache.catalina.core.StandardHostValve.throwable(StandardHostValve.java:344) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:169) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:687) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:360) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:399) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:890) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1743) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61) ~[tomcat-embed-core-9.0.63.jar:9.0.63]
	at java.base/java.lang.Thread.run(Thread.java:829) ~[na:na]
Caused by: org.springframework.core.serializer.support.SerializationFailedException: Failed to deserialize payload. Is the byte array a result of corresponding serialization for DefaultDeserializer?; nested exception is java.io.InvalidClassException: net.huray.himom.core.data.dto.admin.BoDetailDto; local class incompatible: stream classdesc serialVersionUID = -47294078824655423, local class serialVersionUID = 4678237296459558567
	at org.springframework.core.serializer.support.DeserializingConverter.convert(DeserializingConverter.java:78) ~[spring-core-5.3.20.jar:5.3.20]
	at org.springframework.core.serializer.support.DeserializingConverter.convert(DeserializingConverter.java:36) ~[spring-core-5.3.20.jar:5.3.20]
	at org.springframework.data.redis.serializer.JdkSerializationRedisSerializer.deserialize(JdkSerializationRedisSerializer.java:82) ~[spring-data-redis-2.7.0.jar:2.7.0]
	... 73 common frames omitted
Caused by: java.io.InvalidClassException: net.huray.himom.core.data.dto.admin.BoDetailDto; local class incompatible: stream classdesc serialVersionUID = -47294078824655423, local class serialVersionUID = 4678237296459558567
	at java.base/java.io.ObjectStreamClass.initNonProxy(ObjectStreamClass.java:560) ~[na:na]
	at java.base/java.io.ObjectInputStream.readNonProxyDesc(ObjectInputStream.java:2020) ~[na:na]
	at java.base/java.io.ObjectInputStream.readClassDesc(ObjectInputStream.java:1870) ~[na:na]
	at java.base/java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:2201) ~[na:na]
	at java.base/java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1687) ~[na:na]
	at java.base/java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:2496) ~[na:na]
	at java.base/java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:2390) ~[na:na]
	at java.base/java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:2228) ~[na:na]
	at java.base/java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1687) ~[na:na]
	at java.base/java.io.ObjectInputStream.defaultReadFields(ObjectInputStream.java:2496) ~[na:na]
	at java.base/java.io.ObjectInputStream.readSerialData(ObjectInputStream.java:2390) ~[na:na]
	at java.base/java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:2228) ~[na:na]
	at java.base/java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1687) ~[na:na]
	at java.base/java.io.ObjectInputStream.readObject(ObjectInputStream.java:489) ~[na:na]
	at java.base/java.io.ObjectInputStream.readObject(ObjectInputStream.java:447) ~[na:na]
	at org.springframework.core.serializer.DefaultDeserializer.deserialize(DefaultDeserializer.java:72) ~[spring-core-5.3.20.jar:5.3.20]
	at org.springframework.core.serializer.support.DeserializingConverter.convert(DeserializingConverter.java:73) ~[spring-core-5.3.20.jar:5.3.20]
	... 75 common frames omitted
  ```

</div>
</details>

<br>

`SessionRepositoryFilter`는 `RedisIndexedSessionRepository`를 통해 세션을 가져온다. 
하지만 세션을 가져오는 과정에서 UID를 이용해서 서로 같은지 확인하는데 UID는 `UserDetails` 객체의 형태(필드 등)에 따라 바뀐다. 
`UserDetails`의 필드를 추가했기 때문에 UID는 변경되었고 이 때문에 `SerializationException`이 발생하면서 예외가 그대로 밖으로 전파된다.

해결책은 다시 로그인을 하면 된다. 하지만 `SerializationException`이 발생하면 중간에서 처리하지 않기 때문에 그대로 밖으로 전파되면서 500에러가 떠서 로그인 페이지로 들어가지도 못한다.

## 해결책

`SerializationException`이 발생할 경우 중간에서 예외를 잡아주면 된다. 

`RedisIndexedSessionRepository`의 `findById()`에서 세션을 조회하기 때문에 여기서 예외를 잡으면 된다. 

그러기 위해서는 다음 2가지 작업을 하면 된다.
  1. `SessionRepository`를 구현 
  2. `RedisConfig`에 Repository 등록

### 1. `SessionRepository` 구현 
```Java
public class InvalidClassExceptionSafeRepository<S extends Session> implements SessionRepository<S> {
    private final SessionRepository<S> repository;

    public InvalidClassExceptionSafeRepository(SessionRepository<S> repository) {
        this.repository = repository;
    }

    public S createSession() {
        return repository.createSession();
    }

    public void save(S session) {
        repository.save(session);
    }

    @Override
    public S findById(String id) {
        try {
            return repository.findById(id);
        } catch (SerializationException e) {
            return null;  // 세션을 찾지 못했다는 의미로 null을 반환한다.
        }
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

```

### 2. `RedisConfig`에 Repository 등록

```Java
@Primary
@Bean
public SessionRepository primarySessionRepository(RedisIndexedSessionRepository delegate) {
    return new InvalidClassExceptionSafeRepository(delegate);
}
```

이렇게 설정하면 이제 `SessionRepositoryFilter`는 바로 `RedisIndexedSessionRepository`를 사용하지 않고 직접 구현한 `InvalidClassExceptionSafeRepository`를 통해서 
`RedisIndexedSessionRepository`를 사용한다. 

이제 `SerializationException`이 발생해도 `InvalidClassExceptionSafeRepository`에서 catch 해서 null로 반환하기 때문에 500 에러가 나지 않고 로그인 페이지가 보이게 된다.