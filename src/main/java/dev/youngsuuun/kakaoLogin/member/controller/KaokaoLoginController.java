package dev.youngsuuun.kakaoLogin.member.controller;

import dev.youngsuuun.kakaoLogin.member.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import dev.youngsuuun.kakaoLogin.apiPayload.ApiResponse;
import dev.youngsuuun.kakaoLogin.member.dto.res.KakaoUserInfoResponseDto;
import dev.youngsuuun.kakaoLogin.member.dto.res.LoginResDto;
import dev.youngsuuun.kakaoLogin.member.service.KakaoService;
import dev.youngsuuun.kakaoLogin.member.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class KaokaoLoginController {

    private final KakaoService kakaoService;
    private final MemberService memberService;

    @GetMapping("/callback")
    public ApiResponse<LoginResDto> callback(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accessToken = kakaoService.getAccessTokenFromKakao(code);

        KakaoUserInfoResponseDto userInfo = kakaoService.getUserInfo(accessToken);

        //회원가입, 로그인 동시진행
        return ApiResponse.onSuccess(memberService.kakaoLogin(request,response, memberService.kakaoSignup(userInfo)));
    }

}

