package ca.mcgill.ecse428.group1.sportifybackend.service;

import java.util.List;
import java.util.regex.Pattern;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.group1.sportifybackend.dao.ChatRepository;
import ca.mcgill.ecse428.group1.sportifybackend.model.Chat;
import ca.mcgill.ecse428.group1.sportifybackend.model.Member;
import ca.mcgill.ecse428.group1.sportifybackend.model.Message;

@Service
public class ChatService {
    @Autowired
    ChatRepository chatRepository;

    @Transactional
    public Chat createChat(List<Member> members, List<Message> messages) throws IllegalArgumentException {
        if (members.isEmpty() || messages.isEmpty()) {
            throw new IllegalArgumentException("Please input members and messages");
        }
        Chat chat = new Chat();
        chat.setMembers(members);
        chat.setMessages(messages);
        return chatRepository.save(chat);
    }

    @Transactional
    public Chat getChat(int id) throws IllegalArgumentException {
        Chat chat = chatRepository.findById(id);
        if (chat == null) {
            throw new IllegalArgumentException("Chat does not exist!");
        }
        return chat;
    }

    @Transactional
    public List<Member> getMembers(int id) throws IllegalArgumentException {
        List<Member> members = chatRepository.findById(id).getMembers();
        if (members == null || members.isEmpty()) {
            throw new IllegalArgumentException("Member does not exist!");
        }
        return members;
    }

    @Transactional
    public List<Message> getMessages(int id) throws IllegalArgumentException {
        List<Message> messages = chatRepository.findById(id).getMessages();
        if (messages == null || messages.isEmpty()) {
            throw new IllegalArgumentException("Message does not exist!");
        }
        return messages;
    }

    @Transactional
    public void setMembers(int id, List<Member> members) throws IllegalArgumentException {
        chatRepository.findById(id).setMembers(members);
    }

    @Transactional
    public void setMessages(int id, List<Message> messages) throws IllegalArgumentException {
        chatRepository.findById(id).setMessages(messages);
    }

    @Transactional
    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }

    @Transactional
    public void deleteChat(int id) throws IllegalArgumentException {
        Chat c = getChat(id);
        chatRepository.delete(c);
    }
}
