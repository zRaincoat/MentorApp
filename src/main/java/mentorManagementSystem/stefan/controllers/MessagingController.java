package mentorManagementSystem.stefan.controllers;

import mentorManagementSystem.stefan.entities.Message;
import mentorManagementSystem.stefan.services.MenteeService;
import mentorManagementSystem.stefan.services.MentorService;
import mentorManagementSystem.stefan.services.MessageProducer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mentors/messages")
public class MessagingController {
    private final MessageProducer messageProducer;
    private final MentorService mentorService;

    public MessagingController(final MessageProducer messageProducer, final MentorService mentorService) {
        this.messageProducer = messageProducer;
        this.mentorService = mentorService;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestBody Message message){
        Long mentorId = message.getMentorId();
        Long menteeId = message.getMenteeId();
        String messageContent = message.getMessageContent();

        if(mentorService.checkIfMenteeHasThisMentor(menteeId, mentorId)){
            messageProducer.sendMessageToMentee(mentorId, menteeId, messageContent);
            return "Sent";
        }
        return "Mentee not found";
    }
}

