package ca.mcgill.ecse428.group1.sportifybackend.controller;


import ca.mcgill.ecse428.group1.sportifybackend.dto.MessageDto;
import ca.mcgill.ecse428.group1.sportifybackend.model.Message;
import ca.mcgill.ecse428.group1.sportifybackend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*")
@RestController

public class MessageController {

    @Autowired
    private MessageService messageService;


    @GetMapping(value = { "/message", "/message/" })
    public List<MessageDto> getAllMessages() {
        return messageService.getAllMessages().stream().map(p -> convertToMessageDto(p)).collect(Collectors.toList());
    }

    @GetMapping(value = {"/message/chat/{chatId}", "/message/chat/{chatId}/"})
    public List<MessageDto> getAllMessagesByChat(@PathVariable("chatId") String chatId) {
        return messageService.getMessageByChat(chatId).stream().map(p -> convertToMessageDto(p)).collect(Collectors.toList());
    }

    @PostMapping(value = {"/message/{id}", "/message/{id}/"})
    public MessageDto createMessage(@PathVariable("id") String id, @RequestParam String chatId, @RequestParam String description) throws IllegalArgumentException {
        return convertToDto(messageService.createMessage(id, chatId, description));
    }

    @DeleteMapping(value = { "/message/{id}", "/message/{id}/" })
    public MessageDto deleteMessage(@PathVariable("id") String id) {
        return convertToDto(MessageService.deleteMessage(id));
    }

    private MessageDto convertToDto(Message message) throws IllegalArgumentException{
        if (message == null){
            throw new IllegalArgumentException("Message does not exist.");
        }
        ChatController chatController = new ChatController();
        MessageDto messageDto = new MessageDto(message.getId(), message.getDescription(), message.getDate(), chatController.convertToDto(message.getChat()));
        return messageDto;
    }


}