package kr.co.unitalent.web.dto.chat;

import kr.co.unitalent.domain.chat.Chat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Size;

@Getter
@ToString
@NoArgsConstructor
public class ChatSaveRequestDto {
    private Long roomNumber;

    private String requestUser;

    @Size(max = 300)
    private String message;

    private String image;

    @Builder
    public ChatSaveRequestDto(Long roomNumber, String requestUser, String message, String image) {
        this.roomNumber = roomNumber;
        this.requestUser = requestUser;
        this.message = message;
        this.image = image;
    }

    public Chat toEntity() {
        return Chat.builder()
                .roomNumber(roomNumber)
                .requestUser(requestUser)
                .message(message)
                .image(image)
                .build();
    }
}
