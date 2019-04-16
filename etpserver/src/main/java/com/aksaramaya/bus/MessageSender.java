package com.aksaramaya.bus;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class MessageSender {
    private String topic = "users";
    private KafkaProducer<Integer, String> producer;

    public MessageSender() {
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "178.128.106.42:9092");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        this.producer = new KafkaProducer<>(kafkaProps);
    }

    public void send(String payload) throws Exception {
        ProducerRecord<Integer, String> record = new ProducerRecord<>(this.topic, 1, payload);
        RecordMetadata metadata = producer.send(record).get();
        System.out.println(metadata.offset());
    }
}
