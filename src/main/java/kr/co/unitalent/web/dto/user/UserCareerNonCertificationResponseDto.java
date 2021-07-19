package kr.co.unitalent.web.dto.user;

import kr.co.unitalent.domain.user.carrer.UserCareer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class UserCareerNonCertificationResponseDto implements UserCareerResponseDto{
    private String careerName;

    @Builder
    public UserCareerNonCertificationResponseDto(UserCareer userCareer){
        addCareer(userCareer);
    }

    @Override
    public void addCareer(UserCareer userCareer) {
        this.careerName = userCareer.getCareerName();
    }
}
