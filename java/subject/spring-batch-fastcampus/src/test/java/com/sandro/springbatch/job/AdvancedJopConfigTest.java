package com.sandro.springbatch.job;

import com.sandro.springbatch.BatchTestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.*;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ActiveProfiles("test")
@SpringBatchTest
@SpringBootTest(classes = {AdvancedJopConfig.class, BatchTestConfig.class})
class AdvancedJopConfigTest {

    @Autowired
    JobLauncherTestUtils jobLauncherTestUtils;

    @DisplayName("유효하지 않은 파라미터인 경우 예외가 발생한다.")
    @Test
    void exception() throws Exception {
        JobParameters jobParameters = new JobParameters(Map.of("targetDate", new JobParameter("20210101")));

        assertThatThrownBy(() -> jobLauncherTestUtils.launchJob(jobParameters))
                .isInstanceOf(JobParametersInvalidException.class)
                .message().contains("유효하지 않은 날짜");
    }

    @DisplayName("jobExecutionListener")
    @Test
    void jobExecutionListener() throws Exception {
        JobParameters jobParameters = new JobParameters(Map.of("targetDate", new JobParameter("2021-01-01")));

        JobExecution execution = jobLauncherTestUtils.launchJob(jobParameters);

        assertThat(execution.getExitStatus().getExitCode()).isEqualTo(ExitStatus.FAILED.getExitCode());
    }

    @DisplayName("StepExecutionListener")
    @Test
    void stepExecutionListener() throws Exception {
        JobParameters jobParameters = new JobParameters(Map.of("targetDate", new JobParameter("2021-01-02")));

        JobExecution execution = jobLauncherTestUtils.launchJob(jobParameters);
    }

}