package kr.co.unitalent.web.dto.posts;

import com.fasterxml.jackson.annotation.JsonValue;
import kr.co.unitalent.domain.global.Category;
import kr.co.unitalent.domain.posts.TalentProduct;
import kr.co.unitalent.domain.posts.TalentProductImage;
import kr.co.unitalent.domain.user.User;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TalentProductSaveRequestDto {

    private Long userId;

    @Size(min = 1, max = 30)
    @NotBlank
    private String title;

    @NotBlank
    private String categoryId;

    @Size(min = 1, max = 3000)
    @NotBlank
    private String serviceInformation;

    @Min(0)
    @NotNull
    private Long price;

    @NotBlank
    private String type;

    private List<MultipartFile> images;

    private List<String> imageInformation;


    @Builder
    public TalentProductSaveRequestDto(Long userId, String title, String categoryId, String serviceInformation, Long price, String type) {
        this.userId = userId;
        this.title = title;
        this.categoryId = categoryId;
        this.serviceInformation = serviceInformation;
        this.price = price;
        this.type = type;
    }

    public TalentProduct toEntity() {
        return TalentProduct.builder()
                .user(User.builder().id(userId).build())
                .title(title)
                .category(Category.builder().id(categoryId).build())
                .serviceInformation(serviceInformation)
                .price(price)
                .type(type)
                .build();
    }

    public TalentProductImage toSavedImageEntity(TalentProduct talentProduct, int fileIndex, ImageDto imageInfo) {
        return TalentProductImage.builder()
                .talentProduct(talentProduct)
                .imageUrl(images.get(fileIndex).getName())
                .dataOrder(Long.parseLong(imageInfo.getOrder()))
                .build();
    }

    public TalentProductImage toUnSavedImageEntity(TalentProduct talentProduct, int fileIndex, ImageDto imageInfo, String url) {
        return TalentProductImage.builder()
                .talentProduct(talentProduct)
                .imageUrl(url)
                .dataOrder(Long.parseLong(imageInfo.getOrder()))
                .build();
    }

}
