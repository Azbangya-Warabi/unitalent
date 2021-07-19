package kr.co.unitalent.web.dto.posts;

import kr.co.unitalent.domain.global.Category;
import kr.co.unitalent.domain.posts.TalentProduct;
import kr.co.unitalent.domain.user.User;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TalentProductSaveRequestDto {
    @NonNull
    private Long userId;

    @Size(min = 1, max = 30)
    @NonNull
    private String title;

    @NonNull
    private String categoryId;

    @Size(min = 1, max = 3000)
    @NonNull
    private String serviceInformation;

    private String images;

    @Min(0)
    @NonNull
    private Long price;

    private String type;

    @Builder
    public TalentProductSaveRequestDto(Long userId, String title, String categoryId, String serviceInformation, String images, Long price, String type) {
        this.userId = userId;
        this.title = title;
        this.categoryId = categoryId;
        this.serviceInformation = serviceInformation;
        this.images = images;
        this.price = price;
        this.type = type;
    }

    public TalentProduct toEntity() {
        return TalentProduct.builder()
                .user(User.builder().id(userId).build())
                .title(title)
                .category(Category.builder().id(categoryId).build())
                .serviceInformation(serviceInformation)
                .images(images)
                .price(price)
                .type(type)
                .build();
    }
}
