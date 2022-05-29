package com.sieun.metaChat.controller;

import com.sieun.metaChat.dto.ChatMessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StompChatController {
    private final SimpMessagingTemplate template; // 특정 Broker로 메시지를 전달

    @MessageMapping("/chat/enter")
    public void enter(ChatMessageDTO message) {
        message.setMessage(message.getWriter() + "님이 채팅방에 입장하였습니다.");
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping("/chat/message")
    public void message(ChatMessageDTO message) {
        message.setMessage(message.getMessage());
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}
