package com.sieun.metaChat.controller;

import com.sieun.metaChat.dto.request.RequestChatMessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StompChatController {

    private final SimpMessagingTemplate template; // 특정 Broker로 메시지를 전달

    @MessageMapping("/chat/enter")
    public void enter(RequestChatMessageDTO message) {
        String msg = message.getWriter() + "님이 채팅방에 입장하였습니다.";
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), msg);
    }

//    @MessageMapping("/chat/message")
//    public void message(RequestChatMessageDTO message) {
//        message.setMessage(message.getMessage());
//        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
//    }
}
