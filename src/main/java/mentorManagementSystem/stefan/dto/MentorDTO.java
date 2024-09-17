package mentorManagementSystem.stefan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MentorDTO {
    private Long id;
    private String username;
    private String name;
    private String expertise;
}
