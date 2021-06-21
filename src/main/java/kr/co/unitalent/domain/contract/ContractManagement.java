package kr.co.unitalent.domain.contract;

import kr.co.unitalent.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class ContractManagement extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractNumber;

    @Column(nullable = false)
    private String sellerNickname;

    @Column(nullable = false)
    private String buyerNickname;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Long boardNumber;

    @Column(nullable = false)
    private String statusCode;

    @CreatedDate
    private LocalDateTime requestDate;

    private LocalDateTime requestRejectDate;

    private LocalDateTime contractConcludeDate;

    private LocalDateTime contractCancleDate;

    private LocalDateTime contarctCompleteDate;

    private int star;

    private String comment;
}
