package kr.co.unitalent.web.dto.posts;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ImageDto {
    private String firstImage;
    private String secondImage;
    private String thirdImage;
    private String fourthImage;
    private String fifthImage;

    public ImageDto(List<String> images) {
        this.firstImage = images.get(0);
        this.secondImage = images.get(1);
        this.thirdImage = images.get(2);
        this.fourthImage = images.get(3);
        this.fifthImage = images.get(4);
    }
}
