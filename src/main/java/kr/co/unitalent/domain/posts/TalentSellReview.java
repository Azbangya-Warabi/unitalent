package kr.co.unitalent.domain.posts;

import kr.co.unitalent.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@NoArgsConstructor
@Entity
public class TalentSellReview extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewNumber;

    @Column(nullable = false)
    private Long boardNumber;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private int star;

    @Column(nullable = false)
    private String comment;

    @Builder
    public TalentSellReview (Long boardNumber, String nickname, int star, String comment) {
        this.boardNumber = boardNumber;
        this.nickname = nickname;
        this.star = star;
        this.comment = comment;
    }
}
