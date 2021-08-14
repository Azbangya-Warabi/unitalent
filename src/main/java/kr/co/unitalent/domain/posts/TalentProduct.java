package kr.co.unitalent.domain.posts;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import kr.co.unitalent.domain.BaseTimeEntity;
import kr.co.unitalent.domain.chat.Chat;
import kr.co.unitalent.domain.chat.Chatroom;
import kr.co.unitalent.domain.global.Category;
import kr.co.unitalent.domain.user.User;
import kr.co.unitalent.web.dto.posts.TalentProductUpdateDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString(callSuper = true)
@Getter
@NoArgsConstructor
@Entity
public class TalentProduct extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Column(length = 30, nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(length = 3000, nullable = false)
    private String serviceInformation;

    private Long price;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    @ColumnDefault(value = "'비승인'")
    private String status;

    private String statusMessage;

    private String beforeStatusMessage;

    private LocalDateTime approvalDate;

    @OneToMany(mappedBy = "talentProduct")
    private List<TalentProductImage> talentProductImages = new ArrayList<>();

    @OneToOne(mappedBy = "talentProduct")
    private TalentProductReviewTotal talentProductReviewTotal;

    @OneToMany(mappedBy = "talentProduct")
    @ToString.Exclude
    private List<TalentProductReview> talentProductReview = new ArrayList<>();

    @OneToMany(mappedBy = "talentProduct")
    @ToString.Exclude
    private List<TalentProductModifiedHistory> talentProductModifiedHistories = new ArrayList<>();

    @OneToMany(mappedBy = "talentProduct")
    @ToString.Exclude
    private List<Chatroom> chatrooms = new ArrayList<>();

    @Builder
    public TalentProduct(Long id, User user, String title, Category category, String serviceInformation, Long price, String type) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.category = category;
        this.serviceInformation = serviceInformation;
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


    public void update(TalentProductUpdateDto talentProductUpdateDto) {
        this.title = talentProductUpdateDto.getTitle();
        this.category = Category.builder().id(talentProductUpdateDto.getCategoryId()).build();
        this.serviceInformation = talentProductUpdateDto.getServiceInformation();
        this.price = talentProductUpdateDto.getPrice();
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
