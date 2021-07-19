package kr.co.unitalent.domain.chat;

import kr.co.unitalent.domain.BaseTimeEntity;
import kr.co.unitalent.domain.user.User;
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
    private Long id;

    @ManyToOne
    private Chatroom chatroom;

    @ManyToOne
    private User requestUser;

    @Column(length = 300)
    private String message;

    private String image;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean checked;

    @Builder
    public Chat (Chatroom chatroom, User requestUser, String message, String image) {
        this.chatroom = chatroom;
        this.requestUser = requestUser;
        this.message = message;
        this.image = image;
    }

    public void changeCheckedToTrue() {
        this.checked = true;
    }

    @PrePersist
    public void prePersist() {
        this.checked = this.checked == null ? false : this.checked;
    }

}
