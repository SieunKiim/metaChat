package com.sieun.metaChat;

import com.sieun.metaChat.handler.WebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;


//@Configuration
//@RequiredArgsConstructor
//@EnableWebSocket
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

//    private final WebSocketHandler chatHandler;
//
//    // 도메인이 다른 서버에서도 원활히 데이터를 주고받을 수 있도록 .setAllowedOrighins("*") -> 전부 다 받는 것으로 설정
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) { // 웹 소켓 핸드쉐이크
//        registry.addHandler(chatHandler, "/chat").setAllowedOrigins("*"); // 웹 소켓에 연결할 주소 (ws://~~~/chat)
//    }


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/example").setAllowedOrigins("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.setApplicationDestinationPrefixes("/pub");
        config.enableSimpleBroker("/sub");
    }
}
