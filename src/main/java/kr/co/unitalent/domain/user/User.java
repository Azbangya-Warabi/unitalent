package kr.co.unitalent.domain.user;

import kr.co.unitalent.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

import static kr.co.unitalent.domain.user.Role.USER;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNumber;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column
    private String thumbnail;

    @Column(length = 8, unique = true)
    private String nickname;

    @Column(unique = true)
    private String cellphone;

    @Column
    private String locationCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private String universeName;

    private String universeImage;

    private String major;

    private String registrationDate;

    private String registrationGrade;

    @Column(length = 200)
    private String selfIntroduction;

    @Builder
    public User(String name, String email, String thumbnail) {
        this.name = name;
        this.email = email;
        this.thumbnail = thumbnail;
    }

    @PrePersist
    public void prePersist() {
        Role userRole = USER;
        this.role = this.role == null ? userRole : this.role;
    }
}
