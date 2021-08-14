package kr.co.unitalent.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ToString(callSuper = true)
@Getter
@NoArgsConstructor
@Entity
public class TalentProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private TalentProduct talentProduct;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private Long dataOrder;

    @Builder
    public TalentProductImage(TalentProduct talentProduct, String imageUrl, Long dataOrder) {
        this.talentProduct = talentProduct;
        this.imageUrl = imageUrl;
        this.dataOrder = dataOrder;
    }
}
