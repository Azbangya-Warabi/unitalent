package kr.co.unitalent.domain.user.carrer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserCareerRepository extends JpaRepository<UserCareer, Long> {

    List<UserCareer> findByUserId(Long userId);
}
