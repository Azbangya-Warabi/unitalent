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
    private Long roomNumber;
    private String type;
    private Long boardNumber;
    private String seller;
    private String buyer;
    private Long sellerUncheckedMessageCount;
    private LocalDateTime sellerLastMessageDate;
    private Long buyerUncheckedMessageCount;
    private LocalDateTime buyerLastMessageDate;
    private String lastMessage;

    public ChatroomResponseDto(Chatroom entity) {
        this.roomNumber = entity.getRoomNumber();
        this.type = entity.getType();
        this.boardNumber = entity.getBoardNumber();
        this.seller = entity.getSeller();
        this.buyer = entity.getBuyer();
        this.sellerUncheckedMessageCount = entity.getSellerUncheckedMessageCount();
        this.sellerLastMessageDate = entity.getSellerLastMessageDate();
        this.buyerUncheckedMessageCount = entity.getBuyerUncheckedMessageCount();
        this.buyerLastMessageDate = entity.getBuyerLastMessageDate();
        this.lastMessage = entity.getLastMessage();
    }
}
