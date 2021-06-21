package kr.co.unitalent.domain.posts;

import kr.co.unitalent.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class TalentSell extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNumber;

    @Column(nullable = false)
    private String nickname;

    @Column(length = 30, nullable = false)
    private String title;

    @Column(nullable = false)
    private String categoryCode;

    @Column(length = 3000, nullable = false)
    private String serviceInformation;

    @Column(length = 4000)
    private String images;

    private Long price;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    @ColumnDefault(value = "'비승인'")
    private String status;

    private String statusMessage;

    private String beforeStatusMessage;

    private LocalDateTime approvalDate;

    @Builder
    public TalentSell(String nickname, String title, String categoryCode, String serviceInformation, String images, Long price, String type) {
        this.nickname = nickname;
        this.title = title;
        this.categoryCode = categoryCode;
        this.serviceInformation = serviceInformation;
        this.images = images;
        this.price = price;
        this.type = type;
    }

    @PrePersist
    public void prePersist() {
        this.status = this.status == null ? "비승인" : this.status;
    }

    @PreUpdate
    public void preUpdate() {
    }


    public void update(String title, String category, String serviceInformation, String images, Long price) {
        this.title = title;
        this.categoryCode = category;
        this.serviceInformation = serviceInformation;
        this.images = images;
        this.price = price;
        this.status = "비승인";
        this.beforeStatusMessage = this.statusMessage;
        this.statusMessage = null;
    }

    public void delete(String status) {
        this.status = status;
    }

    public void changeStatus(String status, String statusMessage) {
        this.status = status;
        this.statusMessage = statusMessage;
    }
}
