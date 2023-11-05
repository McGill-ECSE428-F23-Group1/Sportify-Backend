package ca.mcgill.ecse428.group1.sportifybackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.sql.Date;

@Entity
public class FriendRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int id;
    private String message;
    private FriendRequestStatus status;
    private Date time;
    @ManyToOne(optional = false)
    private Member sender;
    @ManyToOne(optional = false)
    private Member receiver;

    // write getter and setter
    public int getId() {
        return id;
    }

    public Date getTime() {
        return time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Member getSender() {
        return sender;
    }
    
    public Member getReceiver() {
        return receiver;
    }

    public void setSender(Member sender) {
        this.sender = sender;
    }

    public void setReceiver(Member receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public FriendRequestStatus getStatus() {
        return status;
    }

    public void setMessage(String newMessage) {
        this.message = newMessage;
    }

    public void setStatus(FriendRequestStatus newStatus) {
        this.status = newStatus;
    }    
}
