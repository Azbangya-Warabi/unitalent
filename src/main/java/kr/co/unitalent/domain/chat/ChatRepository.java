package kr.co.unitalent.domain.chat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findByRoomNumberOrderByCreateDate(Long roomNumber);

    List<Chat> findByRoomNumberAndRequestUserAndChecked(Long roomNumber, String requestUser, boolean checked);
}
