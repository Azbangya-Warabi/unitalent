package kr.co.unitalent.web.dto.posts;

import kr.co.unitalent.domain.posts.TalentProduct;
import kr.co.unitalent.domain.posts.TalentProductReviewTotal;
import kr.co.unitalent.domain.user.carrer.UserCareer;
import kr.co.unitalent.util.separator.ImageSeparator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class TalentProductPreviewResponseDto {
    private Long productId;
    private String title;
    private Long price;

    private Long reviewCount;
    private float averageStarScore;

    public TalentProductPreviewResponseDto(TalentProduct entity) {
        this.productId = entity.getId();
        this.title = entity.getTitle();
        this.price = entity.getPrice();
        this.reviewCount = entity.getTalentProductReviewTotal().getReviewCount();
        this.averageStarScore = entity.getTalentProductReviewTotal().getAverageStarScore();
    }
}
