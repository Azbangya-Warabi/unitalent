package kr.co.unitalent.domain.posts;

import kr.co.unitalent.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class TalentProductReviewTotal extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private TalentProduct talentProduct;

    @ColumnDefault("0")
    private Long reviewCount;

    @ColumnDefault("0")
    private Float averageStarScore;

    @Builder
    public TalentProductReviewTotal(TalentProduct talentProduct) {
        this.talentProduct = talentProduct;
    }

    @PrePersist
    public void prePersist() {
        this.reviewCount = this.reviewCount == null ? 0 : this.reviewCount;
        this.averageStarScore = this.averageStarScore == null ? 0 : this.averageStarScore;
    }
}
