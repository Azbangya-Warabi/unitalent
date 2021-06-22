package kr.co.unitalent.web.controller;

import kr.co.unitalent.domain.posts.TalentSell;
import kr.co.unitalent.domain.posts.TalentSellRepository;
import kr.co.unitalent.service.posts.TalentSellService;
import kr.co.unitalent.web.dto.posts.TalentSellSaveRequestDto;
import kr.co.unitalent.web.dto.posts.TalentSellUpdateDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TalentSellApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    TalentSellService talentSellService;

    @Autowired
    TalentSellRepository talentSellRepository;

    @Test
    void talentSellSave() {
        // given
        String nickname = "success test createUser";
        String title = "success test title";
        String category = "success test category";
        String serviceInformation = "success test serviceInformation";
        String images = "success test image";
        Long price = 10000L;
        String type = "success test type";
        String status = "비승인";

        TalentSellSaveRequestDto talentSellSaveRequestDto = TalentSellSaveRequestDto.builder()
                .nickname(nickname)
                .title(title)
                .categoryCode(category)
                .serviceInformation(serviceInformation)
                .images(images)
                .price(price)
                .type(type)
                .build();

        String url = "http://localhost:"+port+"/api/talent-sell";

        // when
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, talentSellSaveRequestDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<TalentSell> talentSellList = talentSellRepository.findAll();
        TalentSell talentSell = talentSellList.get(0);
        assertThat(talentSell.getNickname()).isEqualTo(nickname);
        assertThat(talentSell.getTitle()).isEqualTo(title);
        assertThat(talentSell.getCategoryCode()).isEqualTo(category);
        assertThat(talentSell.getServiceInformation()).isEqualTo(serviceInformation);
        assertThat(talentSell.getImages()).isEqualTo(images);
        assertThat(talentSell.getPrice()).isEqualTo(price);
        assertThat(talentSell.getType()).isEqualTo(type);
        assertThat(talentSell.getStatus()).isEqualTo(status);
//        assertThat(talentSell.getCreate_date()).isEqualTo(LocalDateTime.now());
//        assertThat(talentSell.getModified_date()).isEqualTo(LocalDateTime.now());
    }

    @Test
    void talentSellUpdate() {
        // given
        String nickname = "success test createUser";
        String title = "success test title";
        String category = "success test category";
        String serviceInformation = "success test serviceInformation";
        String images = "success test image";
        Long price = 10000L;
        String type = "success test type";
        String status = "비승인";

        TalentSell saveTalentSell = talentSellRepository.save(TalentSell.builder()
                .nickname(nickname)
                .title(title)
                .categoryCode(category)
                .serviceInformation(serviceInformation)
                .images(images)
                .price(price)
                .type(type)
                .build());

        Long updateId = saveTalentSell.getBoardNumber();
        String changeTitle = "changed title";
        Long changePrice = 30000L;

        TalentSellUpdateDto requestDto = TalentSellUpdateDto.builder()
                .title(changeTitle)
                .categoryCode(category)
                .serviceInformation(serviceInformation)
                .images(images)
                .price(changePrice)
                .build();

        String url = "http://localhost:"+port+"/api/talent-sell/"+updateId;

        HttpEntity<TalentSellUpdateDto> requestEntity = new HttpEntity<>(requestDto);

        // when
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

       // then
        assertThat(responseEntity.getStatusCode().OK).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<TalentSell> talentSellList = talentSellRepository.findAll();
        TalentSell talentSell = talentSellList.get(0);
        assertThat(talentSell.getTitle()).isEqualTo(changeTitle);
        assertThat(talentSell.getPrice()).isEqualTo(changePrice);
    }

    @Test
    void talentSellDelete() {
        // given
        String nickname = "success test createUser";
        String title = "success test title";
        String category = "success test category";
        String serviceInformation = "success test serviceInformation";
        String images = "success test image";
        Long price = 10000L;
        String type = "success test type";
        String status = "비승인";

        TalentSell saveTalentSell = talentSellRepository.save(TalentSell.builder()
                .nickname(nickname)
                .title(title)
                .categoryCode(category)
                .serviceInformation(serviceInformation)
                .images(images)
                .price(price)
                .type(type)
                .build());

        Long deleteId = saveTalentSell.getBoardNumber();

        String url = "http://localhost:"+port+"/api/talent-sell/"+deleteId;

        HttpEntity<Long> requestEntity = new HttpEntity<>(deleteId);

        // when
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Long.class);

        // then
        assertThat(responseEntity.getStatusCode().OK).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<TalentSell> talentSellList = talentSellRepository.findAll();
        TalentSell talentSell = talentSellList.get(0);
        assertThat(talentSell.getStatus()).isEqualTo("비활성화");
    }
}