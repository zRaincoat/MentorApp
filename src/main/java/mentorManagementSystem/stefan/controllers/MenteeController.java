package mentorManagementSystem.stefan.controllers;

import mentorManagementSystem.stefan.dto.MenteeDTO;
import mentorManagementSystem.stefan.entities.Mentee;
import mentorManagementSystem.stefan.services.MenteeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/mentees")
public class MenteeController {
    private final MenteeService menteeService;

    public MenteeController(MenteeService menteeService) {
        this.menteeService = menteeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenteeDTO> getMenteeById(@PathVariable Long id) {
        if(!menteeService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Mentee mentee = menteeService.getMenteeById(id);
        MenteeDTO menteeDTO = menteeService.convertMenteeToDto(mentee);
        return ResponseEntity.ok(menteeDTO);
    }

    @GetMapping("/search/{username}")
    public ResponseEntity<MenteeDTO> getMenteeByUsername(@PathVariable String username) {
        Mentee mentee = menteeService.getMenteeByUsername(username);
        if(!menteeService.existsById(mentee.getId())) {
            return ResponseEntity.notFound().build();
        }
        MenteeDTO menteeDTO = menteeService.convertMenteeToDto(mentee);
        return ResponseEntity.ok(menteeDTO);
    }

    @PostMapping
    public ResponseEntity<MenteeDTO> createMentee(@RequestBody Mentee mentee) {
        menteeService.create(mentee);
        MenteeDTO menteeDTO = menteeService.convertMenteeToDto(mentee);
        return ResponseEntity.ok(menteeDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mentee> updateMentee(@PathVariable Long id, @RequestBody Mentee mentee) {
        if(!menteeService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(menteeService.save(id, mentee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMentee(@PathVariable Long id) {
        if(!menteeService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        menteeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
