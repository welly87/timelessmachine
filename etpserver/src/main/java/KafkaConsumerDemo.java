import org.apache.kafka.clients.consumer.Consumer;

public class KafkaConsumerDemo {
	static SampleConsumer consumerThread;
    public static void main(String[] args) {
        consumerThread = new SampleConsumer("users");
        consumerThread.start();
    }
} 