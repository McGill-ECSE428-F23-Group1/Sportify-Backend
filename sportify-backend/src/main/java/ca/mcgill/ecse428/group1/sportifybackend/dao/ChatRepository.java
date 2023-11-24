package ca.mcgill.ecse428.group1.sportifybackend.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse428.group1.sportifybackend.model.Chat;
import ca.mcgill.ecse428.group1.sportifybackend.model.Member;

public interface ChatRepository extends CrudRepository<Chat, String> {
    Chat findChatById(int id);

    List<Chat> findAll();

    List<Chat> findChatsByMember1(Member member1); // does this work?

    List<Chat> findChatsByMember2(Member member2); // does this work?

    Chat findChatByMember1AndMember2(Member member1, Member member2);

}