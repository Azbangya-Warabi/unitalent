package kr.co.unitalent.domain.user.carrer;

import kr.co.unitalent.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class UserUniverseData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String major;

    @ColumnDefault("false")
    private boolean certification;

    private String certificationData;

    private LocalDateTime registrationDate;

    private LocalDateTime certificationDate;

    @OneToOne(mappedBy = "userUniverseData")
    private User user;

}
