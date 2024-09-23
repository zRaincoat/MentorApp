package mentorManagementSystem.stefan.repositories;

import mentorManagementSystem.stefan.entities.Mentee;
import mentorManagementSystem.stefan.entities.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenteeRepository extends JpaRepository<Mentee, Long> {
    Mentee findByUsername(String username);

    List<Mentee> getAllMenteesByMentorId(Long id);

}
