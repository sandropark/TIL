## Builder 사용시 Generic 적용하기

```java
RequestParam<String> req =  RequestParam.<String>builder().body("TEST Value").build();
```

이런식으로 Generic을 명시할 수 있다.