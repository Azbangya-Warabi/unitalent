package kr.co.unitalent.domain.global;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class ContractStatus {

    @Id
    private String statusCode;

    private String status;

    private String statusDetail;
}
