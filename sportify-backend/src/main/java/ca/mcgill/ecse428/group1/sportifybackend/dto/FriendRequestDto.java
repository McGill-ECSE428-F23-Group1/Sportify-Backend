package ca.mcgill.ecse428.group1.sportifybackend.dto;

import java.sql.Date;
import ca.mcgill.ecse428.group1.sportifybackend.model.FriendRequestStatus;

public class FriendRequestDto {

    private int id;
    private String message;
    private FriendRequestStatus status;
    private Date time;
    private MemberDto sender;
    private MemberDto receiver;
    
    
    public FriendRequestDto() {

    }

    public FriendRequestDto(int id, String message, FriendRequestStatus status, Date time, MemberDto sender, MemberDto receiver) {
        this.id = id;
        this.message = message;
        this.status = status;
        this.time = time;
        this.sender = sender;
        this.receiver = receiver;
    }

    public int getId() {
        return id;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String newMessage) {
        this.message = newMessage;
    }

    public FriendRequestStatus getStatus() {
        return status;
    }

    public void setStatus(FriendRequestStatus newStatus) {
        this.status = newStatus;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date newTime) {
        this.time = newTime;
    }

    public MemberDto getSender() {
        return sender;
    }

    public void setSender(MemberDto newSender) {
        this.sender = newSender;
    }

    public MemberDto getReceiver() {
        return receiver;
    }

    public void setReceiver(MemberDto newReceiver) {
        this.receiver = newReceiver;
    }
}
