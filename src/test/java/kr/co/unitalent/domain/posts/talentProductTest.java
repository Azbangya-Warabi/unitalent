package kr.co.unitalent.domain.posts;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class talentProductTest {

    @Autowired
    TalentProductRepository talentProductRepository;

    @After
    public void cleanup() {
        talentProductRepository.deleteAll();
    }

    @Test
    public void saveTest() {
//        // given
//        String nickname = "success test createUser";
//        String title = "success test title";
//        String category = "success test category";
//        String serviceInformation = "success test serviceInformation";
//        String image = "success test image";
//        Long price = 10000L;
//        String type = "success test type";
//        String status = "비승인";
//
//        talentProductRepository.save(TalentProduct.builder()
//                .nickname(nickname)
//                .title(title)
//                .categoryCode(category)
//                .serviceInformation(serviceInformation)
//                .images(image)
//                .price(price)
//                .type(type)
//                .build()
//        );
//
//        // when
//        List<TalentProduct> talentProductList = talentProductRepository.findAll();
//
//        // then
//        TalentProduct talentProduct = talentProductList.get(0);
//        assertThat(talentProduct.getNickname()).isEqualTo(nickname);
//        assertThat(talentProduct.getTitle()).isEqualTo(title);
//        assertThat(talentProduct.getCategoryCode()).isEqualTo(category);
//        assertThat(talentProduct.getServiceInformation()).isEqualTo(serviceInformation);
//        assertThat(talentProduct.getImages()).isEqualTo(image);
//        assertThat(talentProduct.getPrice()).isEqualTo(price);
//        assertThat(talentProduct.getType()).isEqualTo(type);
//        assertThat(talentProduct.getStatus()).isEqualTo(status);
    }
}