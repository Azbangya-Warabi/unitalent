package kr.co.unitalent.web.dto.posts;

import kr.co.unitalent.domain.posts.TalentProductReview;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class TalentProductReviewResponseDto {
    private String buyerThumbnail;
    private String buyerNickname;
    private float reviewStar;
    private LocalDateTime reviewDate;
    private String reviewComment;

    @Builder
    public TalentProductReviewResponseDto(TalentProductReview entity) {
        this.buyerThumbnail = entity.getUser().getThumbnail();
        this.buyerNickname = entity.getUser().getNickname();
        this.reviewStar = entity.getStar();
        this.reviewDate = entity.getModifiedDate();
        this.reviewComment = entity.getComment();
    }
}
