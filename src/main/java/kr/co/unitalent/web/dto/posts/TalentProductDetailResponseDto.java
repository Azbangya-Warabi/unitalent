package kr.co.unitalent.web.dto.posts;

import kr.co.unitalent.domain.posts.TalentProduct;
import kr.co.unitalent.domain.user.carrer.UserCareer;
import kr.co.unitalent.util.converter.CategoryConverter;
import kr.co.unitalent.util.converter.LocationConverter;
import kr.co.unitalent.util.separator.ImageSeparator;
import kr.co.unitalent.web.dto.user.UserCareerCertificationResponseDto;
import kr.co.unitalent.web.dto.user.UserCareerNonCertificationResponseDto;
import kr.co.unitalent.web.dto.user.UserCareerResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class TalentProductDetailResponseDto {
    private Long productId;
    private String title;
    private String categoryId;
    private String serviceInformation;
    private ImageDto images;
    private Long price;
    private String type;

    private Long reviewCount;
    private float averageStarScore;

    private Long userId;
    private String thumbnail;
    private String nickname;
    private String locationId;
    private List<UserCareerResponseDto> certificationCareer = new ArrayList<>();
    private List<UserCareerResponseDto> nonCertificationCareer = new ArrayList<>();

    public TalentProductDetailResponseDto(TalentProduct entity, List<UserCareer> userCareers) {
        this.productId = entity.getId();
        this.title = entity.getTitle();
        this.categoryId = CategoryConverter.builder()
                .code(entity.getCategory().getId())
                .build()
                .toConvert();
        this.serviceInformation = entity.getServiceInformation();
        this.images = new ImageDto(ImageSeparator.builder()
                .images(entity.getImages())
                .build()
                .toList());
        this.price = entity.getPrice();
        this.type = entity.getType();

        this.reviewCount = entity.getTalentProductReviewTotal().getReviewCount();
        this.averageStarScore = entity.getTalentProductReviewTotal().getAverageStarScore();

        this.userId = entity.getUser().getId();
        this.thumbnail = entity.getUser().getThumbnail();
        this.nickname = entity.getUser().getNickname();
        this.locationId = LocationConverter.builder()
                .code(entity.getUser().getLocation().getId())
                .build()
                .toConvert();
        addUserCareers(userCareers);
    }

    public void addUserCareers(List<UserCareer> userCareer) {
        userCareer.forEach(entity -> {
            if(entity.getCertification().equals("인증")) {
                this.certificationCareer.add(UserCareerCertificationResponseDto.builder().userCareer(entity).build());
            } else {
                this.nonCertificationCareer.add(UserCareerNonCertificationResponseDto.builder().userCareer(entity).build());
            }
        });
    }
}
