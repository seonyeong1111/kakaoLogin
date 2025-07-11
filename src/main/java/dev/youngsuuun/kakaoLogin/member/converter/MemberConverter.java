package dev.youngsuuun.kakaoLogin.member.converter;

import dev.youngsuuun.kakaoLogin.member.domain.Member;
import dev.youngsuuun.kakaoLogin.member.dto.req.MemberRequestDto;
import dev.youngsuuun.kakaoLogin.member.dto.res.LoginResDto;
import dev.youngsuuun.kakaoLogin.member.dto.res.MemberResponseDto;
import dev.youngsuuun.kakaoLogin.member.domain.enums.Gender;

import java.time.LocalDateTime;

public class MemberConverter {

    public static MemberResponseDto.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDto.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .point(member.getPoint())
                .build();
    }

    public static Member toMember(MemberRequestDto.JoinDto request){

        Gender gender = null;

        switch (request.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        return Member.builder()
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .name(request.getName())
                .age(request.getAge())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .build();
    }

    public static LoginResDto signInRes(Member member, String accessToken) {
        return LoginResDto.builder()
                .id(member.getId())
                .name(member.getName())
                .accessToken(accessToken)
                .build();
    }
}
