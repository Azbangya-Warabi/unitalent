package kr.co.unitalent.web.dto.admin;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Size;

@Getter
@ToString
@NoArgsConstructor
public class TalentProductStatusUpdateDto {

    private String status;

    @Size(min = 0, max = 255)
    private String statusMessage;

    @Builder
    public TalentProductStatusUpdateDto(String status, String statusMessage) {
        this.status = status;
        this.statusMessage = statusMessage;
    }

}
