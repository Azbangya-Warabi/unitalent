package kr.co.unitalent.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.unitalent.domain.posts.TalentProduct;
import kr.co.unitalent.service.TalentProductService;
import kr.co.unitalent.web.dto.posts.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class TalentProductApiController {

    private final TalentProductService talentProductService;

    @GetMapping("/product/home")
    public ResponseEntity<List<TalentProductPreviewResponseDto>> findHome(@RequestParam(value = "type") String type) {
        return new ResponseEntity<>(talentProductService.findByHomeData(type), HttpStatus.OK);
    }


    @GetMapping("/product/{type}/list/page/{page}/amount/{amount}")
    public List<TalentProductPreviewResponseDto> getList(@PathVariable(value = "type") String type,
                                                         @RequestParam(value = "category") String categoryId,
                                                         @RequestParam(value = "location", required = false) String locationId,
                                                         @RequestParam(value = "sort", required = false) String sortOption) {
        return talentProductService.findByProductSellListWithPaging(type, categoryId, locationId, sortOption);
    }

    @GetMapping("/product/{userId}/mypage/sell/myservice")
    public ResponseEntity<List<TalentProductMypageResponseDto>> findMypage(@PathVariable Long userId) {
        return new ResponseEntity<>(talentProductService.findByMypageData(userId), HttpStatus.OK);
    }

    @GetMapping("/product")
    public ResponseEntity<List<TalentProductPreviewResponseDto>> findAll() {
        return new ResponseEntity<>(talentProductService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public TalentProductDetailResponseDto findByProductId(@PathVariable Long productId) {
        return talentProductService.findByProductId(productId);
    }

    @GetMapping("/product/{productId}/post")
    public TalentProductPostResponseDto findByPost(@PathVariable Long productId) {
        return talentProductService.findByProductIdToPost(productId);
    }

    @GetMapping("/product/{productId}/images")
    public List<TalentProductImageResponseDto> findByImages(@PathVariable Long productId) {
        return talentProductService.findByProductImage(productId);
    }


    @PostMapping(value = "/product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Long talentProductSave(@ModelAttribute @Valid TalentProductSaveRequestDto talentProductSaveRequestDto) {
        return talentProductService.talentProductSave(talentProductSaveRequestDto);
    }

    @PutMapping("/product/{productId}")
    public Long talentProductUpdate(@PathVariable Long productId, @RequestBody @Valid TalentProductUpdateDto talentProductUpdateDto) {
        return talentProductService.talentProductUpdate(productId, talentProductUpdateDto);
    }

    @DeleteMapping("/product/{productId}")
    public Long talentProductDelete(@PathVariable Long productId) {
        return talentProductService.delete(productId);
    }


    // test
    @GetMapping("/product/test/{productId}")
    public TalentProductDetailResponseDto getDetail(@PathVariable Long productId) {
        return talentProductService.findByProductId(productId);
    }

    @GetMapping("/test/querydsl")
    public List<TalentProductPreviewResponseDto> testFind() {
        return talentProductService.testFind("1", "1");
    }
}
