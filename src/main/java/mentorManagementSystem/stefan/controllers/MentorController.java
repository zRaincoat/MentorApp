package mentorManagementSystem.stefan.controllers;

import mentorManagementSystem.stefan.dto.MentorDTO;
import mentorManagementSystem.stefan.entities.Mentor;
import mentorManagementSystem.stefan.services.MentorService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mentors")
public class MentorController {
    private final MentorService mentorService;

    public MentorController(final MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MentorDTO> getMentorById(@PathVariable Long id) {
        if (!mentorService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Mentor mentor = mentorService.getMentorById(id);
        MentorDTO mentorDTO = mentorService.convertToDto(mentor);
        return ResponseEntity.ok(mentorDTO);
    }

    @GetMapping("/search/{username}")
    public ResponseEntity<MentorDTO> getMentorByUsername(@PathVariable String username) {
        Mentor mentor = mentorService.getMentorByUsername(username);
        if (!mentorService.existsById(mentor.getId())) {
            return ResponseEntity.notFound().build();
        }
        MentorDTO mentorDTO = mentorService.convertToDto(mentor);
        return ResponseEntity.ok(mentorDTO);
    }

    @PostMapping
    public ResponseEntity<Mentor> createMentor(@RequestBody Mentor mentor){
        return ResponseEntity.ok(mentorService.create(mentor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mentor> updateMentor(@PathVariable Long id, @RequestBody Mentor mentor){
        if(mentorService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mentorService.save(id, mentor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMentor(@PathVariable Long id) {
        if(!mentorService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        mentorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
