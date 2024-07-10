### 배치 시스템이란?

일정 시간마다 일괄적으로 작업을 처리하는 시스템이다. 대체로 대용량 데이터를 처리한다. 

#### 배치 시스템이 필요한 상황?

- 데이터를 모아서 한 번에 처리해야 할 때. 예) 월별 거래 명세서 생성
- 일부러 지연시켜서 처리할 때. 예) 쇼핑몰에서 주문이 들어오면 바로 배송처리 하지 않고 일정 시간에 한 번에 배송처리하기.
- 자원을 효율적으로 활용하기 위해. 예) 트래픽이 적은 시간 대에 서버 리소스 활용

#### 활용예시

- 메시지, 이메일, 푸시 등을 발송할 때.
- 데이터를 마이그레이션할 때. 테이블을 새로 만들고 기존 데이터를 마이그레이션하는 경우가 왕왕있다.
- 실패한 트랜잭션을 재처리할 때.
- 쿠폰, 포인트가 만료된 경우 소진시키는 처리.
- 월말 또는 월초에 특정 데이터를 생성할 때. 거래 명세서 등.

---

# Spring Batch

목차
- [Architecture](#architecture)
    - [Layer](#layer)
    - [Job](#job)
    - [Step](#step)
    - [스키마 구조](#spring-batch-스키마-구조)

## Architecture

<img width="600" alt="image" src="https://user-images.githubusercontent.com/129827948/231910754-7ab304ab-18ea-4577-b55b-3fe4433df267.png">

- JobLauncher 
    
    Job을 실행시키는 컴포넌트
- Job

    실행할 작업. Job은 여러 Step으로 구성되어 있다.
- Step

    Step은 데이터를 읽고, 가공하고, 쓰는 컴포넌트로 구성되어 있다. 
    - ItemReader
    - ItemProcessor
    - ItemWriter
- JopRepository

    Job 실행과 Step을 저장하는 곳

### Layer

Spring Batch는 3가지 레이어로 구성되어 있다. 

<img width="250" alt="image" src="https://user-images.githubusercontent.com/129827948/231911479-6981737e-78b0-4330-8779-62db94a444b1.png">

- Application

    사용자의 코드와 구성으로 비즈니스, 서비스 로직이다.
- Spring Batch
    - Core

        배치 작업을 시작하고 제어하는데 필수적인 클래스. Job, Step, JobLauncher 등.
    - Infrastructure

        외부와 상호작용하는 레이어다. ItemReader, ItemWriter, RetryTemplate 등.

### Job

전체 배치 프로세스를 캡슐화한 도메인이다. Step의 순서를 정의한다. JobParameter를 받는다. 

<img width="600" alt="image" src="https://user-images.githubusercontent.com/129827948/231912251-6d35e90d-0a66-441e-89e0-787fe231f61c.png">

Job은 언제 무엇을 할 지 정의되어 있고 JobParameters를 받아서 Instance를 생성한다. 그 다음 Job을 수행한다. 

### Step

작업 처리의 단위다. `Chunk`, `Tasklet` 기반 스텝 2가지로 나뉜다. `Chunk` 기반의 Step을 많이 사용한다. 

#### Chunck-oriented Processing Step

<img width="500" alt="image" src="https://user-images.githubusercontent.com/129827948/231913195-6a72dcfe-e51d-4977-ba07-d36cf7046a8d.png">

`Chunk` 기반으로 하나의 트랜잭션에서 데이터를 처리한다. 데이터를 읽고, 가공하고, 쓰는 작업을 하나의 트랜잭션으로 묶어서 작업한다. 

- chunkSize : 한 트랜잭션에서 쓸 아이템의 갯수.
- commitInterval : Reader가 한 번에 읽을 아이템의 갯수.

예를 들어 chunkSize가 10이라면 이 작업(트랜잭션)에서 사용하는 아이템은 총 10개라는 뜻이다. commitInterval이 5라면 한 번에 5개씩 아이템을 읽어온 뒤 가공해서 데이터를 쓰기 전에 모아둔다. chunkSize(10)가 될 때까지 다시 데이터를 5개 더 읽어오고 가공한 뒤 데이터가 10개가 되었다면 한 번에 쓰기한다. 어차피 ChunkSize에 맞춰서 한 단위로 묶이기 때문에 웬만하면 chunkSize와 commitInterval은 동일하게 하는 것이 좋다. 

#### TaskletStep

Chunk Step 처럼 읽기, 가공, 수정을 분리하지 않고 한 번에 처리할 때 사용한다. 보통 단순한 처리를 할 때 사용한다. 

### Spring Batch 스키마 구조

배치를 실행하고 관리하는 meta data를 저장하는 스키마다.

<img width="600" alt="image" src="https://user-images.githubusercontent.com/129827948/231917422-ef2d137f-4e2b-4413-a9ac-2356b0a2c089.png">

Spring Batch 실행 시 meta data 테이블을 사용하기 때문에 **초기 설정**을 해야 한다. 

## 사용법

스프링 배치를 사용하기 위해서는 DB를 연결해둬야 한다. 배치가 실행되면 로그를 DB에 저장하기 때문이다. 미리 DB를 연결해둔다.

스프링 배치를 사용하려면 몇 가지 설정을 해야 한다. HelloWorld 수준으로 Spring Batch를 사용해본다. 

- [application.yml](#applicationyml) (Option)
- [SpringBootApplication](#springbootapplication)
- [JobConfig](#jobconfig)

#### application.yml

어떤 job을 실행할지 설정할 수 있다.

```yaml
spring:
  batch:
    job:
      names: ${job.name:NONE}
```

#### SpringBootApplication

`@EnableBatchProcessing`을 달아줘야 한다.

```java
@EnableBatchProcessing
@SpringBootApplication
```

### Tasklet

Job과 Step을 설정할 수 있다. 

```java
@Configuration
@RequiredArgsConstructor
public class HelloJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloJob(Step helloStep) {   // Job 설정
        return jobBuilderFactory.get("helloJob")
                .incrementer(new RunIdIncrementer())
                .start(helloStep)   // Step을 지정했다.
                .build();
    }

    @JobScope
    @Bean
    public Step helloStep(Tasklet tasklet) {    // Step 설정
        return stepBuilderFactory.get("helloStep")
                .tasklet(tasklet)   // Chunk와 Tasklet 중 선택할 수 있다. 단순하게 Tasklet을 사용했다.
                .build();
    }

    @StepScope
    @Bean
    public Tasklet tasklet() {  // Tasklet 설정
        return (((contribution, chunkContext) -> {
            System.out.println("Hello Spring Batch");
            return RepeatStatus.FINISHED;
        }));
    }
}
```

Job과 Step과 Tasklet을 각각 빈으로 등록한 뒤 Step에 Tasklet을 주입, Job에 Step을 주입했다. 

---

3가지 설정을 다 한다음 `Edit Configuration` -> `Program arguments`에 `--spring.batch.job.names=helloJob` 이렇게 적어주면 Bean으로 등록한 Job을 실행하게 된다. 

이제 Application을 실행하면 `Hello Spring Batch`가 출력된다. 

#### DB 확인

배치를 실행한 뒤 DB를 확인해보면 어떤 작업을 했는지 로그처럼 데이터가 쌓인 것을 확인할 수 있다. 

#### 빌드 및 실행

프로젝트를 빌드하면 `build/lib/`에 jar 파일이 생성된다. 

`java -jar build/lib/__.jar --spring.batch.job.names=helloJob` 명령어를 실행하면 jar 파일을 이용해서 배치를 돌릴 수 있다. 

---

실무에서 배치를 사용할 때는 `Quartz` 프레임워크나 `Jenkins`와 함께 사용한다. 

### Chunck

```java
@RequiredArgsConstructor
@Configuration
public class PlainTextJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final PlainTextRepository plainTextRepository;

    @Bean
    public Job plainTextJob(Step plainTextStep) {
        return jobBuilderFactory.get("plainTextJob")
                .incrementer(new RunIdIncrementer())
                .start(plainTextStep)
                .build();
    }

    @JobScope
    @Bean
    public Step plainTextStep(ItemReader<PlainText> plainTextReader,
                              ItemProcessor<PlainText, String> plainTextProcessor,
                              ItemWriter<String> plainTextWriter) {
        return stepBuilderFactory.get("plainTextStep")
                .<PlainText, String>chunk(5)    // ItemReader에서 지정한 크기와 동일하게 설정한다. 
                .reader(plainTextReader)
                .processor(plainTextProcessor)
                .writer(plainTextWriter)
                .build();
    }

    @StepScope
    @Bean
    public RepositoryItemReader<PlainText> plainTextReader() {  // DB에서 데이터를 읽어온다.
        return new RepositoryItemReaderBuilder<PlainText>() // 가져올 데이터 타입
                .name("plainTextReader")
                .repository(plainTextRepository)  // 사용할 Repository
                .methodName("findBy")   
                .pageSize(5)    // 한 번에 가져올 데이터의 수
                .arguments(List.of())
                .sorts(Collections.singletonMap("id", Sort.Direction.DESC))
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<PlainText, String> plainTextProcessor() {  // 데이터를 가공한다. <읽어올 데이터 타입, 변환할 타입>
        return item -> "processed " + item.getText();  // PlainText 객체를 String으로 변환한다.
    }

    @StepScope
    @Bean
    public ItemWriter<String> plainTextWriter() {  // 각 Chunck 별로 어떤 작업을 할 지 설정한다. 
        return items -> {   
            items.forEach(System.out::println);    
            System.out.println("==== chunk is finished ====");
        };
    }

}
```