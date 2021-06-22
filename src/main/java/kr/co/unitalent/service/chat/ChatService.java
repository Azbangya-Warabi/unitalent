package kr.co.unitalent.service.chat;

import kr.co.unitalent.domain.chat.Chat;
import kr.co.unitalent.domain.chat.ChatRepository;
import kr.co.unitalent.domain.chat.ChatroomRepository;
import kr.co.unitalent.web.dto.chat.ChatResponseDto;
import kr.co.unitalent.web.dto.chat.ChatSaveRequestDto;
import kr.co.unitalent.web.dto.chat.ChatroomResponseDto;
import kr.co.unitalent.web.dto.chat.ChatroomSaveRequestDto;
import kr.co.unitalent.web.dto.posts.TalentSellSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatService {
    private final ChatroomRepository chatroomRepository;
    private final ChatRepository chatRepository;

    // chatroom-service

    public List<ChatroomResponseDto> getAllChatroom(String nickname) {
        return chatroomRepository.findBySellerOrBuyerOrderByModifiedDate(nickname, nickname).stream().map(ChatroomResponseDto::new).collect(Collectors.toList());
    }


    @Transactional
    public Long createChatroom(ChatroomSaveRequestDto chatroomSaveRequestDto) {
        return chatroomRepository.save(chatroomSaveRequestDto.toEntity()).getRoomNumber();
    }

    // chat-service

    public List<ChatResponseDto> findByRoomNum(Long roomNum) {
        return chatRepository.findByRoomNumberOrderByCreateDate(roomNum).stream().map(ChatResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Long changeCheckedToTrue(Long roomNumber, String requestUser) {
        List<Chat> chatList = chatRepository.findByRoomNumberAndRequestUserAndChecked(roomNumber, requestUser, false);
        chatList.forEach(entity -> entity.changeCheckedToTrue(true));
        return roomNumber;
    }

    @Transactional
    public Long saveChatMessage(ChatSaveRequestDto chatSaveRequestDto) {
        return chatRepository.save(chatSaveRequestDto.toEntity()).getRoomNumber();
    }
}
