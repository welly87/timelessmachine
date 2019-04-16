package com.aksaramaya;

import com.aksaramaya.bus.MessageReceiver;

import java.util.concurrent.ExecutionException;

public class StorageMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MessageReceiver receiver = new MessageReceiver("users");
        receiver.start();
    }
} 