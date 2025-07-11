package dev.youngsuuun.kakaoLogin.member.service;

import dev.youngsuuun.kakaoLogin.common.config.jwt.TokenProvider;
import dev.youngsuuun.kakaoLogin.member.domain.enums.SocialType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import dev.youngsuuun.kakaoLogin.member.converter.MemberConverter;
import dev.youngsuuun.kakaoLogin.member.domain.Member;
import dev.youngsuuun.kakaoLogin.member.domain.enums.Role;
import dev.youngsuuun.kakaoLogin.member.dto.req.MemberRequestDto;
import dev.youngsuuun.kakaoLogin.member.dto.res.KakaoUserInfoResponseDto;
import dev.youngsuuun.kakaoLogin.member.dto.res.LoginResDto;
import dev.youngsuuun.kakaoLogin.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public Member joinMember(MemberRequestDto.JoinDto request) {

        Member newMember = MemberConverter.toMember(request); //컨버터 위치

        newMember.encodePassword(passwordEncoder.encode(request.getPassword()));

       return memberRepository.save(newMember);
    }

    public boolean existsById(Long memberId) {
        return memberRepository.existsById(memberId);
    }

    // 카카오 로그인 시 신규 회원가입 또는 기존 회원 조회
    public Member kakaoSignup(KakaoUserInfoResponseDto userInfo) {
        return memberRepository.findByName(userInfo.getKakaoAccount().getProfile().getNickname())
                .orElseGet(() -> {
                    Member newMem = Member.builder()
                            .email(userInfo.getKakaoAccount().getEmail())
                            .name(userInfo.getKakaoAccount().getProfile().getNickname())
                            .role(Role.USER)
                            .socialType(SocialType.KAKAO)
                            .build();
                    memberRepository.save(newMem);
                    return newMem;
                });
    }

    // 카카오 로그인 처리 후 토큰 발급
    public LoginResDto kakaoLogin(HttpServletRequest request, HttpServletResponse response, Member member) {
        String accessToken = tokenProvider.createAccessToken(member);


        return MemberConverter.signInRes(member, accessToken);
    }


}
