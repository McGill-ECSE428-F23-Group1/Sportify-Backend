package ca.mcgill.ecse428.group1.sportifybackend.dto;

import java.time.LocalDate;

public class MessageDto {

    private String id;

    private String description;

    private LocalDate date;

    private ChatDto chat;

    public MessageDto{

    }

    public MessageDto(String id, String description, LocalDate date, ChatDto chat){
        this.id = id;
        this.description = description;
        this.date = date;
        this.chat = chat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ChatDto getChat() {
        return chat;
    }

    public void setChat(ChatDto chat) {
        this.chat = chat;
    }
}