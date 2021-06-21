package kr.co.unitalent.web.dto;

import kr.co.unitalent.domain.posts.TalentSell;
import lombok.Getter;

@Getter
public class TalentSellMypageResponseDto {
    private Long boardNumber;
    private String title;
    private String images;
    private Long price;
    private String type;
    private String status;
    private String statusMessage;

    public TalentSellMypageResponseDto(TalentSell entity) {
        this.boardNumber = entity.getBoardNumber();
        this.title = entity.getTitle();
        this.images = entity.getImages();
        this.price = entity.getPrice();
        this.type = entity.getType();
        this.status = entity.getStatus();
        this.statusMessage = entity.getStatusMessage();
    }
}
