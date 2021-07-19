package kr.co.unitalent.service;

import kr.co.unitalent.domain.chat.Chat;
import kr.co.unitalent.domain.chat.ChatRepository;
import kr.co.unitalent.domain.chat.Chatroom;
import kr.co.unitalent.domain.chat.ChatroomRepository;
import kr.co.unitalent.domain.posts.TalentProduct;
import kr.co.unitalent.domain.posts.TalentProductRepository;
import kr.co.unitalent.domain.user.User;
import kr.co.unitalent.domain.user.UserRepository;
import kr.co.unitalent.web.dto.chat.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatService {
    private final UserRepository userRepository;
    private final TalentProductRepository talentProductRepository;
    private final ChatroomRepository chatroomRepository;
    private final ChatRepository chatRepository;

    // chatroom-service

    @Transactional(readOnly = true)
    public List<ChatroomResponseDto> findAllChatroom(Long userId) {
        return chatroomRepository.findBySellerIdOrBuyerIdOrderByModifiedDate(userId, userId).stream().map(ChatroomResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ChatroomTalentProductResponseDto findTalentProductOfChatroom(Long productId, Long userId) {
        TalentProduct talentProduct = talentProductRepository.findByProductId(productId);
        User user = userRepository.findById(userId).get();
        return new ChatroomTalentProductResponseDto(talentProduct, user);
    }

    @Transactional
    public Long createChatroom(ChatroomSaveRequestDto chatroomSaveRequestDto) {
        return chatroomRepository.save(chatroomSaveRequestDto.toEntity()).getId();
    }

    // chat-service

    @Transactional(readOnly = true)
    public List<ChatResponseDto> findByRoomNum(Long chatroomId) {
        return chatRepository.findByChatroomIdOrderByCreateDate(chatroomId).stream().map(ChatResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Long changeCheckedToTrue(Long chatroomId, Long userId) {
        Chatroom chatroom = chatroomRepository.findById(chatroomId).get();
        if(chatroom.getSeller().getId() == userId) {
            chatroom.sellerChecking();
        } else {
            chatroom.buyerChecking();
        }
        List<Chat> chatList = chatRepository.findByChatroomIdAndRequestUserIdAndChecked(chatroomId, userId, false);
        chatList.forEach(entity -> entity.changeCheckedToTrue());
        return chatroomId;
    }

    @Transactional
    public Long saveChatMessage(ChatSaveRequestDto chatSaveRequestDto) {
        Chatroom chatroom = chatroomRepository.getById(chatSaveRequestDto.getChatroomId());
        if(chatroom.getSeller().getId() == chatSaveRequestDto.getRequestUser()) {
            if(chatSaveRequestDto.getMessage() != null) {
                chatroom.sellerUpdate(chatSaveRequestDto.getMessage());
            } else {
                chatroom.sellerUpdate("이미지 파일");
            }
        } else {
            if(chatSaveRequestDto.getMessage() != null) {
                chatroom.buyerUpdate(chatSaveRequestDto.getMessage());
            } else {
                chatroom.buyerUpdate("이미지 파일");
            }
        }
        return chatRepository.save(chatSaveRequestDto.toEntity()).getChatroom().getId();
    }
}
