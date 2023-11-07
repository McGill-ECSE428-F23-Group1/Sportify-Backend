package ca.mcgill.ecse428.group1.sportifybackend.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse428.group1.sportifybackend.model.Chat;

public interface ChatRepository extends CrudRepository<Chat, String> {
    Chat findById(int id);

    List<Chat> findAll();
}
