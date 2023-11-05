package ca.mcgill.ecse428.group1.sportifybackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.group1.sportifybackend.dao.FriendRequestRepository;
import ca.mcgill.ecse428.group1.sportifybackend.model.FriendRequest;
import ca.mcgill.ecse428.group1.sportifybackend.model.FriendRequestStatus;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
public class FriendRequestService {
    
    @Autowired
    private FriendRequestRepository friendRequestRepository;

    @Autowired
    private MemberService memberService;

    @Transactional
    public FriendRequest createFriendRequest(String message, String senderUsername, String receiverUsername) throws IllegalArgumentException{

        FriendRequest checkFriendRequest = friendRequestRepository.findFriendRequestBySenderAndReceiver(memberService.getMember(senderUsername), memberService.getMember(receiverUsername));
        if (checkFriendRequest != null && checkFriendRequest.getStatus() == FriendRequestStatus.PENDING) {
            throw new IllegalArgumentException("Friend request already exists!");
        }
        FriendRequest checkFriendRequest2 = friendRequestRepository.findFriendRequestBySenderAndReceiver(memberService.getMember(receiverUsername), memberService.getMember(senderUsername));
        if (checkFriendRequest2 != null && checkFriendRequest2.getStatus() == FriendRequestStatus.PENDING) {
            throw new IllegalArgumentException("This person already sent you a friend request!");
        }
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setMessage(message);
        friendRequest.setSender(memberService.getMember(senderUsername));
        friendRequest.setReceiver(memberService.getMember(receiverUsername));
        friendRequest.setStatus(FriendRequestStatus.PENDING);
        Calendar calendar = Calendar.getInstance();
        Date currentTime = new Date(calendar.getTime().getTime());
        friendRequest.setTime(currentTime);
        friendRequestRepository.save(friendRequest);
        return friendRequest;
    }

    @Transactional
    public FriendRequest updateFriendRequestStatus(int id, String status) throws IllegalArgumentException{
        FriendRequest friendRequest = friendRequestRepository.findFriendRequestById(id);
        if (friendRequest == null) {
            throw new IllegalArgumentException("Friend request does not exist!");
        }
        if (status.equals("ACCEPTED")) {
            friendRequest.setStatus(FriendRequestStatus.ACCEPTED);
            friendRequest.getSender().getFriends().add(friendRequest.getReceiver());
            friendRequest.getReceiver().getFriends().add(friendRequest.getSender());
            //should a friend request be deleted after being accepted?
        } else if (status.equals("REJECTED")) {
            friendRequest.setStatus(FriendRequestStatus.REJECTED);
        } else {
            throw new IllegalArgumentException("Invalid status!");
        }
        friendRequestRepository.save(friendRequest);
        return friendRequest;
    }

    @Transactional
    public FriendRequest getFriendRequestById(int id) throws IllegalArgumentException{
        FriendRequest friendRequest = friendRequestRepository.findFriendRequestById(id);
        if (friendRequest == null) {
            throw new IllegalArgumentException("Friend request does not exist!");
        }
        return friendRequest;
    }

    @Transactional
    public List<FriendRequest> getFriendRequestBySender(String username) throws IllegalArgumentException{
        return friendRequestRepository.findFriendRequestBySender(memberService.getMember(username));
    }

    @Transactional
    public List<FriendRequest> getFriendRequestByReceiver(String username) throws IllegalArgumentException{
        return friendRequestRepository.findFriendRequestByReceiver(memberService.getMember(username));
    }

    @Transactional
    public FriendRequest deleteFriendRequest(int id) throws IllegalArgumentException{
        FriendRequest friendRequest = friendRequestRepository.findFriendRequestById(id);
        if (friendRequest == null) {
            throw new IllegalArgumentException("Friend request does not exist!");
        }
        friendRequestRepository.delete(friendRequest);
        return friendRequest;
    }

}
