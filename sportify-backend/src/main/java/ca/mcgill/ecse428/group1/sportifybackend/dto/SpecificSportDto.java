package ca.mcgill.ecse428.group1.sportifybackend.dto;

public class SpecificSportDto {
    private long id;
    private String username;
    private String sportName;
    private String sportLevel;

    public SpecificSportDto(long id, String username, String sportName, String sportLevel) {
        this.id = id;
        this.username = username;
        this.sportName = sportName;
        this.sportLevel = sportLevel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getSportLevel() {
        return sportLevel;
    }

    public void setSportLevel(String sportLevel) {
        this.sportLevel = sportLevel;
    }
}
