package kr.co.unitalent.domain.chat;

import kr.co.unitalent.domain.BaseTimeEntity;
import lombok.Builder;
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
    private String requestUser;

    @Column(length = 300)
    private String message;

    private String image;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean checked;

    @Builder
    public Chat (Long roomNumber, String requestUser, String message, String image) {
        this.roomNumber = roomNumber;
        this.requestUser = requestUser;
        this.message = message;
        this.image = image;
    }

    public void changeCheckedToTrue(boolean checked) {
        this.checked = checked;
    }

    @PrePersist
    public void prePersist() {
        this.checked = this.checked == null ? false : this.checked;
    }

}
