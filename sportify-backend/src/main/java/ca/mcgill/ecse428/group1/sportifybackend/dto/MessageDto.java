package ca.mcgill.ecse428.group1.sportifybackend.dto;

import java.sql.Timestamp;

public class MessageDto {
    
    private int id;
    private String description;
    private Timestamp date;
    private int chatId;
    private MemberDto messageSender;
    private MemberDto messageReceiver;

    public MessageDto() {

    }

    public MessageDto(int id, String description, Timestamp date, int chatId, MemberDto messageSender, MemberDto messageReceiver) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.chatId = chatId;
        this.messageSender = messageSender;
        this.messageReceiver = messageReceiver;
    }

    public int getId() {
        return id;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp newDate) {
        this.date = newDate;
    }

    public int getChat() {
        return chatId;
    }

    public void setChat(int chatId) {
        this.chatId = chatId;
    }

    public MemberDto getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(MemberDto newMessageSender) {
        this.messageSender = newMessageSender;
    }

    public MemberDto getMessageReceiver() {
        return messageReceiver;
    }

    public void setMessageReceiver(MemberDto newMessageReceiver) {
        this.messageReceiver = newMessageReceiver;
    }

    
}
