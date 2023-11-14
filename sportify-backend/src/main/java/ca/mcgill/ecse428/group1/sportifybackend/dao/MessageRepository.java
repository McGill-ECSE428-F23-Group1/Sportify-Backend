package ca.mcgill.ecse428.group1.sportifybackend.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse428.group1.sportifybackend.model.Message;
import ca.mcgill.ecse428.group1.sportifybackend.model.Chat;
import java.util.List;

public interface MessageRepository extends CrudRepository<Message, String> {

    Message findMessageById(String id);

    List<Message> findMessagesByChat(Chat chat);

}