package kr.co.unitalent.web.dto.chat;

import kr.co.unitalent.domain.chat.Chatroom;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class ChatroomResponseDto {
    private Long chatroomId;
    private String type;
    private String seller;
    private String buyer;
    private Long sellerUncheckedMessageCount;
    private Long buyerUncheckedMessageCount;
    private String lastMessage;
    private LocalDateTime lastMessageDate;

    public ChatroomResponseDto(Chatroom entity) {
        this.chatroomId = entity.getId();
        this.type = entity.getTalentProduct().getType();
        this.seller = entity.getSeller().getNickname();
        this.buyer = entity.getBuyer().getNickname();
        this.sellerUncheckedMessageCount = entity.getSellerUncheckedMessageCount();
        this.buyerUncheckedMessageCount = entity.getBuyerUncheckedMessageCount();
        this.lastMessage = entity.getLastMessage();
        this.lastMessageDate = entity.getModifiedDate();
    }
}
