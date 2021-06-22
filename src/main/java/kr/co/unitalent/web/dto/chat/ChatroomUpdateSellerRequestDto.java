package kr.co.unitalent.web.dto.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class ChatroomUpdateSellerRequestDto {
    private Long sellerUncheckedMessageCount;
    private String lastMessage;
}
