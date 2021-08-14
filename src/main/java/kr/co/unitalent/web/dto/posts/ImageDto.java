package kr.co.unitalent.web.dto.posts;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ImageDto {
    private String isSaved;
    private String order;
}
