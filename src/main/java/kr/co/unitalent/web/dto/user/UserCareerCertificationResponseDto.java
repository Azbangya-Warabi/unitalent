package kr.co.unitalent.web.dto.user;

import kr.co.unitalent.domain.user.carrer.UserCareer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class UserCareerCertificationResponseDto implements UserCareerResponseDto{
    private String careerName;

    @Builder
    public UserCareerCertificationResponseDto(UserCareer userCareer){
        addCareer(userCareer);
    }

    @Override
    public void addCareer(UserCareer userCareer) {
        this.careerName = userCareer.getCareerName();
    }
}
