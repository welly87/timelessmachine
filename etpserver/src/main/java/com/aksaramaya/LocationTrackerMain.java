package com.aksaramaya;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;

public class LocationTrackerMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        WebSocketClient webSocketClient = new StandardWebSocketClient();
        WebSocketSession webSocketSession = webSocketClient.doHandshake(new TextWebSocketHandler() {
            @Override
            public void handleTextMessage(WebSocketSession session, TextMessage message) {
                System.out.println("received message - " + message.getPayload());
            }

            @Override
            public void afterConnectionEstablished(WebSocketSession session) {
                System.out.println("established connection - " + session);
            }
        }, new WebSocketHttpHeaders(), URI.create("ws://localhost:8090/mdls")).get();

        newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            try {
                TextMessage message = new TextMessage("Hello !!");
                webSocketSession.sendMessage(message);
                System.out.println("sent message - " + message.getPayload());
            } catch (Exception e) {
                System.out.println("Exception while sending a message " + e.getMessage());
            }
        }, 1, 10, TimeUnit.SECONDS);
    }
}
