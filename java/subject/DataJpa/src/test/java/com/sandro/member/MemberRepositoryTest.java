package com.sandro.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest()
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    void test() throws Exception {
//        Member member = new Member();
//
//        memberRepository.saveAndFlush(member);
//        em.clear();
//
//        List<Member> members = memberRepository.myFindAll();
//        assertThat(members).hasSize(1);
    }

}