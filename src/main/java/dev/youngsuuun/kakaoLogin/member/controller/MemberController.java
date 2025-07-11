package dev.youngsuuun.kakaoLogin.member.controller;

import dev.youngsuuun.kakaoLogin.apiPayload.ApiResponse;
import dev.youngsuuun.kakaoLogin.apiPayload.code.status.SuccessStatus;
import dev.youngsuuun.kakaoLogin.member.converter.MemberConverter;
import dev.youngsuuun.kakaoLogin.member.domain.Member;
import dev.youngsuuun.kakaoLogin.member.dto.req.MemberRequestDto;
import dev.youngsuuun.kakaoLogin.member.dto.res.MemberResponseDto;
import dev.youngsuuun.kakaoLogin.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members") //공통 경로
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<MemberResponseDto.JoinResultDTO> join(@RequestBody @Valid MemberRequestDto.JoinDto request){
        Member savedMember = memberService.joinMember(request);
        return ApiResponse.of(MemberConverter.toJoinResultDTO(savedMember), SuccessStatus._CREATED);
    }
}
