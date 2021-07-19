package kr.co.unitalent.domain.posts;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TalentProductRepositoryCustom {
    List<TalentProduct> findByProductList(String type ,String categoryId, String locationId, String sortOption);

    List<TalentProduct> testFind(String category, String location);
}
