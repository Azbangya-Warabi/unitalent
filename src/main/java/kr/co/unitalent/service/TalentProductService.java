package kr.co.unitalent.service;

import kr.co.unitalent.domain.posts.*;
import kr.co.unitalent.domain.user.carrer.UserCareer;
import kr.co.unitalent.domain.user.carrer.UserCareerRepository;
import kr.co.unitalent.util.separator.ImageSeparator;
import kr.co.unitalent.web.dto.posts.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TalentProductService {

    private final TalentProductRepository talentProductRepository;
    private final TalentProductModifiedHistoryRepository talentProductModifiedHistoryRepository;
    private final TalentProductReviewTotalRepository talentProductReviewTotalRepository;
    private final UserCareerRepository userCareerRepository;
    private final TalentProductReviewRepository talentProductReviewRepository;

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
    public List<TalentProductMypageResponseDto> findByMypageData(Long userId) {
        return talentProductRepository.findByUserIdOrderByModifiedDateDesc(userId).stream().map(TalentProductMypageResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Long talentProductSave(TalentProductSaveRequestDto requestDto) {
        int imageAmount = ImageSeparator.builder().images(requestDto.getImages()).build().toList().size();
        if(imageAmount == 0 || requestDto.getImages().equals("")) {
            requestDto.setImages("/image/util/no-image.png,/image/util/no-image.png,/image/util/no-image.png,/image/util/no-image.png,/image/util/no-image.png");
        } else {
            int appendDefaultImageAmount = 5-imageAmount;
            for(int i=0; i<appendDefaultImageAmount; i++) {
                requestDto.setImages(requestDto.getImages().concat(",/image/util/no-image.png"));
            }
        }
        TalentProduct entity = talentProductRepository.save(requestDto.toEntity());
        talentProductReviewTotalRepository.save(TalentProductReviewTotal.builder().talentProduct(entity).build());
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
