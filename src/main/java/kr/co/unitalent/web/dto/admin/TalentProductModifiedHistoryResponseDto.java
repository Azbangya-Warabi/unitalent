package kr.co.unitalent.web.dto.admin;

import kr.co.unitalent.domain.posts.TalentProductModifiedHistory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class TalentProductModifiedHistoryResponseDto {

    private Long productId;
    private String title;
    private String categoryId;
    private String serviceInformation;
    private String images;
    private Long price;
    private String statusMessage;
    private LocalDateTime createDate;

    public TalentProductModifiedHistoryResponseDto(TalentProductModifiedHistory entity) {
        this.productId = entity.getTalentProduct().getId();
        this.title = entity.getTitle();
        this.categoryId = entity.getCategoryId();
        this.serviceInformation = entity.getServiceInformation();
        this.images = entity.getImages();
        this.price = entity.getPrice();
        this.statusMessage = entity.getStatusMessage();
        this.createDate = entity.getCreateDate();
    }
}
