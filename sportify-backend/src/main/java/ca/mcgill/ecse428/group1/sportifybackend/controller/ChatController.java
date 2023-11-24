package ca.mcgill.ecse428.group1.sportifybackend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse428.group1.sportifybackend.dto.ChatDto;
import ca.mcgill.ecse428.group1.sportifybackend.dto.MessageDto;
import ca.mcgill.ecse428.group1.sportifybackend.model.Chat;
import ca.mcgill.ecse428.group1.sportifybackend.model.Message;
import ca.mcgill.ecse428.group1.sportifybackend.service.ChatService;

@CrossOrigin(origins = "*")
@RestController
public class ChatController {
    @Autowired
    private ChatService service;

    @PostMapping(value = { "/chat", "/chat/" })
    public ChatDto createChat(@RequestParam String member1Username, @RequestParam String member2Username) {
        return convertToDto(service.createChat(member1Username, member2Username));
    }

    /*
     * @author: Shaun 
     * You can use this to get all the chats for one member
     * 
     * @param username
     */
    @GetMapping(value = { "/chat/{username}", "/chat/{username}/" })
    public List<ChatDto> getAllChatForOneMember(@PathVariable("username") String username) throws IllegalArgumentException {
        return service.getChatsByMember(username).stream().map(p -> convertToDto(p)).collect(Collectors.toList());
    }

    /*
     * @author: Shaun 
     * You can use this to get the chat between two members.
     * This will also return all the messages
     * 
     * @param member1Username
     * @param member2Username
     */
    @GetMapping(value = { "/chat/", "/chat" })
    public ChatDto getChatBetweenTwoMembers(@RequestParam String member1Username, @RequestParam String member2Username) throws IllegalArgumentException {
        return convertToDto(service.getChatByMembers(member1Username, member2Username));
    }

    /*
     * @Author: Shaun
     * This will return true if the chat exists, false otherwise
     * 
     * @param member1Username
     * @param member2Username
     */

    @GetMapping(value = { "/chat/checkIfExists", "/chat/checkIfExists/" })
    public boolean checkIfExists(@RequestParam String member1Username, @RequestParam String member2Username) throws IllegalArgumentException {
        return service.checkIfChatExists(member1Username, member2Username);
    }

    @DeleteMapping(value = { "/chat", "/chat/" })
    public void deleteMember(@RequestParam String member1Username, @RequestParam String member2Username) throws IllegalArgumentException {
        service.deleteChat(member1Username, member2Username);
    }

    public ChatDto convertToDto(Chat c) throws IllegalArgumentException {
        if (c == null) {
            throw new IllegalArgumentException("Chat does not exist!");
        }
        MemberController memberController = new MemberController();
        MessageController messageController = new MessageController();
        List<Message> messages = c.getMessages();
        List<MessageDto> messageDtos = new ArrayList<MessageDto>();
        if (messages != null) {
            for (Message m: messages) {
                messageDtos.add(messageController.convertToDto(m));
            }
        }
        ChatDto chatDto = new ChatDto(c.getId(), memberController.convertToDto(c.getMember1()), memberController.convertToDto(c.getMember2()), messageDtos);
        return chatDto;
    }
}