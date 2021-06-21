package kr.co.unitalent.web.dto;

import kr.co.unitalent.domain.posts.TalentSell;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@ToString
@NoArgsConstructor
public class TalentSellSaveRequestDto {
    @NonNull
    private String nickname;

    @Size(min = 1, max = 30)
    @NonNull
    private String title;

    @NonNull
    private String categoryCode;

    @Size(min = 1, max = 3000)
    @NonNull
    private String serviceInformation;

    @Size(min = 0, max = 4000)
    private String images;

    @Min(0)
    @NonNull
    private Long price;


    private String type;

    @Builder
    public TalentSellSaveRequestDto(String nickname, String title, String categoryCode, String serviceInformation, String images, Long price, String type) {
        this.nickname = nickname;
        this.title = title;
        this.categoryCode = categoryCode;
        this.serviceInformation = serviceInformation;
        this.images = images;
        this.price = price;
        this.type = type;
    }

    public TalentSell toEntity() {
        return TalentSell.builder()
                .nickname(nickname)
                .title(title)
                .categoryCode(categoryCode)
                .serviceInformation(serviceInformation)
                .images(images)
                .price(price)
                .type(type)
                .build();
    }
}
