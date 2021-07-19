package kr.co.unitalent.domain.chat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findByChatroomIdOrderByCreateDate(Long chatroomId);

    List<Chat> findByChatroomIdAndRequestUserIdAndChecked(Long chatroomId, Long requestUserId, boolean checked);
}
