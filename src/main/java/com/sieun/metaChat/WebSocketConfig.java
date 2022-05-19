package com.sieun.metaChat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.socket.config.annotation.*;


//@Configuration
//@RequiredArgsConstructor
//@EnableWebSocket
@Configuration
@EnableWebSocketMessageBroker
@Slf4j
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/university").setAllowedOriginPatterns("*").withSockJS(); // websocket 핸드셰이크를 위한 endpoint 지정
        log.info("소켓 연결");
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
//        config.setPathMatcher(new AntPathMatcher("."));
        config.setApplicationDestinationPrefixes("/pub"); // 대상 헤더가 시작되는 STOMP 메시지는 해당 클래스의 메서드로 라우팅됨
        config.enableSimpleBroker("/sub");
    }
}
