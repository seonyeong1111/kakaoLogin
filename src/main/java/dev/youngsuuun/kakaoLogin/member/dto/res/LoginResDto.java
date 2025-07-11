package dev.youngsuuun.kakaoLogin.member.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Data
@AllArgsConstructor
@Builder
public class LoginResDto {

        private Long id;
        private String name;
        private String accessToken;

}
