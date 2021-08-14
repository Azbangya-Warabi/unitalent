package kr.co.unitalent.web.dto.chat;

import kr.co.unitalent.domain.posts.TalentProduct;
import kr.co.unitalent.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class ChatroomTalentProductResponseDto {
    private Long id;
    private String title;
    private Long price;

    private String thumbnail;
    private String nickname;

    @Builder
    public ChatroomTalentProductResponseDto(TalentProduct talentProduct, User user) {
        this.id = talentProduct.getId();
        this.title = talentProduct.getTitle();
        this.price = talentProduct.getPrice();

        this.thumbnail = user.getThumbnail();
        this.nickname = user.getNickname();
    }
}
