package ca.mcgill.ecse428.group1.sportifybackend.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse428.group1.sportifybackend.model.FriendRequest;
import ca.mcgill.ecse428.group1.sportifybackend.model.Member;
import java.util.List;

public interface FriendRequestRepository extends CrudRepository<FriendRequest, Integer> {
    FriendRequest findFriendRequestById(int id);

    List<FriendRequest> findFriendRequestBySender(Member sender);

    List<FriendRequest> findFriendRequestByReceiver(Member receiver);

    FriendRequest findFriendRequestBySenderAndReceiver(Member sender, Member receiver);

}
