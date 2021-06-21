package kr.co.unitalent.web.dto.admin;

import kr.co.unitalent.domain.posts.TalentSell;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TalentSellStatusResponseDto {
    private Long boardNumber;
    private String nickname;
    private String title;
    private String categoryCode;
    private Long price;
    private String type;
    private String status;
    private String statusMessage;
    private String beforeStatusMessage;
    private LocalDateTime modifiedDate;

    public TalentSellStatusResponseDto(TalentSell entity) {
        this.boardNumber = entity.getBoardNumber();
        this.nickname = entity.getNickname();
        this.title = entity.getTitle();
        this.categoryCode = entity.getCategoryCode();
        this.price = entity.getPrice();
        this.type = entity.getType();
        this.status = entity.getStatus();
        this.statusMessage = entity.getStatusMessage();
        this.beforeStatusMessage = entity.getBeforeStatusMessage();
        this.modifiedDate = entity.getModifiedDate();
    }
}
