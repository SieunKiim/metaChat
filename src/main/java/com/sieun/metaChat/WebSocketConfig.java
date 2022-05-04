package com.sieun.metaChat;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;


//@Configuration
//@RequiredArgsConstructor
//@EnableWebSocket
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/university").setAllowedOrigins("*"); // websocket 핸드셰이크를 위한 endpoint 지정
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.setApplicationDestinationPrefixes("/pub"); // 대상 헤더가 시작되는 STOMP 메시지는 해당 클래스의 메서드로 라우팅됨
        config.enableSimpleBroker("/sub");
    }
}
