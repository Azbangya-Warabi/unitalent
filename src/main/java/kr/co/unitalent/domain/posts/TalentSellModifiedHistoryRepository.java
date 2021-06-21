package kr.co.unitalent.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TalentSellModifiedHistoryRepository extends JpaRepository<TalentSellModifiedHistory, Long> {

    List<TalentSellModifiedHistory> findByBoardNumberOrderByCreateDateDesc(Long boardNumber);
}
