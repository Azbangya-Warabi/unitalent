package kr.co.unitalent.util.converter;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileConverter implements Converter<File>{

    private MultipartFile multipartFile;

    @Builder
    public FileConverter(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    @Override
    public File toConvert() {
        try {
            File file = new File(System.getProperty("java.io.tmpdir")+"/"+multipartFile.getOriginalFilename());
            multipartFile.transferTo(file);
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
