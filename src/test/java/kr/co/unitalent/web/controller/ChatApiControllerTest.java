package kr.co.unitalent.web.controller;

import kr.co.unitalent.domain.chat.ChatroomRepository;
import kr.co.unitalent.domain.global.Category;
import kr.co.unitalent.domain.global.CategoryRepository;
import kr.co.unitalent.domain.posts.TalentProduct;
import kr.co.unitalent.domain.posts.TalentProductRepository;
import kr.co.unitalent.domain.user.User;
import kr.co.unitalent.domain.user.UserRepository;
import kr.co.unitalent.service.ChatService;
import kr.co.unitalent.service.TalentProductService;
import kr.co.unitalent.web.dto.chat.ChatroomSaveRequestDto;
import kr.co.unitalent.web.dto.posts.TalentProductSaveRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ChatApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ChatService chatService;
    @Autowired
    private ChatroomRepository chatroomRepository;
    @Autowired
    private TalentProductRepository talentProductRepository;

    @Transactional
    @Test
    void createChatroom() {
        // given
        givenUser();
        givenCategory();
        System.out.println(givenProduct());

        ChatroomSaveRequestDto chatroomSaveRequestDto = ChatroomSaveRequestDto.builder()
                .talentProductId(1L)
                .sellerId(1L)
                .buyerId(2L)
                .build();

        System.out.println(chatroomRepository.save(chatroomSaveRequestDto.toEntity()));
        System.out.println(chatService.findByRoomNum(1L));

//        String url = "http://localhost:"+port+"/api/chatroom";
//
//        // when
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, chatroomSaveRequestDto, Long.class);
//
//        // then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
    }

    public void givenUser() {
        for (int i = 0; i < 5; i++) {
            userRepository.save(User.builder()
                    .name("name" + i)
                    .email("email" + i)
                    .thumbnail("thumbnail" + i)
                    .build());
        }
        userRepository.findAll().forEach(System.out::println);
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
        return talentProductRepository.save(TalentProduct.builder()
                .user(User.builder().id(1L).build())
                .title("제목")
                .category(Category.builder().id("1").build())
                .serviceInformation("본문")
                .images("이미지")
                .price(1000L)
                .type("talent-sell")
                .build());
    }
}