package kr.co.unitalent.util.converter;

import lombok.Builder;

public class CategoryConverter implements Converter<String> {
    private String code;

    @Builder
    public CategoryConverter(String code) {
        this.code = code;
    }

    @Override
    public String toConvert() {
        switch (this.code) {
            case "01":
                return "디자인";
            case "02":
                return "IT프로그래밍";
            default:
                return "전체";
        }
    }

    public String toInverseConverter() {
        switch (this.code) {
            case "디자인":
                return "1";
            case "IT프로그래밍":
                return "2";
            case "통역 번역":
                return "3";
            case "마케팅":
                return "4";
            case "영상/편집":
                return "5";
            case "레슨":
                return "6";
            case "스포츠":
                return "7";
            case "문서작업":
                return "8";
            default:
                return "전체";
        }
    }
}
