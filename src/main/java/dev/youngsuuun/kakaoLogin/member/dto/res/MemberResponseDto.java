package dev.youngsuuun.kakaoLogin.member.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MemberResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDTO{

        private Long memberId;
        private LocalDateTime createdAt;
        private Integer point;
    }
}
