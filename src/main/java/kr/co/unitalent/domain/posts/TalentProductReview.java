package kr.co.unitalent.domain.posts;

import kr.co.unitalent.domain.BaseTimeEntity;
import kr.co.unitalent.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@NoArgsConstructor
@Entity
public class TalentProductReview extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private TalentProduct talentProduct;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private Float star;

    @Column(nullable = false)
    private String comment;

    @Builder
    public TalentProductReview(User user, Float star, String comment) {
        this.user = user;
        this.star = star;
        this.comment = comment;
    }
}
