package kr.co.unitalent.domain.user.carrer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class UserUniverseData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long universeDataNumber;

    private String universeName;

    private String universeImage;

    private String major;

    private String certificationData;

    private LocalDateTime registrationDate;

    private LocalDateTime certificationDate;
}
