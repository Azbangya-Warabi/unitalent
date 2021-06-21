package kr.co.unitalent.domain.posts;

import kr.co.unitalent.web.dto.TalentSellResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TalentSellRepository extends JpaRepository<TalentSell, Long> {

    List<TalentSell> findTop8ByStatusOrderByModifiedDateDesc(String status);

    List<TalentSell> findByStatusOrderByModifiedDateDesc(String status);

    List<TalentSell> findByNicknameOrderByModifiedDateDesc(String nickname);

    Page<TalentSell> findByStatus(String status, Pageable pageable);
}
