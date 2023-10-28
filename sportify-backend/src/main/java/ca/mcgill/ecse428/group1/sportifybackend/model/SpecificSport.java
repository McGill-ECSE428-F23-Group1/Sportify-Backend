package ca.mcgill.ecse428.group1.sportifybackend.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class SpecificSport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Member member;

    @ManyToOne
    private Sport sport;

    private SportLevel sportLevel;

    public SpecificSport() {}

    public long getId() {
        return id;
    }

    public Member getMember() {
        return member;
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

    public void setMember(Member member) {
        this.member = member;
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
        return Objects.equals(member.getUsername(), other.getMember().getUsername()) &&
                Objects.equals(sport.getSportName(), other.getSport().getSportName());
    }
}
