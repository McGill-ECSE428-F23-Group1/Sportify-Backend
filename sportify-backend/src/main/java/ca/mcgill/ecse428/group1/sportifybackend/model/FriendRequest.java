package ca.mcgill.ecse428.group1.sportifybackend.model;

import java.sql.Time;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class FriendRequest {

    @Id
    private int id;
    // private String sender;
    // private String receiver;
    // private Time time;

    // write getter and setter
    public int getId() {
        return id;
    }

    // public Time getTime() {
    //     return time;
    // }

    public void setId(int id) {
        this.id = id;
    }

    // public void setTime(Time time) {
    //     this.time = time;
    // }
    
}
