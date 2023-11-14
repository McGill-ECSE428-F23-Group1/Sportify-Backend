package ca.mcgill.ecse428.group1.sportifybackend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.sql.Date;
import java.time.LocalDate;
import jakarta.persistence.ManyToOne;


@Entity
public class Message {

    @Id
    private String id;

    private String description;

    private LocalDate date;

    @ManyToOne(optional = false)
    private Chat chat;

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Chat getChat() { return chat; }

    public void setChat(Chat chat) { this.chat = chat; }

}