package kr.co.unitalent.domain.global;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Category {

    @Id
    private String categoryCode;

    private String largeGroup;

    private String middleGroup;

    private String smallGroup;
}
