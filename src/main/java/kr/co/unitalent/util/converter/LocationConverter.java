package kr.co.unitalent.util.converter;

import lombok.Builder;

public class LocationConverter implements Converter<String>{

    private String  code;

    @Builder
    public LocationConverter(String code) {
        this.code = code;
    }

    @Override
    public String toConvert() {
        switch (this.code) {
            case "1":
                return "강원도 춘천";
            case  "2":
                return "영문서 번역";
            default:
                return "해당하는 지역이 없습니다.";
        }
    }
}
