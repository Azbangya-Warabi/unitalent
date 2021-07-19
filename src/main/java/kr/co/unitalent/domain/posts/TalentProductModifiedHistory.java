package kr.co.unitalent.domain.posts;

import kr.co.unitalent.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@NoArgsConstructor
@Entity
public class TalentProductModifiedHistory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private TalentProduct talentProduct;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false)
    private String categoryId;

    @Column(nullable = false, length = 3000)
    private String serviceInformation;

    private String images;

    @Column(nullable = false)
    private Long price;

    private String statusMessage;

    @Builder
    public TalentProductModifiedHistory(TalentProduct talentProduct, String title, String categoryId, String serviceInformation, String images, Long price, String statusMessage) {
        this.talentProduct = talentProduct;
        this.title = title;
        this.categoryId = categoryId;
        this.serviceInformation = serviceInformation;
        this.images = images;
        this.price = price;
        this.statusMessage = statusMessage;
    }
}
