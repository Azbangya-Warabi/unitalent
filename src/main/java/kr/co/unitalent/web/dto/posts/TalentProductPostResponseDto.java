package kr.co.unitalent.web.dto.posts;

import kr.co.unitalent.domain.posts.TalentProduct;
import kr.co.unitalent.util.converter.CategoryConverter;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class TalentProductPostResponseDto {

    private Long productId;
    private String title;
    private String categoryId;
    private String serviceInformation;
    private Long price;

    @Builder
    public TalentProductPostResponseDto(TalentProduct entity) {
        this.productId = entity.getId();
        this.title = entity.getTitle();
        this.categoryId = new CategoryConverter(entity.getCategory().getId()).toInverseConverter();
        this.serviceInformation = entity.getServiceInformation();
        this.price = entity.getPrice();
    }
}
