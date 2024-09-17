package mentorManagementSystem.stefan.controllers;

import mentorManagementSystem.stefan.dto.MenteeDTO;
import mentorManagementSystem.stefan.entities.Mentee;
import mentorManagementSystem.stefan.services.MenteeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
        MenteeDTO menteeDTO = menteeService.convertToDto(mentee);
        return ResponseEntity.ok(menteeDTO);
    }

    @GetMapping("/search/{username}")
    public ResponseEntity<MenteeDTO> getMenteeByUsername(@PathVariable String username) {
        Mentee mentee = menteeService.getMenteeByUsername(username);
        if(!menteeService.existsById(mentee.getId())) {
            return ResponseEntity.notFound().build();
        }
        MenteeDTO menteeDTO = menteeService.convertToDto(mentee);
        return ResponseEntity.ok(menteeDTO);
    }

    @GetMapping("/mymentees/{mentorId}")
    public ResponseEntity<List<MenteeDTO>> getMenteeByMentor(@PathVariable Long mentorId) {
        List<Mentee> mentees = menteeService.getAllMenteesByMentorId(mentorId);
        if(mentees.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<MenteeDTO> menteeDTOs = mentees.stream()
                .map(menteeService::convertToDto)
                .toList();
        return ResponseEntity.ok(menteeDTOs);
    }

    @PostMapping
    public ResponseEntity<MenteeDTO> createMentee(@RequestBody Mentee mentee) {
        menteeService.create(mentee);
        MenteeDTO menteeDTO = menteeService.convertToDto(mentee);
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
