package kr.co.unitalent.web.dto.admin;

import kr.co.unitalent.domain.posts.TalentSellModifiedHistory;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TalentSellModifiedHistoryResponseDto {

    private Long boardNumber;
    private String title;
    private String categoryCode;
    private String serviceInformation;
    private String images;
    private Long price;
    private String statusMessage;
    private LocalDateTime createDate;

    public TalentSellModifiedHistoryResponseDto(TalentSellModifiedHistory entity) {
        this.boardNumber = entity.getBoardNumber();
        this.title = entity.getTitle();
        this.categoryCode = entity.getCategoryCode();
        this.serviceInformation = entity.getServiceInformation();
        this.images = entity.getImages();
        this.price = entity.getPrice();
        this.statusMessage = entity.getStatusMessage();
        this.createDate = entity.getCreateDate();
    }
}
