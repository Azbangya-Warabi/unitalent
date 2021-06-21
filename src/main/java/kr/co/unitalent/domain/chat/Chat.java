package kr.co.unitalent.domain.chat;

import kr.co.unitalent.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Chat extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatNumber;

    @Column(nullable = false)
    private Long roomNumber;

    @Column(nullable = false)
    private String request_user;

    @Column(nullable = false)
    private String response_user;

    @Column(length = 300)
    private String message;

    private String image;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean uncheking;

    @PrePersist
    public void prePersist() {
        this.uncheking = this.uncheking == null ? false : this.uncheking;
    }

}
