Jasypt는 자바에서 **암호화**를 쉽게할 수 있는 라이브러리다. 

예를들어 `application.yml`에 DB Connection 정보를 기입해두는 경우 url, username, password 와 같이 중요한 정보가 노출될 수 있다. 이때 Jasypt를 사용해서 해당 프로퍼티를 암호화할 수 있다. 

## 사용법

```java
@Configuration
class JasyptConfig {
 
    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
 
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        
        config.setPassword("test"); // encrypt key
        
        config.setAlgorithm("PBEWITHMD5ANDDES");
        config.setPoolSize("1");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }
}
```

`encryptor`를 객체를 생성한 뒤 객체를 사용해서 원하는 데이터를 암호화 및 복호화 한다. 예시 코드에서는 encryptor 객체를 생성해서 설정한 뒤 빈으로 등록하는 코드다. 

여기서 가장 중요한 부분은 `config.setPassword()`다. 암호화한 코드를 복호화하기 위해서는 암호화할 때 사용한 객체에 설정한 비밀번호와 같은 비밀번호를 설정한 객체를 사용해야 한다. 만약 비밀번호가 다르다면 복호화시 예외가 발생하게 된다. 

암호화한 코드는 `ENC()`로 감싸줘야 한다.

```yaml
spring.datasource:
  url : ENC(4ZaqS2McicWUUV6VSLPuVA==)
```

비밀번호의 경우 환경변수를 사용해서 관리하면 된다.