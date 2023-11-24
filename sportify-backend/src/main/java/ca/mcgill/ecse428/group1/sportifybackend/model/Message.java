package ca.mcgill.ecse428.group1.sportifybackend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.sql.Timestamp;

import jakarta.persistence.ManyToOne;


@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    private Timestamp date;

    @ManyToOne(optional = false)
    private Chat chat;

    @ManyToOne(optional = false)
    private Member messageSender;

    @ManyToOne(optional = false)
    private Member messageReceiver;

    public Message() {
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getDate() {
        return date;
    }

    public Member getMessageSender() {
        return messageSender;
    }

    public Member getMessageReceiver() {
        return messageReceiver;
    }

    public void setMessageSender(Member messageSender) {
        this.messageSender = messageSender;
    }

    public void setMessageReceiver(Member messageReceiver) {
        this.messageReceiver = messageReceiver;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Chat getChat() { return chat; }

    public void setChat(Chat chat) { this.chat = chat; }

}