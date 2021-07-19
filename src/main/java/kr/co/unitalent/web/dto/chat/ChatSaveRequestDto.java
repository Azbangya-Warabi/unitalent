package kr.co.unitalent.web.dto.chat;

import kr.co.unitalent.domain.chat.Chat;
import kr.co.unitalent.domain.chat.Chatroom;
import kr.co.unitalent.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Size;

@Getter
@ToString
@NoArgsConstructor
public class ChatSaveRequestDto {
    private Long chatroomId;

    private Long requestUser;

    @Size(max = 300)
    private String message;

    private String image;

    @Builder
    public ChatSaveRequestDto(Long chatroomId, Long requestUser, String message, String image) {
        this.chatroomId = chatroomId;
        this.requestUser = requestUser;
        this.message = message;
        this.image = image;
    }

    public Chat toEntity() {
        return Chat.builder()
                .chatroom(Chatroom.builder().id(chatroomId).build())
                .requestUser(User.builder().id(requestUser).build())
                .message(message)
                .image(image)
                .build();
    }
}
