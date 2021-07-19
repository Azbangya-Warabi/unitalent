package kr.co.unitalent.web.controller;

import kr.co.unitalent.service.ChatService;
import kr.co.unitalent.web.dto.chat.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class ChatApiController {

    private final ChatService chatService;

    // chatroom

    @GetMapping("/chatroom/{userId}/list")
    public ResponseEntity<List<ChatroomResponseDto>> findAllChatroom(@PathVariable Long userId) {
        return new ResponseEntity<>(chatService.findAllChatroom(userId), HttpStatus.OK);
    }

    @GetMapping("/chatroom/{chatroomId}/talent-product/{sellerId}")
    public ResponseEntity<ChatroomTalentProductResponseDto> findTalentProductOfChatroom(@PathVariable Long chatroomId, @PathVariable Long sellerId) {
        return new ResponseEntity<>(chatService.findTalentProductOfChatroom(chatroomId, sellerId), HttpStatus.OK);
    }

    @PostMapping("/chatroom")
    public Long createChatroom(@RequestBody @Valid ChatroomSaveRequestDto chatroomSaveRequestDto) {
        return chatService.createChatroom(chatroomSaveRequestDto);
    }

    // chat
    @GetMapping("/chatroom/{chatroomId}/chat/message")
    public ResponseEntity<List<ChatResponseDto>> findByRoomNumber(@PathVariable Long chatroomId) {
        return new ResponseEntity<>(chatService.findByRoomNum(chatroomId), HttpStatus.OK);
    }

    @PostMapping("/chatroom/{chatroomId}/chat/{userId}/checked")
    public Long changeCheckedToTrue(@PathVariable Long chatroomId, @PathVariable Long userId) {
        return chatService.changeCheckedToTrue(chatroomId, userId);
    }

    @PostMapping("/chat")
    public Long saveChatMessage(@RequestBody @Valid ChatSaveRequestDto chatSaveRequestDto) {
        return chatService.saveChatMessage(chatSaveRequestDto);
    }
}
