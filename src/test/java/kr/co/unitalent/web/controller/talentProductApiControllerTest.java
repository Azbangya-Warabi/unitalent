package kr.co.unitalent.web.controller;

import kr.co.unitalent.domain.global.Category;
import kr.co.unitalent.domain.global.CategoryRepository;
import kr.co.unitalent.domain.posts.TalentProduct;
import kr.co.unitalent.domain.posts.TalentProductModifiedHistoryRepository;
import kr.co.unitalent.domain.posts.TalentProductRepository;
import kr.co.unitalent.domain.user.User;
import kr.co.unitalent.domain.user.UserRepository;
import kr.co.unitalent.service.AdminService;
import kr.co.unitalent.service.TalentProductService;
import kr.co.unitalent.web.dto.admin.TalentProductStatusUpdateDto;
import kr.co.unitalent.web.dto.posts.TalentProductSaveRequestDto;
import kr.co.unitalent.web.dto.posts.TalentProductUpdateDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class talentProductApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    TalentProductService talentProductService;
    @Autowired
    TalentProductRepository talentProductRepository;
    @Autowired
    TalentProductModifiedHistoryRepository talentProductModifiedHistoryRepository;
    @Autowired
    AdminService adminService;

    @Test
    void talentProductSaveTest() {
        // given
        givenUser();
        givenCategory();
        Long userId = 1L;
        String title = "success test title";
        String category = "1";
        String serviceInformation = "success test serviceInformation";
        String images = "success test image";
        Long price = 10000L;
        String type = "success test type";
        String status = "비승인";

        TalentProductSaveRequestDto talentProductSaveRequestDto = TalentProductSaveRequestDto.builder()
                .userId(userId)
                .title(title)
                .categoryId(category)
                .serviceInformation(serviceInformation)
                .price(price)
                .type(type)
                .build();

        String url = "http://localhost:"+port+"/api/product";

        // when
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, talentProductSaveRequestDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        talentProductRepository.findAll().forEach(System.out::println);

    }

    @Test
    void talentProductUpdateAndHistorySaveTest() {
        // given
        givenUser();
        givenCategory();
        TalentProduct productEntity = givenProduct();
        talentProductRepository.save(productEntity);
        adminService.changeProductStatus(productEntity.getId(), TalentProductStatusUpdateDto.builder().status("승인").build());

        System.out.println(talentProductRepository.findById(productEntity.getId()));

        String url = "http://localhost:"+port+"/api/product/"+productEntity.getId();

        HttpEntity<TalentProductUpdateDto> requestEntity = new HttpEntity<>(TalentProductUpdateDto.builder()
                .title("업데이트")
                .categoryId("2")
                .serviceInformation("업데이트")
                .build());
        // when
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

       // then
        System.out.println(talentProductRepository.findById(productEntity.getId()));
        talentProductModifiedHistoryRepository.findAll().forEach(System.out::println);
    }
//
//    @Test
//    void talentSellDelete() {
//        // given
//        String nickname = "success test createUser";
//        String title = "success test title";
//        String category = "success test category";
//        String serviceInformation = "success test serviceInformation";
//        String images = "success test image";
//        Long price = 10000L;
//        String type = "success test type";
//        String status = "비승인";
//
//        TalentProduct saveTalentProduct = talentProductRepository.save(TalentProduct.builder()
//                .nickname(nickname)
//                .title(title)
//                .categoryCode(category)
//                .serviceInformation(serviceInformation)
//                .images(images)
//                .price(price)
//                .type(type)
//                .build());
//
//        Long deleteId = saveTalentProduct.getBoardNumber();
//
//        String url = "http://localhost:"+port+"/api/talent-sell/"+deleteId;
//
//        HttpEntity<Long> requestEntity = new HttpEntity<>(deleteId);
//
//        // when
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Long.class);
//
//        // then
//        assertThat(responseEntity.getStatusCode().OK).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//        List<TalentProduct> talentProductList = talentProductRepository.findAll();
//        TalentProduct talentProduct = talentProductList.get(0);
//        assertThat(talentProduct.getStatus()).isEqualTo("비활성화");
//    }

    public void givenUser() {
        for (int i = 0; i < 5; i++) {
            userRepository.save(User.builder()
                    .name("name" + i)
                    .email("email" + i)
                    .thumbnail("thumbnail" + i)
                    .build());
        }
//        userRepository.findAll().forEach(System.out::println);
    }

    public void givenCategory() {
        for (int i = 0; i < 5; i++) {
            categoryRepository.save(Category.builder()
                    .id(i+"")
                    .build());
        }
//        categoryRepository.findAll().forEach(System.out::println);
    }

    public TalentProduct givenProduct() {
        return TalentProduct.builder()
                .user(User.builder().id(1L).build())
                .title("제목")
                .category(Category.builder().id("1").build())
                .serviceInformation("본문")
                .images("이미지")
                .price(1000L)
                .type("talent-sell")
                .build();
    }
}