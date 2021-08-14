package kr.co.unitalent.web.dto.posts;

import kr.co.unitalent.domain.posts.TalentProductImage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class TalentProductImageResponseDto {

    private Long talentProductId;
    private String imageUrl;
    private Long dataOrder;

    @Builder
    public TalentProductImageResponseDto(TalentProductImage talentProductImage) {
        this.talentProductId = talentProductImage.getTalentProduct().getId();
        this.imageUrl = talentProductImage.getImageUrl();
        this.dataOrder = talentProductImage.getDataOrder();
    }
}
