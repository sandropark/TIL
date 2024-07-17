package com.sandro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.lang.Thread.sleep;

@SpringBootTest
class TransactionTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    void test() throws Exception {
        memberRepository.save(Member.builder().name("m1").build());
        memberRepository.save(Member.builder().name("m2").build());

        for (int i = 0; i < 2; i++)
            CompletableFuture.runAsync(() -> memberService.findById(1L));
        sleep(10000);
    }
}
