package kr.co.unitalent.domain.global;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Location {

    @Id
    private String locationCode;

    private String largeGroup;

    private String middleGroup;
}
