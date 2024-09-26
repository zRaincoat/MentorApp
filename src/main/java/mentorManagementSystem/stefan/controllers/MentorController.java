package mentorManagementSystem.stefan.controllers;

import mentorManagementSystem.stefan.dto.MenteeDTO;
import mentorManagementSystem.stefan.dto.MentorDTO;
import mentorManagementSystem.stefan.entities.Mentee;
import mentorManagementSystem.stefan.entities.Mentor;
import mentorManagementSystem.stefan.services.MentorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        MentorDTO mentorDTO = mentorService.convertMentorToDto(mentor);
        return ResponseEntity.ok(mentorDTO);
    }

    @GetMapping("/search/{username}")
    public ResponseEntity<MentorDTO> getMentorByUsername(@PathVariable String username) {
        Mentor mentor = mentorService.getMentorByUsername(username);
        if (!mentorService.existsById(mentor.getId())) {
            return ResponseEntity.notFound().build();
        }
        MentorDTO mentorDTO = mentorService.convertMentorToDto(mentor);
        return ResponseEntity.ok(mentorDTO);
    }

    @GetMapping("/mymentees/{id}")
    public ResponseEntity<List<MenteeDTO>> getAllMenteesOfAMentor(@PathVariable Long id){
        if (!mentorService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        List<Mentee> mentees = mentorService.getAllMenteesOfMentor(id);
        List<MenteeDTO> menteeDTOs = mentees.stream()
                .map(mentorService::convertMenteeToDto)
                .toList();
        return ResponseEntity.ok(menteeDTOs);
    }

    @PostMapping
    public ResponseEntity<Mentor> createMentor(@RequestBody Mentor mentor){
        return ResponseEntity.ok(mentorService.create(mentor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mentor> updateMentor(@PathVariable Long id, @RequestBody Mentor mentor){
        if(!mentorService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mentorService.save(id, mentor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMentor(@PathVariable Long id) {
        if(!mentorService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        mentorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
