package kr.co.unitalent.web.controller;

import kr.co.unitalent.service.chat.ChatService;
import kr.co.unitalent.web.dto.chat.ChatResponseDto;
import kr.co.unitalent.web.dto.chat.ChatSaveRequestDto;
import kr.co.unitalent.web.dto.chat.ChatroomResponseDto;
import kr.co.unitalent.web.dto.chat.ChatroomSaveRequestDto;
import kr.co.unitalent.web.dto.posts.TalentSellResponseDto;
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

    @GetMapping("/chatroom/{nickname}/list")
    public ResponseEntity<List<ChatroomResponseDto>> findAllChatroom(@PathVariable String nickname) {
        return new ResponseEntity<>(chatService.getAllChatroom(nickname), HttpStatus.OK);
    }

    @PostMapping("/chatroom")
    public Long createChatroom(@RequestBody @Valid ChatroomSaveRequestDto chatroomSaveRequestDto) {
        return chatService.createChatroom(chatroomSaveRequestDto);
    }

    // chat
    @GetMapping("/chatroom/{roomNumber}/chat/message")
    public ResponseEntity<List<ChatResponseDto>> findByRoomNumber(@PathVariable Long roomNumber) {
        return new ResponseEntity<>(chatService.findByRoomNum(roomNumber), HttpStatus.OK);
    }

    @PostMapping("/chatroom/{roomNumber}/chat/{nickname}/checked")
    public Long changeCheckedToTrue(@PathVariable Long roomNumber, @PathVariable String nickname) {
        return chatService.changeCheckedToTrue(roomNumber, nickname);
    }

    @PostMapping("/chat")
    public Long saveChatMessage(@RequestBody @Valid ChatSaveRequestDto chatSaveRequestDto) {
        return chatService.saveChatMessage(chatSaveRequestDto);
    }
}
