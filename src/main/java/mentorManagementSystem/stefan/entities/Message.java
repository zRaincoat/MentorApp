package mentorManagementSystem.stefan.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {
    private Long mentorId;
    private Long menteeId;
    private String messageContent;
    private LocalDateTime timestamp;
}
