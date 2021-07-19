package kr.co.unitalent.web.dto.admin;

import kr.co.unitalent.domain.posts.TalentProduct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class TalentProductStatusResponseDto {
    private Long productId;
    private Long userId;
    private String nickname;
    private String title;
    private String categoryId;
    private Long price;
    private String type;
    private String status;
    private String statusMessage;
    private String beforeStatusMessage;
    private LocalDateTime modifiedDate;

    public TalentProductStatusResponseDto(TalentProduct entity) {
        this.productId = entity.getId();
        this.userId = entity.getUser().getId();
        this.nickname = entity.getUser().getNickname();
        this.title = entity.getTitle();
        this.categoryId = entity.getCategory().getId();
        this.price = entity.getPrice();
        this.type = entity.getType();
        this.status = entity.getStatus();
        this.statusMessage = entity.getStatusMessage();
        this.beforeStatusMessage = entity.getBeforeStatusMessage();
        this.modifiedDate = entity.getModifiedDate();
    }
}
