package kr.co.unitalent.domain.chat;

import kr.co.unitalent.domain.BaseTimeEntity;
import kr.co.unitalent.domain.posts.TalentProduct;
import kr.co.unitalent.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString(callSuper = true)
@Getter
@NoArgsConstructor
@Entity
public class Chatroom extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private TalentProduct talentProduct;

    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private User seller;

    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    private User buyer;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long sellerUncheckedMessageCount;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long buyerUncheckedMessageCount;

    private String lastMessage;

    @OneToMany
    @JoinColumn(name = "chatroom_id",insertable = false, updatable = false)
    @ToString.Exclude
    private List<Chat> chats = new ArrayList<>();

    @Builder
    public Chatroom(Long id, TalentProduct talentProduct, User seller, User buyer) {
        this.id = id;
        this.talentProduct = talentProduct;
        this.seller = seller;
        this.buyer = buyer;
    }

    @PrePersist
    public void prePersist() {
        this.sellerUncheckedMessageCount = this.sellerUncheckedMessageCount == null ? 0L : this.sellerUncheckedMessageCount;
        this.buyerUncheckedMessageCount = this.buyerUncheckedMessageCount == null ? 0L : this.buyerUncheckedMessageCount;
    }

    public void sellerUpdate(String lastMessage) {
        this.sellerUncheckedMessageCount++;
        this.lastMessage = lastMessage;
    }

    public void buyerUpdate(String lastMessage) {
        this.buyerUncheckedMessageCount++;
        this.lastMessage = lastMessage;
    }

    public void sellerChecking() {
        this.sellerUncheckedMessageCount = 0L;
    }

    public void buyerChecking() {
        this.buyerUncheckedMessageCount = 0L;
    }
}
