package kr.co.unitalent.domain.global;

import kr.co.unitalent.domain.posts.TalentProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@ToString(callSuper = true)
@Getter
@NoArgsConstructor
@Entity
public class Category {

    @Id
    private String id;

    private String largeGroup;

    private String middleGroup;

    private String smallGroup;

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private List<TalentProduct> talentProduct = new ArrayList<>();

    @Builder
    public Category(String id, String largeGroup, String middleGroup, String smallGroup) {
        this.id = id;
        this.largeGroup = largeGroup;
        this.middleGroup = middleGroup;
        this.smallGroup = smallGroup;
    }
}
