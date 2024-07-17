package com.sandro.docs;

import com.sandro.docs.member.Member;
import com.sandro.docs.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class DataSetup implements ApplicationRunner {

    private final MemberRepository memberRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        final List<Member> members = new ArrayList<>();

        members.add(new Member("abc@abc.com", "kim"));
        members.add(new Member("123@abc.com", "park"));
        members.add(new Member("321@abc.com", "choi"));
        members.add(new Member("243@abc.com", "noh"));

        memberRepository.saveAll(members);
    }

}
