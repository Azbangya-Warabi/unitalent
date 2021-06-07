package kr.co.unitalent.domain.user;

import kr.co.unitalent.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_number")
    private Long userNumber;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column
    private String thumnail;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Column(nullable = false)
    private String cellphone;

    @Column
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(name = "universe_name")
    private String universeName;

    @Column(name = "universe_image")
    private String universeImage;

    @Column
    private String major;

    @Column(name = "registration_name")
    private String registrationDate;

    @Column(name = "registration_grade")
    private String registrationGrade;

    @Column(name = "self_introduction", length = 200)
    private String selfIntroduction;

    @Builder
    public User(String name, String email, String thumnail, Role role) {
        this.name = name;
        this.email = email;
        this.thumnail = thumnail;
        this.role = role;
    }
}
