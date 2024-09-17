package mentorManagementSystem.stefan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenteeDTO {
    private Long id;
    private String username;
    private String name;
    private Long mentorId;
}
