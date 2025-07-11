package dev.youngsuuun.kakaoLogin.member.dto.req;

import lombok.Getter;
import dev.youngsuuun.kakaoLogin.member.domain.enums.Role;

//클라이언트에서 입력되는 화면으로 구조 설계
public class MemberRequestDto {

    @Getter
    public static class JoinDto{
        String name;
        Integer gender;
        Integer age;
        Integer birthYear;
        Integer birthMonth;
        Integer birthDay;
        String address;
        String specAddress;
        String password;
        String email;
        Role role;

    }
}
