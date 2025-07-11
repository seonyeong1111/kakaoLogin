package dev.youngsuuun.kakaoLogin.common.config.security;

import lombok.RequiredArgsConstructor;
import dev.youngsuuun.kakaoLogin.apiPayload.code.status.ErrorStatus;
import dev.youngsuuun.kakaoLogin.apiPayload.exception.GeneralException;
import dev.youngsuuun.kakaoLogin.member.domain.Member;
import dev.youngsuuun.kakaoLogin.member.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new GeneralException(ErrorStatus._MEMBER_NOT_FOUND));

        return new CustomUserDetails(member);
    }
}