package kr.co.unitalent.web.dto.posts;

import kr.co.unitalent.domain.posts.TalentSell;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class TalentSellResponseDto {
    private Long boardNumber;
    private String nickname;
    private String title;
    private String categoryCode;
    private String serviceInformation;
    private String images;
    private Long price;
    private String type;

    public TalentSellResponseDto(TalentSell entity) {
        this.boardNumber = entity.getBoardNumber();
        this.nickname = entity.getNickname();
        this.title = entity.getTitle();
        this.categoryCode = entity.getCategoryCode();
        this.serviceInformation = entity.getServiceInformation();
        this.images = entity.getImages();
        this.price = entity.getPrice();
        this.type = entity.getType();
    }
}
