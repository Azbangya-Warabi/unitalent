package kr.co.unitalent.web.dto.posts;

import kr.co.unitalent.domain.posts.TalentProduct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class TalentProductMypageResponseDto {
    private Long productId;
    private String title;
    private String images;
    private Long price;
    private String type;
    private String status;
    private String statusMessage;

    public TalentProductMypageResponseDto(TalentProduct entity) {
        this.productId = entity.getId();
        this.title = entity.getTitle();
        this.images = entity.getImages();
        this.price = entity.getPrice();
        this.type = entity.getType();
        this.status = entity.getStatus();
        this.statusMessage = entity.getStatusMessage();
    }
}
