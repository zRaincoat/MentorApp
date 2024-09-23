package mentorManagementSystem.stefan.services;

import mentorManagementSystem.stefan.entities.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {
    @KafkaListener(topics = "mentor-to-mentee-topic", groupId = "mentee-group")
    public void consumeMessage(Message message) {
        System.out.println(message.getTimestamp() + ": " + message.getMessageContent());
    }
}
