package ca.mcgill.ecse428.group1.sportifybackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Sport {
    @Id
    private String sportName;
    @OneToMany
    private Set<SpecificSport> specificSports;

    public Sport() {
        this.specificSports = new HashSet<>();
    }

    public String getSportName() {
        return sportName;
    }

    public Set<SpecificSport> getSpecificSports() {
        return specificSports;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public void setSpecificSports(Set<SpecificSport> specificSports) {
        this.specificSports = specificSports;
    }

    public boolean addSpecificSport(SpecificSport s) {
        return this.specificSports.add(s);
    }

    public boolean removeSpecificSport(SpecificSport s) {
        return this.specificSports.remove(s);
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
