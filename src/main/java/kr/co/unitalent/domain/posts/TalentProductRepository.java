package kr.co.unitalent.domain.posts;

import kr.co.unitalent.web.dto.posts.TalentProductDetailResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TalentProductRepository extends JpaRepository<TalentProduct, Long>, TalentProductRepositoryCustom {

    List<TalentProduct> findTop8ByStatusAndTypeOrderByModifiedDateDesc(String status, String type);

    List<TalentProduct> findByStatusOrderByModifiedDateDesc(String status);

    List<TalentProduct> findByUserIdOrderByModifiedDateDesc(Long userId);

    Page<TalentProduct> findByStatusAndTypeOrderByModifiedDateDesc(String status, Pageable pageable, String type);

    Long countByStatusAndType(String status, String type);

    @Query(value = "select a from TalentProduct a join fetch a.talentProductReviewTotal b join fetch a.user c where a.id = :id")
    TalentProduct findByProductId(@Param("id") Long id);
}
