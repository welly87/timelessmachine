package com.aksaramaya.ws;

import com.aksaramaya.bus.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class TextMessageHandler extends TextWebSocketHandler {
    @Autowired
    private MessageSender sender;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println(message.getPayload());
        sender.send(message.getPayload());
    }
}