package ca.mcgill.ecse428.group1.sportifybackend.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse428.group1.sportifybackend.model.FriendRequest;

public interface FriendRequestRepository extends CrudRepository<FriendRequest, Integer> {
    FriendRequest findFriendRequestById(int id);
}
