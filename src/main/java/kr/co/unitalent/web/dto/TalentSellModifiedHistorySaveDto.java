package kr.co.unitalent.web.dto;

import kr.co.unitalent.domain.posts.TalentSell;
import kr.co.unitalent.domain.posts.TalentSellModifiedHistory;
import lombok.Builder;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class TalentSellModifiedHistorySaveDto {

    private Long boardNumber;
    private String title;
    private String categoryCode;
    private String serviceInformation;
    private String images;
    private Long price;
    private String statusMessage;

    public TalentSellModifiedHistorySaveDto(TalentSell talentSell) {
        this.boardNumber = talentSell.getBoardNumber();
        this.title = talentSell.getTitle();
        this.categoryCode = talentSell.getCategoryCode();
        this.serviceInformation = talentSell.getServiceInformation();
        this.images = talentSell.getImages();
        this.price = talentSell.getPrice();
        this.statusMessage = talentSell.getStatusMessage();
    }

    public TalentSellModifiedHistory toEntity() {
        return TalentSellModifiedHistory.builder()
                .boardNumber(boardNumber)
                .title(title)
                .categoryCode(categoryCode)
                .serviceInformation(serviceInformation)
                .images(images)
                .price(price)
                .statusMessage(statusMessage)
                .build();
    }

}
