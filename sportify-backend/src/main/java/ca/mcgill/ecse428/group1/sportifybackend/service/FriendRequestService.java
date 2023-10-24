package ca.mcgill.ecse428.group1.sportifybackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.group1.sportifybackend.dao.FriendRequestRepository;
import ca.mcgill.ecse428.group1.sportifybackend.model.FriendRequest;

@Service
public class FriendRequestService {
    
    @Autowired
    private FriendRequestRepository friendRequestRepository;

    @Transactional
    public FriendRequest createFriendRequest(int id) {
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setId(id);
        friendRequestRepository.save(friendRequest);
        return friendRequest;
    }
}
