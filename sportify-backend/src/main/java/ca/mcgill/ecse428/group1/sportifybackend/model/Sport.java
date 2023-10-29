package ca.mcgill.ecse428.group1.sportifybackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Sport {
    @Id
    private String sportName;

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sportName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Sport other = (Sport) obj;
        return Objects.equals(sportName, other.getSportName());
    }
}
