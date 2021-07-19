package kr.co.unitalent.domain.user.carrer;

import kr.co.unitalent.domain.BaseTimeEntity;
import kr.co.unitalent.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@ToString
@Getter
@NoArgsConstructor
@Entity
public class UserCareer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private String careerName;

    @Column(nullable = false)
    @ColumnDefault(value = "'미인증'")
    private String certification;

    @OneToOne(mappedBy = "userCareer")
    private UserCareerData userCareerData;

    @PrePersist
    public void prePersist() {
        this.certification = this.certification == null ? "미인증" : this.certification;
    }
}
