package kr.co.unitalent.web.dto.chat;

import kr.co.unitalent.domain.chat.Chat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class ChatResponseDto {

    private String requestUser;

    private String message;

    private String image;

    private boolean checked;

    private LocalDateTime createDate;

    public ChatResponseDto(Chat entity) {
        this.requestUser = entity.getRequestUser();
        this.message = entity.getMessage();
        this.image = entity.getImage();
        this.checked = entity.getChecked();
        this.createDate = entity.getCreateDate();
    }
}
