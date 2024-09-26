package mentorManagementSystem.stefan.services;

import mentorManagementSystem.stefan.dto.MenteeDTO;
import mentorManagementSystem.stefan.dto.MentorDTO;
import mentorManagementSystem.stefan.entities.Mentee;
import mentorManagementSystem.stefan.entities.Mentor;
import mentorManagementSystem.stefan.repositories.MentorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MentorService {
    private final PasswordEncoder passwordEncoder;
    private final MentorRepository mentorRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MentorService(PasswordEncoder passwordEncoder, MentorRepository mentorRepository, ModelMapper modelMapper) {
        this.passwordEncoder = passwordEncoder;
        this.mentorRepository = mentorRepository;
        this.modelMapper = modelMapper;
    }

    public Mentor create(Mentor mentor) {
        mentor.setPassword(passwordEncoder.encode(mentor.getPassword()));
        mentorRepository.save(mentor);
        return mentor;
    }

    public Mentor getMentorById(Long id) {
        return mentorRepository.findMentorById(id);
    }

    public Mentor getMentorByUsername(String username) {
        return mentorRepository.getMentorByUsername(username);
    }

    public boolean existsById(Long id) {
        return mentorRepository.existsById(id);
    }

    public void deleteById(Long id) {
        List<Mentee> menteesList = getMentorById(id).getMentees();
        for (Mentee mentee : menteesList) {
            mentee.setMentor(null);
        }
        mentorRepository.deleteById(id);
    }

    public Mentor save(Long id, Mentor mentor) {
        mentor.setPassword(passwordEncoder.encode(mentor.getPassword()));
        return mentorRepository.save(mentor);
    }

    public MentorDTO convertMentorToDto(Mentor mentor) {
        return modelMapper.map(mentor, MentorDTO.class);
    }

    public MenteeDTO convertMenteeToDto(Mentee mentee) {
        return modelMapper.map(mentee, MenteeDTO.class);
    }

    public List<Mentee> getAllMenteesOfMentor(Long id) {
        List<Mentee> mentees = getMentorById(id).getMentees();
        return mentees;
    }

    public boolean checkIfMenteeHasThisMentor(Long menteeId, Long mentorId) {
        Optional<Mentor> mentorOptional = mentorRepository.findMentorByMentees_Id(menteeId);
        return mentorOptional.isPresent() && mentorOptional.get().getId().equals(mentorId);
    }
}
