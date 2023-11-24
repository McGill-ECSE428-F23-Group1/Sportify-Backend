package ca.mcgill.ecse428.group1.sportifybackend.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse428.group1.sportifybackend.model.Message;

public interface MessageRepository extends CrudRepository<Message, String> {
    Message findMessageById(int id);
}
