package kr.co.unitalent.domain.global;

import kr.co.unitalent.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Location {

    @Id
    private String id;

    private String largeGroup;

    private String middleGroup;

    @OneToMany
    @JoinColumn(name = "location_id", insertable = false, updatable = false)
    List<User> users = new ArrayList<>();
}
