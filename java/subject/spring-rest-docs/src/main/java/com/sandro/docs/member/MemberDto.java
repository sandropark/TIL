package com.sandro.docs.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public interface MemberDto {

    @Getter
    @AllArgsConstructor
    class UpdateReq {
        @NotEmpty
        private String name;
    }

    @Getter
    class Req extends UpdateReq {
        @Email
        private String email;

        public Req(String name, String email) {
            super(name);
            this.email = email;
        }

        public Member toEntity() {
            return new Member(email, getName());
        }
    }

    @Getter
    class Res extends Req {
        private Long id;

        public Res(Long id, String email, String name) {
            super(name, email);
            this.id = id;
        }

        public static Res from(Member member) {
            return new Res(member.getId(), member.getEmail(), member.getName());
        }

    }

}
