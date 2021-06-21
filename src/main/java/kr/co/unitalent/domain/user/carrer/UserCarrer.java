package kr.co.unitalent.domain.user.carrer;

import kr.co.unitalent.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class UserCarrer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userCarrerNumber;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String carrerName;

    @Column(nullable = false)
    @ColumnDefault(value = "'미인증'")
    private String certification;

    @PrePersist
    public void prePersist() {
        this.certification = this.certification == null ? "미인증" : this.certification;
    }
}
