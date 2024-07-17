package com.sandro.springbatch.job.player;

import com.sandro.springbatch.BatchTestConfig;
import com.sandro.springbatch.core.service.PlayerSalaryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.AssertFile;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBatchTest
@SpringBootTest(classes = {FlatFileJobConfig.class, BatchTestConfig.class, PlayerSalaryService.class})
class FlatFileJobConfigTest {

    @Autowired
    JobLauncherTestUtils jobLauncherTestUtils;

    @DisplayName("파일에서 데이터를 읽고, 데이터를 가공해서 다시 파일로 저장한다.")
    @Test
    void test() throws Exception {
        JobExecution execution = jobLauncherTestUtils.launchJob();

        assertThat(execution.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);
        AssertFile.assertFileEquals(new File("player-salary-list.txt"), new File("succeed-player-salary-list.txt"));
    }
}