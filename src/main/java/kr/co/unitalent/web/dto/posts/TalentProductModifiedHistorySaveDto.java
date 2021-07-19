package kr.co.unitalent.web.dto.posts;

import kr.co.unitalent.domain.posts.TalentProduct;
import kr.co.unitalent.domain.posts.TalentProductModifiedHistory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class TalentProductModifiedHistorySaveDto {

    private TalentProduct talentProduct;
    private String title;
    private String categoryId;
    private String serviceInformation;
    private String images;
    private Long price;
    private String statusMessage;

    public TalentProductModifiedHistorySaveDto(TalentProduct talentProduct) {
        this.talentProduct = talentProduct;
        this.title = talentProduct.getTitle();
        this.categoryId = talentProduct.getCategory().getId();
        this.serviceInformation = talentProduct.getServiceInformation();
        this.images = talentProduct.getImages();
        this.price = talentProduct.getPrice();
        this.statusMessage = talentProduct.getStatusMessage();
    }

    public TalentProductModifiedHistory toEntity() {
        return TalentProductModifiedHistory.builder()
                .talentProduct(talentProduct)
                .title(title)
                .categoryId(categoryId)
                .serviceInformation(serviceInformation)
                .images(images)
                .price(price)
                .statusMessage(statusMessage)
                .build();
    }

}
