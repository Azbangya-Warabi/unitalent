package kr.co.unitalent.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.co.unitalent.domain.BaseTimeEntity;
import kr.co.unitalent.domain.chat.Chat;
import kr.co.unitalent.domain.chat.Chatroom;
import kr.co.unitalent.domain.global.Location;
import kr.co.unitalent.domain.posts.TalentProduct;
import kr.co.unitalent.domain.posts.TalentProductReview;
import kr.co.unitalent.domain.user.carrer.UserCareer;
import kr.co.unitalent.domain.user.carrer.UserUniverseData;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static kr.co.unitalent.domain.user.Role.USER;

@ToString(callSuper = true)
@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToOne
    private Location location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToOne
    private UserUniverseData userUniverseData;

    @Column(length = 500)
    private String selfIntroduction;

    @ToString.Exclude
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<TalentProduct> talentProduct = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<UserCareer> userCareers = new ArrayList<>();

    @ToString.Exclude
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<TalentProductReview> talentProductReviews = new ArrayList<>();

    @OneToMany(mappedBy = "seller")
    private List<Chatroom> sellers = new ArrayList<>();

    @OneToMany(mappedBy = "buyer")
    private List<Chatroom> buyers = new ArrayList<>();

    @OneToMany(mappedBy = "requestUser")
    private List<Chat> chats = new ArrayList<>();

    @Builder
    public User(Long id, String name, String email, String thumbnail, String nickname) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.thumbnail = thumbnail;
        this.nickname = nickname;
    }

    @PrePersist
    public void prePersist() {
        this.role = this.role == null ? USER : this.role;
    }
}
