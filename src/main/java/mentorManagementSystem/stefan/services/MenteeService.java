package mentorManagementSystem.stefan.services;

import mentorManagementSystem.stefan.dto.MenteeDTO;
import mentorManagementSystem.stefan.entities.Mentee;
import mentorManagementSystem.stefan.entities.Mentor;
import mentorManagementSystem.stefan.repositories.MenteeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenteeService {
    private final MenteeRepository menteeRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public MenteeService(MenteeRepository menteeRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.menteeRepository = menteeRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    public Mentee create(Mentee mentee) {
        mentee.setPassword(passwordEncoder.encode(mentee.getPassword()));
        menteeRepository.save(mentee);
        return mentee;
    }

    public Mentee getMenteeById(Long id) {
        return menteeRepository.findById(id).orElse(null);
    }

    public Mentee getMenteeByUsername(String username) {
        return menteeRepository.findByUsername(username);
    }

    public boolean existsById(Long id) {
        return menteeRepository.existsById(id);
    }

    public Mentee save(Long id, Mentee mentee) {
        mentee.setId(id);
        return menteeRepository.save(mentee);
    }

    public void deleteById(Long id) {
        menteeRepository.deleteById(id);
    }

    public List<Mentee> getAllMenteesByMentorId(Long id) {
        return menteeRepository.getAllMenteesByMentorId(id);
    }

    public MenteeDTO convertToDto(Mentee mentee) {
        return modelMapper.map(mentee, MenteeDTO.class);
    }
}
