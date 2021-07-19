package kr.co.unitalent.web.dto.chat;

import kr.co.unitalent.domain.chat.Chat;
import kr.co.unitalent.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class ChatResponseDto {

    private Long userId;
    private String nickname;

    private String message;

    private String image;

    private boolean checked;

    private LocalDateTime createDate;

    public ChatResponseDto(Chat entity) {
        this.userId = entity.getRequestUser().getId();
        this.nickname = entity.getRequestUser().getNickname();
        this.message = entity.getMessage();
        this.image = entity.getImage();
        this.checked = entity.getChecked();
        this.createDate = entity.getCreateDate();
    }
}
