package kr.co.unitalent.domain.posts;

import kr.co.unitalent.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class TalentSellModifiedHistory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyNumber;

    private Long boardNumber;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false)
    private String categoryCode;

    @Column(nullable = false, length = 3000)
    private String serviceInformation;

    @Column(length = 4000)
    private String images;

    private Long price;

    private String statusMessage;

    @Builder
    public TalentSellModifiedHistory(Long boardNumber, String title, String categoryCode, String serviceInformation, String images, Long price, String statusMessage) {
        this.boardNumber = boardNumber;
        this.title = title;
        this.categoryCode = categoryCode;
        this.serviceInformation = serviceInformation;
        this.images = images;
        this.price = price;
        this.statusMessage = statusMessage;
    }
}
