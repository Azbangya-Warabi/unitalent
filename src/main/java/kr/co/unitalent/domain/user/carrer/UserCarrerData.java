package kr.co.unitalent.domain.user.carrer;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class UserCarrerData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userCarrerDataNumber;

    @Column(nullable = false)
    private Long userCarrerNumber;

    private String certificationData;
}
