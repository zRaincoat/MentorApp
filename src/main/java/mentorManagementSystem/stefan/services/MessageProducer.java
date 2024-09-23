package mentorManagementSystem.stefan.services;

import mentorManagementSystem.stefan.entities.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageProducer {
    private final KafkaTemplate<String, Message> kafkaTemplate;

    public MessageProducer(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessageToMentee(Long mentorId, Long menteeId, String messageContent) {
        Message message = Message.builder()
                .mentorId(mentorId)
                .menteeId(menteeId)
                .messageContent(messageContent)
                .timestamp(LocalDateTime.now())
                .build();
        kafkaTemplate.send("mentor-to-mentee-topic", message);
    }
}

