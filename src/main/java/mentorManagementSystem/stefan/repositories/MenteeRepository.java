package mentorManagementSystem.stefan.repositories;

import mentorManagementSystem.stefan.entities.Mentee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenteeRepository extends JpaRepository<Mentee, Long> {
    Mentee findByUsername(String username);

    List<Mentee> getAllMenteesByMentorId(Long id);

    Mentee save(Mentee mentee);

}
