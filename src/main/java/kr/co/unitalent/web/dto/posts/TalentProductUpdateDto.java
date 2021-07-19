package kr.co.unitalent.web.dto.posts;

import kr.co.unitalent.domain.global.Category;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@ToString
@NoArgsConstructor
public class TalentProductUpdateDto {
    @Size(min = 1, max = 30)
    @NonNull
    private String title;

    @NonNull
    private String  categoryId;

    @Size(min = 1, max = 3000)
    @NonNull
    private String serviceInformation;

    @Size(min = 1, max = 3000)
    private String images;

    @Min(0)
    @NonNull
    private Long price;

    @Builder
    public TalentProductUpdateDto(String title, String categoryId, String serviceInformation, String images, Long price) {
        this.title = title;
        this.categoryId = categoryId;
        this.serviceInformation = serviceInformation;
        this.images = images;
        this.price = price;
    }

}
