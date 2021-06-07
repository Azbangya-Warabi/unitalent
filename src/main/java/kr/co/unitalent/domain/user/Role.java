package kr.co.unitalent.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("ROLE_USER", "일반 사용자"),
    STUDENT("ROLE_STUDENT", "재학생"),
    GRADUATE("ROLE_GRADUATE", "졸업생"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String title;
}
