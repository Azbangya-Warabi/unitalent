package kr.co.unitalent.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TalentProductModifiedHistoryRepository extends JpaRepository<TalentProductModifiedHistory, Long> {

    List<TalentProductModifiedHistory> findByIdOrderByCreateDateDesc(Long id);
}
