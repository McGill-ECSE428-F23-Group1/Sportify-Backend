package ca.mcgill.ecse428.group1.sportifybackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.group1.sportifybackend.dao.MessageRepository;
import ca.mcgill.ecse428.group1.sportifybackend.model.Message;
import ca.mcgill.ecse428.group1.sportifybackend.model.Chat;
import ca.mcgill.ecse428.group1.sportifybackend.model.Member;

import java.sql.Timestamp;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatService chatService;

    @Autowired
    private MemberService memberService;

    @Transactional
    public Message createMessage(String senderUsername, String receiverUsername, String description) throws IllegalArgumentException{

        if (senderUsername == null || senderUsername.trim().length() == 0) {
            throw new IllegalArgumentException("Message sender cannot be empty.");
        }
        if (receiverUsername == null || receiverUsername.trim().length() == 0) {
            throw new IllegalArgumentException("Message receiver cannot be empty.");
        }
        if (description == null || description.trim().length() == 0) {
            throw new IllegalArgumentException("Message description cannot be empty.");
        }

        Member sender = memberService.getMember(senderUsername);
        Member receiver = memberService.getMember(receiverUsername);

        if (sender == null || receiver == null){
            throw new IllegalArgumentException("Message sender or receiver does not exist.");
        }
        
        Chat chat = chatService.getChatByMembers(senderUsername, receiverUsername);

        if (chat == null){
            throw new IllegalArgumentException("Chat does not exist.");
        }

        Message message = new Message();
        message.setMessageSender(sender);
        message.setMessageReceiver(receiver);
        message.setDescription(description);
        message.setChat(chat);
        long currentTime = System.currentTimeMillis();
        Timestamp currentTimestamp = new Timestamp(currentTime);
        message.setDate(currentTimestamp);
        messageRepository.save(message);
        return message;
    }

    @Transactional
    public Message getMessage(int id) throws IllegalArgumentException{

        Message message = messageRepository.findMessageById(id);

        if (message == null){
            throw new IllegalArgumentException("Message does not exist");
        }

        return message;
    }

    @Transactional
    public Message deleteMessage(int id) throws IllegalArgumentException{
        Message message = messageRepository.findMessageById(id);
        if (message == null){
            throw new IllegalArgumentException("Message does not exist.");
        }
        messageRepository.delete(message);
        return message;
    }
}