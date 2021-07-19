package kr.co.unitalent.util.separator;

import lombok.Builder;

import java.util.Arrays;
import java.util.List;

public class ImageSeparator implements Separator<String>{
    private String images;

    @Builder
    public ImageSeparator(String images) {
        this.images = images;
    }

    @Override
    public List<String> toList() {
        return Arrays.asList(images.split(","));
    }
}
