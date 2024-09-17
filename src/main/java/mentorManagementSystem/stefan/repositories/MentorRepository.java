package mentorManagementSystem.stefan.repositories;

import jakarta.transaction.Transactional;
import mentorManagementSystem.stefan.entities.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {

    Mentor findMentorById(Long id);

    Mentor getMentorByUsername(String username);

    boolean existsById(Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Mentor m WHERE m.id = :id")
    default void deleteMentorById(@Param("id") Long id) {

    }
}
