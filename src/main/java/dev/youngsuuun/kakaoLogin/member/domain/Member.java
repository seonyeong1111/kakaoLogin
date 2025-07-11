package dev.youngsuuun.kakaoLogin.member.domain;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dev.youngsuuun.kakaoLogin.member.domain.enums.Gender;
import dev.youngsuuun.kakaoLogin.member.domain.enums.MemberStatus;
import dev.youngsuuun.kakaoLogin.member.domain.enums.Role;
import dev.youngsuuun.kakaoLogin.member.domain.enums.SocialType;
import dev.youngsuuun.kakaoLogin.global.BaseEntity;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Builder
@DynamicUpdate
@DynamicInsert //이 두 개는 insert와 update 시 null 인 경우는 그냥 쿼리를 보내지 않도록 해줍니다.
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String specAddress;

//    기본 값인 ORDINAL을 사용하면 데이터베이스에 enum의 순서가 저장이 되는데,
//    만약 Springboot에서 enum의 순서를 바꾸게 될 경우 에러가 생기게 됩니다.
//    따라서 반드시 STRING을 사용해주세요!
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @ColumnDefault("'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    private LocalDate inactiveDate;//비활성 상태가 될 때 LocalDate.now() 등으로 값이 설정

    private String email;

    @ColumnDefault("0")
    private Integer point;

    private String password;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'USER'")
    private Role role;

    public void encodePassword(String password) {
        this.password = password;
    }


}
