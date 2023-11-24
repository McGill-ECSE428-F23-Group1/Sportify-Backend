package ca.mcgill.ecse428.group1.sportifybackend.controller;


import ca.mcgill.ecse428.group1.sportifybackend.dto.MessageDto;
import ca.mcgill.ecse428.group1.sportifybackend.model.Message;
import ca.mcgill.ecse428.group1.sportifybackend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*")
@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping(value = {"/message/", "/message"})
    public MessageDto createMessage(@RequestParam String senderUsername, @RequestParam String receiverUsername, @RequestParam String description) throws IllegalArgumentException {
        return convertToDto(messageService.createMessage(senderUsername, receiverUsername, description));
    }

    public MessageDto convertToDto(Message message) throws IllegalArgumentException{
        if (message == null){
            throw new IllegalArgumentException("Message does not exist.");
        }
        MemberController memberController = new MemberController();
        MessageDto messageDto = new MessageDto(message.getId(), message.getDescription(), message.getDate(), message.getChat().getId(), memberController.convertToDto(message.getMessageSender()), memberController.convertToDto(message.getMessageReceiver()));
        return messageDto;
    }
}