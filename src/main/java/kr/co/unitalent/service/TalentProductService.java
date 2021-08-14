package kr.co.unitalent.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.unitalent.domain.posts.*;
import kr.co.unitalent.domain.user.carrer.UserCareer;
import kr.co.unitalent.domain.user.carrer.UserCareerRepository;
import kr.co.unitalent.util.aws.S3;
import kr.co.unitalent.util.converter.FileConverter;
import kr.co.unitalent.web.dto.posts.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TalentProductService {

    private final TalentProductRepository talentProductRepository;
    private final TalentProductModifiedHistoryRepository talentProductModifiedHistoryRepository;
    private final TalentProductReviewTotalRepository talentProductReviewTotalRepository;
    private final UserCareerRepository userCareerRepository;
    private final TalentProductReviewRepository talentProductReviewRepository;
    private final TalentProductImageRepository talentProductImageRepository;

    @Transactional(readOnly = true)
    public List<TalentProductPreviewResponseDto> findByHomeData(String type) {
        return talentProductRepository.findTop8ByStatusAndTypeOrderByModifiedDateDesc("승인", type).stream().map(TalentProductPreviewResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TalentProductPreviewResponseDto> findAll() {
        return talentProductRepository.findByStatusOrderByModifiedDateDesc("비승인").stream().map(TalentProductPreviewResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TalentProductPreviewResponseDto> findByProductListWithPaging(int page, int size, String type) {
        return talentProductRepository.findByStatusAndTypeOrderByModifiedDateDesc("승인", PageRequest.of(page, size), type).stream().map(TalentProductPreviewResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TalentProductPreviewResponseDto> findByProductSellListWithPaging(String type ,String categoryId, String locationId, String sortOption) {
        return talentProductRepository.findByProductList(type, categoryId, locationId, sortOption).stream().map(TalentProductPreviewResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public List<TalentProductReviewResponseDto> findByProductReviews(Long userId) {
        return talentProductReviewRepository.findByTalentProductIdOrderByCreateDateDesc(userId).stream().map(TalentProductReviewResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Long talentProductCount(String type) {
        return talentProductRepository.countByStatusAndType("승인", type);
    }

    @Transactional(readOnly = true)
    public TalentProductDetailResponseDto findByProductId(Long productId) {
        TalentProduct entity = talentProductRepository.findByProductId(productId);
        List<UserCareer> userCareers = userCareerRepository.findByUserId(entity.getUser().getId());
        return new TalentProductDetailResponseDto(entity, userCareers);
    }

    @Transactional(readOnly = true)
    public TalentProductPostResponseDto findByProductIdToPost(Long productId) {
        TalentProduct entity = talentProductRepository.findByProductId(productId);
        return new TalentProductPostResponseDto(entity);
    }


    @Transactional(readOnly = true)
    public List<TalentProductImageResponseDto> findByProductImage(Long productId) {
        return talentProductImageRepository.findByTalentProductId(productId).stream().map(TalentProductImageResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TalentProductMypageResponseDto> findByMypageData(Long userId) {
        return talentProductRepository.findByUserIdOrderByModifiedDateDesc(userId).stream().map(TalentProductMypageResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Long talentProductSave(TalentProductSaveRequestDto requestDto) {
        requestDto.setUserId(1L);
        TalentProduct entity = talentProductRepository.save(requestDto.toEntity());
        talentProductReviewTotalRepository.save(TalentProductReviewTotal.builder().talentProduct(entity).build());

        System.out.println(requestDto);
        ObjectMapper objectMapper = new ObjectMapper();
        List<ImageDto> imageDto = new ArrayList<>();
        if(requestDto.getImages() != null) {
            System.out.println("s3");


            for(int i=1; i<requestDto.getImageInformation().size(); i++) {
                try {
                    String info = requestDto.getImageInformation().get(i);
                    System.out.println(info);
                    imageDto.add(objectMapper.readValue(info, ImageDto.class));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
//            requestDto.getImageInformation().forEach(info ->
//                    {
//                        try {
//                            System.out.println(info);
//                            imageDto.add(objectMapper.readValue(info, ImageDto.class));
//                        } catch (JsonProcessingException e) {
//                            e.printStackTrace();
//                        }
//                    }
//            );
            System.out.println(imageDto);
            for(int i=0; i<imageDto.size(); i++) {
                if(imageDto.get(i).getIsSaved().equals("false")) {
                    S3 awsS3 = new S3();
                    System.out.println(requestDto.getImages().size());
                    MultipartFile image = requestDto.getImages().get(i);
                    FileConverter multipartFile = FileConverter.builder().multipartFile(image).build();

                    String bucketName = "unitalenttest";
                    UUID uuid = UUID.randomUUID();
                    String filePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"))+uuid+image.getOriginalFilename();

                    awsS3.getS3Client().putObject(bucketName, filePath, multipartFile.toConvert());

                    URL uploadResult = awsS3.getS3Client().getUrl(bucketName, filePath);

                    TalentProductImage tImage = requestDto.toUnSavedImageEntity(entity, i, imageDto.get(i), uploadResult.getProtocol()+"://"+uploadResult.getHost()+uploadResult.getPath());
                    talentProductImageRepository.save(tImage);
                } else {
                    TalentProductImage image = requestDto.toSavedImageEntity(entity, i, imageDto.get(i));
                    talentProductImageRepository.save(image);
                }
            }
        }
        System.out.println("asd");
        return entity.getId();
    }

    @Transactional
    public Long talentProductUpdate(Long productId, TalentProductUpdateDto requestDto) {
        TalentProduct entity = talentProductRepository.findById(productId).orElseThrow(IllegalAccessError::new);
        if(entity.getStatus().equals("승인")) {
            talentProductModifiedHistoryRepository.save(new TalentProductModifiedHistorySaveDto(entity).toEntity());
        }
        entity.update(requestDto);
        return productId;
    }

    @Transactional
    public Long delete(Long productId) {
        TalentProduct entity = talentProductRepository.findById(productId).orElseThrow(IllegalAccessError::new);
        entity.delete("비활성화");
        return productId;
    }

    // test
    @Transactional
    public List<TalentProductPreviewResponseDto> testFind(String category, String location) {
        return talentProductRepository.testFind(category, location).stream().map(TalentProductPreviewResponseDto::new).collect(Collectors.toList());
    }
}
