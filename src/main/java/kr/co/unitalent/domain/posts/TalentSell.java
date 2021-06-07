package kr.co.unitalent.domain.posts;

import kr.co.unitalent.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class TalentSell extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_number")
    private Long boardNumber;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(nullable = false)
    private String category;

    @Column(name = "service_information", length = 3000, nullable = false)
    private String serviceInformation;

    @Column(length = 4000)
    private String images;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    @ColumnDefault(value = "'비승인'")
    private String status;

    @Builder
    public TalentSell(String title, String category, String serviceInformation, String images, String type) {
        this.title = title;
        this.category = category;
        this.serviceInformation = serviceInformation;
        this.images = images;
        this.type = type;
    }

    @PrePersist
    public void prePersist() {
        this.status = this.status == null ? "비승인" : this.status;
    }

}
