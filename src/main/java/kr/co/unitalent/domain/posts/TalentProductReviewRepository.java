package kr.co.unitalent.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TalentProductReviewRepository extends JpaRepository<TalentProductReview, Long> {

    List<TalentProductReview> findByTalentProductIdOrderByCreateDateDesc(Long talentProductId);
}
