package kr.co.unitalent.web.controller;

import kr.co.unitalent.domain.posts.TalentProduct;
import kr.co.unitalent.service.TalentProductService;
import kr.co.unitalent.web.dto.posts.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

//    @GetMapping("/product/{type}/list/page/{page}/amount/{amount}")
//    public ResponseEntity<List<TalentProductPreviewResponseDto>> findByBoardListWithPaging(@PathVariable int page, @PathVariable int amount, @PathVariable String type) {
//        return new ResponseEntity<>(talentProductService.findByProductListWithPaging(page-1, amount, type), HttpStatus.OK);
//    }

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

    @PostMapping("/product")
    public Long talentProductSave(@RequestBody @Valid TalentProductSaveRequestDto talentProductSaveRequestDto) {
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
