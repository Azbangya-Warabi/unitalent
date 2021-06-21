package kr.co.unitalent.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class TalentSellUpdateDto {
    @Size(min = 1, max = 30)
    @NonNull
    private String title;

    @NonNull
    private String categoryCode;

    @Size(min = 1, max = 3000)
    @NonNull
    private String serviceInformation;

    @Size(min = 1, max = 3000)
    private String images;

    @Min(0)
    @NonNull
    private Long price;

    @Builder
    public TalentSellUpdateDto(String title, String categoryCode, String serviceInformation, String images, Long price) {
        this.title = title;
        this.categoryCode = categoryCode;
        this.serviceInformation = serviceInformation;
        this.images = images;
        this.price = price;
    }

}
