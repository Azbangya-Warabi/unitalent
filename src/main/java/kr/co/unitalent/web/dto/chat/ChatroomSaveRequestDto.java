package kr.co.unitalent.web.dto.chat;
import kr.co.unitalent.domain.chat.Chatroom;
import kr.co.unitalent.domain.posts.TalentProduct;
import kr.co.unitalent.domain.user.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class ChatroomSaveRequestDto {

    @NonNull
    private Long talentProductId;

    @NonNull
    private Long sellerId;

    @NonNull
    private Long buyerId;

    @Builder
    public ChatroomSaveRequestDto(Long talentProductId, Long sellerId, Long buyerId) {
        this.talentProductId = talentProductId;
        this.sellerId = sellerId;
        this.buyerId = buyerId;
    }

    public Chatroom toEntity() {
        return Chatroom.builder()
                .talentProduct(TalentProduct.builder().id(talentProductId).build())
                .seller(User.builder().id(sellerId).build())
                .buyer(User.builder().id(buyerId).build())
                .build();
    }
}
