package kr.co.unitalent.domain.chat;

import kr.co.unitalent.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Chatroom extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomNumber;

    private String type;

    private Long boardNumber;

    @Column(nullable = false)
    private String seller;

    @Column(nullable = false)
    private String buyer;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long sellerUncheckedMessageCount;

    @LastModifiedDate
    private LocalDateTime sellerLastMessageDate;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long buyerUncheckedMessageCount;

    @LastModifiedDate
    private LocalDateTime buyerLastMessageDate;

    private String lastMessage;

    @PrePersist
    public void prePersist() {
        this.sellerUncheckedMessageCount = this.sellerUncheckedMessageCount == null ? 0L : this.sellerUncheckedMessageCount;
        this.buyerUncheckedMessageCount = this.buyerUncheckedMessageCount == null ? 0L : this.buyerUncheckedMessageCount;
    }
}
