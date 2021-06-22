package kr.co.unitalent.web.dto.posts;

import kr.co.unitalent.domain.posts.TalentSell;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
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
