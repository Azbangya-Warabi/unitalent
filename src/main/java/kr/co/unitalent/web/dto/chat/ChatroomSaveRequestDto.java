package kr.co.unitalent.web.dto.chat;
import kr.co.unitalent.domain.chat.Chatroom;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class ChatroomSaveRequestDto {
    @NonNull
    private String type;

    @NonNull
    private Long boardNumber;

    @NonNull
    private String seller;

    @NonNull
    private String buyer;

    @Builder
    public ChatroomSaveRequestDto(String type, Long boardNumber, String seller, String buyer) {
        this.type = type;
        this.boardNumber = boardNumber;
        this.seller = seller;
        this.buyer = buyer;
    }

    public Chatroom toEntity() {
        return Chatroom.builder()
                .type(type)
                .boardNumber(boardNumber)
                .seller(seller)
                .buyer(buyer)
                .build();
    }
}
