package com.aksaramaya.ws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(binaryHandler(), "/etp");
        registry.addHandler(textHandler(), "/mdls");
    }

    @Bean
    public BinaryMessageHandler binaryHandler() {
        return new BinaryMessageHandler();
    }

    @Bean
    public TextMessageHandler textHandler() {
        return new TextMessageHandler();
    }

}