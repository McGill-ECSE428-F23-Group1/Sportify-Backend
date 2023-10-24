package ca.mcgill.ecse428.group1.sportifybackend.dto;

public class FriendRequestDto {

    private int id;
    
    
    public FriendRequestDto() {

    }

    public FriendRequestDto(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int newId) {
        this.id = newId;
    }
}
