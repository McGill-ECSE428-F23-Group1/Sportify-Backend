package ca.mcgill.ecse428.group1.sportifybackend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse428.group1.sportifybackend.dto.ChatDto;
import ca.mcgill.ecse428.group1.sportifybackend.dto.MemberDto;
import ca.mcgill.ecse428.group1.sportifybackend.model.Gender;
import ca.mcgill.ecse428.group1.sportifybackend.model.Chat;
import ca.mcgill.ecse428.group1.sportifybackend.model.Member;
import ca.mcgill.ecse428.group1.sportifybackend.model.Message;
import ca.mcgill.ecse428.group1.sportifybackend.service.ChatService;

@CrossOrigin(origins = "*")
@RestController
public class ChatController {
    @Autowired
    private ChatService service;

    @GetMapping(value = { "/chat", "/chat/" })
    public List<ChatDto> getAllChats() {
        return service.getAllChats().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
    }

    @PostMapping(value = { "/chat/{id}", "/chat/{id}/" })
    public MemberDto createMember(@PathVariable("id") int id,
            @RequestParam List<Member> members, @RequestParam List<Message> messages,
        Chat chat = service.createChat(members, messages);
        return convertToDto(chat);
    }

    @GetMapping(value = { "/chat/{id}", "/chat/{id}/" })
    public ChatDto getChat(@PathVariable("id") int id) throws IllegalArgumentException {
        return convertToDto(service.getChat(id));
    }

    @DeleteMapping(value = { "/chat/{id}", "/chat/{id}/" })
    public void deleteMember(@PathVariable("id") int id) throws IllegalArgumentException {
        service.deleteChat(id);
    }

    @PatchMapping(value = { "/chat/{id}", "/chat/{id}/" })
    public ChatDto updateChat(@PathVariable("id") int id, @RequestParam Message message)
            throws IllegalArgumentException {
        Chat chat = service.getChat(id);
        chat.getMessages.add(message);
        return convertToDto(chat);
    }

    private ChatDto convertToDto(Chat c) throws IllegalArgumentException {
        if (c == null) {
            throw new IllegalArgumentException("Chat does not exist!");
        }

        ChatDto chatDto = new ChatDto(c.getId(), c.getMembers(), c.getMessages());

        return chatDto;
    }
}
