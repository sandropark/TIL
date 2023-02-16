# Java 수행시간 측정 방법

## nonoTime

nanotime은 자바 기본 라이브러리에 포함된 기능이다. nanoTime은 기준 시점에서 경과된 시간을 **나노초** 단위로 측정한다. 

> 나노초 (nanoseconds) : 십억분의 1초

```
long start = System.nanoTime();
// 수행 코드
long end = System.nanoTime();
long time = end - start;
```

## StopWatch

스프링 프레임워크를 사용한다면 StopWatch를 사용할 수 있다. 

```
stopWatch.start("테스크 1");
// 수행 코드
stopWatch.stop();

stopWatch.start("테스크 2");
// 수행 코드
stopWatch.stop();

System.out.println(stopWatch.prettyPrint());
```

StopWatch는 인스턴스 변수로 상태를 가지고 있기 때문에 `start(),stop()`을 사용해서 여러 테스크의 수행시간을 측정해두고 `prettyPrint()`을 사용해서 한 번에 결과를 확인할 수 있다. 

```
---------------------------------------------
ns         %     Task name
---------------------------------------------
000003959  089%  테스크1
000000500  011%  테스크2
```

StopWatch 역시 내부에서 `nanoTime`을 사용한다.

### 정리
---
스프링을 사용할 때는 `StopWatch`를, 그렇지 않다면 `nanoTime`을 사용하면 될 것 같다.
