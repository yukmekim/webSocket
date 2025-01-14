package org.websocket.chat.config;

import org.websocket.chat.handler.ChatWebSocketHandler;
import org.websocket.chat.service.ChatService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class ChatConfig implements WebSocketConfigurer {

    private final ChatService chatService;

    public ChatConfig(ChatService chatService) {
        this.chatService = chatService;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatWebSocketHandler(), "/chat")
                .setAllowedOrigins("*"); // CORS 설정
    }

    @Bean
    public WebSocketHandler chatWebSocketHandler() {
        return new ChatWebSocketHandler(chatService);
    }

}
