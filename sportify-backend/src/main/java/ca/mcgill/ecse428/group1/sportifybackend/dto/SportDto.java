package ca.mcgill.ecse428.group1.sportifybackend.dto;

public class SportDto {
    private String sportName;

    public SportDto(String sportName) {
        this.sportName = sportName;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }
}
