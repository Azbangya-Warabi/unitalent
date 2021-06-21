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
class TalentSellTest {

    @Autowired
    TalentSellRepository talentSellRepository;

    @After
    public void cleanup() {
        talentSellRepository.deleteAll();
    }

    @Test
    public void saveTest() {
        // given
        String nickname = "success test createUser";
        String title = "success test title";
        String category = "success test category";
        String serviceInformation = "success test serviceInformation";
        String image = "success test image";
        Long price = 10000L;
        String type = "success test type";
        String status = "비승인";

        talentSellRepository.save(TalentSell.builder()
                .nickname(nickname)
                .title(title)
                .categoryCode(category)
                .serviceInformation(serviceInformation)
                .images(image)
                .price(price)
                .type(type)
                .build()
        );

        // when
        List<TalentSell> talentSellList = talentSellRepository.findAll();

        // then
        TalentSell talentSell = talentSellList.get(0);
        assertThat(talentSell.getNickname()).isEqualTo(nickname);
        assertThat(talentSell.getTitle()).isEqualTo(title);
        assertThat(talentSell.getCategoryCode()).isEqualTo(category);
        assertThat(talentSell.getServiceInformation()).isEqualTo(serviceInformation);
        assertThat(talentSell.getImages()).isEqualTo(image);
        assertThat(talentSell.getPrice()).isEqualTo(price);
        assertThat(talentSell.getType()).isEqualTo(type);
        assertThat(talentSell.getStatus()).isEqualTo(status);
    }
}