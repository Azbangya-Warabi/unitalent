package kr.co.unitalent.domain.chat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {
    List<Chatroom> findBySellerIdOrBuyerIdOrderByModifiedDate(Long sellerId, Long buyerId);
}
