package mentorManagementSystem.stefan.repositories;

import jakarta.transaction.Transactional;
import mentorManagementSystem.stefan.entities.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {

    Mentor findMentorById(Long id);

    Mentor getMentorByUsername(String username);

    boolean existsById(Long id);

    void deleteById(Long id);

    Optional<Mentor> findMentorByMentees_Id(Long menteeId);
}
