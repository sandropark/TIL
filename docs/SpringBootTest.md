# SpringBootTest 시 원하는 Bean만 로드하는 법

## 상황

서비스를 테스트하고 싶은데 @SpringBootTest로 테스트를 돌리면 main 패키지의 SpringApplication을 읽어서 빈을 가져오기 때문에 너무 많은 빈을 가져오게 된다. 빈마다 가진 의존성을 챙겨주지 못하면 ContextLoad 에러가 자꾸 발생해서 테스트가 불가능하다. 덤으로 속도도 느리다.

main 패키지의 SpringApplication을 수정할 수는 없기 때문에 test 패키지에 원하는 빈만 스캔하는 SpringApplication을 생성해서 테스트 시 사용하기로 했다.

## 해결

@SpringBootTest가 main 패키지의 @SpringApplication을 로드하지 않고 test 패키지에 생성한 @SpringApplication 을 로드하게 설정을 추가해줘야 한다. 

먼저 test 패키지에 원하는 빈만 스캔하는 SpringApplication 을 생성한다. 여기까지만 하면 @SpringBootTest 는 main과 test 패키지의 2개의 SpringApplication 을 모두 읽으면서 에러가 난다. 원하는 SpringApplication 을 정하기 위해서는 `@ContextConfiguration` 을 사용해야 한다.

테스트 클래스에 `@ContextConfiguration(classes = [Test 패키지의 SpringApplication].class)` 를 추가해준다.

이렇게 하면 @SpringBootTest는 더 이상 main의 SpringApplication 을 찾지 않고 test의 SpringApplication 를 사용한다.