package kr.co.unitalent.web.dto.admin;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class TalentSellStatusUpdateDto {

    private String status;

    @Size(min = 0, max = 255)
    private String statusMessage;

    @Builder
    public TalentSellStatusUpdateDto(String status, String statusMessage) {
        this.status = status;
        this.statusMessage = statusMessage;
    }

}
