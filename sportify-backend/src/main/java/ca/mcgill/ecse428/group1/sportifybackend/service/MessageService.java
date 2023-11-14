package ca.mcgill.ecse428.group1.sportifybackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.group1.sportifybackend.dao.MessageRepository;
import ca.mcgill.ecse428.group1.sportifybackend.model.Message;

import java.sql.Date;
import java.util.Calendar;
import java.time.LocalDate;

public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatService chatService;

    @Transactional
    public Message createMessage(String id, String chatId, String description) throws IllegalArgumentException{
        if (id == null || id.trim().length() == 0) {
            throw new IllegalArgumentException("Message id cannot be null.");
        }
        if (messageRepository.getMessageById(id) != null) {
            throw new IllegalArgumentException("This message already exists.");
        }
        if (description == null || description.trim().length() == 0) {
            throw new IllegalArgumentException("Message description cannot be null.");
        }

        Message message = new Message();
        message.setId(id);
        message.setChat(chatService.getChat(chatId));
        message.setDate(LocalDate.now())

        return message;
    }

    @Transactional
    public Message getMessage(String id) throws IllegalArgumentException{
        if (id == null || id.trim().length() == 0) {
            throw new IllegalArgumentException("Message id cannot be null.");
        }
        Message message = messageRepository.getMessageById(id);

        if (message == null){
            throw new IllegalArgumentException("Message does not exist");
        }

        return message;
    }

    @Transactional
    public List<Message> getMessagesByChat(String chatId){
        if (chatId = null || chatId.trim().length() == 0) {
            throw new IllegalArgumentException("Chat id cannot be null");
        }
        return messageRepository.getMessagesByChatId(chatService.getChat(chatId));
    }

    @Transactional
    public Message deleteMessage(String id) throws IllegalArgumentException{
        Message message = messageRepository.findMessageById(id);
        if (message == null){
            throw new IllegalArgumentException("Message does not exist.");
        }
        messageRepository.delete(message);
        return message;
    }
}