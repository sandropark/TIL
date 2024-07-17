package com.sandro.docs.member;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberApi {

    private final MemberRepository memberRepository;

    /**
     * 1. Member 단일 조회
     * 2. Member 생성
     * 3. Member 수정
     * 4. Member 페이징 조회
     */

    @PostMapping
    public Long createMember(@RequestBody @Valid MemberDto.Req memberSaveReq) {
        Member savedMember = memberRepository.save(memberSaveReq.toEntity());
        return savedMember.getId();
    }

    @GetMapping("/{id}")
    public MemberDto.Res getMember(@PathVariable Long id) {
        Member member = memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return MemberDto.Res.from(member);
    }

    @PutMapping("/{id}")
    public void updateMember(@PathVariable Long id, @RequestBody @Valid MemberDto.UpdateReq memberUpdateReq) {
        Member member = memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        member.updateName(memberUpdateReq.getName());
    }

    @GetMapping
    public Page<MemberDto.Res> getMembers(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return memberRepository.findAll(pageable).map(MemberDto.Res::from);
    }
}
