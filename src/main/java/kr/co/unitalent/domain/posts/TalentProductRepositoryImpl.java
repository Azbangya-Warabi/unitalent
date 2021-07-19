package kr.co.unitalent.domain.posts;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import java.util.List;

import static kr.co.unitalent.domain.posts.QTalentProduct.talentProduct;
import static kr.co.unitalent.domain.global.QCategory.category;

@RequiredArgsConstructor
public class TalentProductRepositoryImpl implements TalentProductRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<TalentProduct> findByProductList(String type ,String categoryId, String locationId, String sortOption) {
        return queryFactory.selectFrom(talentProduct)
                .leftJoin(category)
                .on(talentProduct.category.id.eq(category.id))
                .where(talentProduct.type.eq(type),
                        talentProduct.status.eq("승인"),
                        isCategoryId(categoryId),
                        isLocationId(locationId))
                .orderBy(sortOption(sortOption))
                .fetch();
    }

    @Override
    public List<TalentProduct> testFind(String category, String location) {
        return queryFactory.selectFrom(talentProduct)
                .where(talentProduct.category.id.eq(category),
                        talentProduct.user.location.id.eq(location))
                .fetch();
    }

    private BooleanExpression isCategoryId(String categoryId) {
        if(!StringUtils.hasLength(categoryId)) {
            return null;
        }
        return talentProduct.category.id.startsWith(categoryId);
    }

    private BooleanExpression isLocationId(String locationId) {
        if(!StringUtils.hasLength(locationId)) {
            return null;
        }
        return talentProduct.user.location.id.startsWith(locationId);
    }

    private OrderSpecifier<?> sortOption(String sortOption) {
        if(sortOption.equals("modifiedDate")) {
            return talentProduct.modifiedDate.desc();
        } else if(sortOption.equals("expensive")) {
            return talentProduct.price.desc();
        } else if(sortOption.equals("cheap")) {
            return talentProduct.price.asc();
        } else if(sortOption.equals("review")) {
            return talentProduct.talentProductReviewTotal.averageStarScore.desc();
        }
        return talentProduct.modifiedDate.desc();
    }
}
