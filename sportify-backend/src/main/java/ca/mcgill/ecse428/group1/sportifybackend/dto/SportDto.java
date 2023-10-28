package ca.mcgill.ecse428.group1.sportifybackend.dto;

import java.util.List;

public class SportDto {
    private String sportName;
    private List<String> members;   // usernames of members that play this sport

    public SportDto(String sportName, List<String> members) {
        this.sportName = sportName;
        this.members = members;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }
}
