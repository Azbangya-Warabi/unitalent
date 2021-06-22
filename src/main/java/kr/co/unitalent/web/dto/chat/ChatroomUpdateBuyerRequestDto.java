package kr.co.unitalent.web.dto.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class ChatroomUpdateBuyerRequestDto {
    private Long buyerUncheckedMessageCount;
    private String lastMessage;
}
