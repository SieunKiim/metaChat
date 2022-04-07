package com.sieun.metaChat.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;


@Component
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {

    private static List<WebSocketSession> list = new ArrayList<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload ={}", payload);

        for (WebSocketSession sess : list) {
            sess.sendMessage(message);
        }
    }

    // 접속이 되었을 때
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        list.add(session);
        log.info("{} | 접속", session);
    }

    //접속이 끊겼을 때
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("{} | 접속 끊김", session);
        list.remove(session);
    }
}
