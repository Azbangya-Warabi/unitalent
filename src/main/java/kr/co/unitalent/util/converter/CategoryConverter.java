package kr.co.unitalent.util.converter;

import lombok.Builder;

public class CategoryConverter implements Converter<String> {
    private String  code;

    @Builder
    public CategoryConverter(String code) {
        this.code = code;
    }

    @Override
    public String toConvert() {
        switch (this.code) {
            case "1":
                return "IT프로그래밍";
            case  "2":
                return "영문서 번역";
            default:
                return "해당하는 카테고리가 없습니다.";
        }
    }
}
