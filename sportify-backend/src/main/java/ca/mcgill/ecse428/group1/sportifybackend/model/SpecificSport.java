package ca.mcgill.ecse428.group1.sportifybackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Entity
public class SpecificSport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Sport sport;

    private SportLevel sportLevel;

    public long getId() {
        return id;
    }

    public Sport getSport() {
        return sport;
    }

    public SportLevel getSportLevel() {
        return sportLevel;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public void setSportLevel(SportLevel sportLevel) {
        this.sportLevel = sportLevel;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SpecificSport other = (SpecificSport) obj;
        return Objects.equals(id, other.getId());
    }
}
