package com.sandro.springbatch.job;

import com.sandro.springbatch.BatchTestConfig;
import com.sandro.springbatch.core.domain.PlainText;
import com.sandro.springbatch.core.domain.ResultText;
import com.sandro.springbatch.core.repository.PlainTextRepository;
import com.sandro.springbatch.core.repository.ResultTextRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBatchTest
@SpringBootTest(classes = {PlainTextJobConfig.class, BatchTestConfig.class})
class PlainTextJobConfigTest {

    @Autowired JobLauncherTestUtils jobLauncherTestUtils;
    @Autowired PlainTextRepository plainTextRepository;
    @Autowired ResultTextRepository resultTextRepository;
    @Autowired EntityManager em;

    @AfterEach
    void tearDown() {
        resultTextRepository.deleteAll();
    }

    @Test
    void test() throws Exception {
        // Given
        int count = 12;
        init(count);

        // When
        JobExecution execution = jobLauncherTestUtils.launchJob();

        // Then
        assertThat(execution.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);

        List<ResultText> results = resultTextRepository.findAll();
        assertThat(results).hasSize(count);
        assertThat(results.get(0).getText()).isEqualTo("processed text0");
    }

    private void init(int count) {
        IntStream.range(0, count).forEach(num -> plainTextRepository.save(new PlainText("text" + num)));
    }
}