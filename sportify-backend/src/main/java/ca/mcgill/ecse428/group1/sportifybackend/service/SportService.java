package ca.mcgill.ecse428.group1.sportifybackend.service;

import ca.mcgill.ecse428.group1.sportifybackend.dao.SportRepository;
import ca.mcgill.ecse428.group1.sportifybackend.model.SpecificSport;
import ca.mcgill.ecse428.group1.sportifybackend.model.Sport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class SportService {
    @Autowired
    SportRepository sportRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    SpecificSportService specificSportService;

    @Transactional
    public Sport getSport(String sportName) throws IllegalArgumentException {
        if (sportName == null || sportName.trim().length() == 0) {
            throw new IllegalArgumentException("Sport name cannot be empty!");
        }
        Sport sport = sportRepository.findBySportName(sportName);
        if (sport == null) {
            throw new IllegalArgumentException("Sport does not exist!");
        }
        return sport;
    }

    @Transactional
    public Sport createSport(String sportName) throws IllegalArgumentException {
        if (sportName == null || sportName.trim().length() == 0) {
            throw new IllegalArgumentException("Sport name cannot be empty!");
        }
        if (sportRepository.findBySportName(sportName) != null) {
            throw new IllegalArgumentException("Sport with the given name already exists!");
        }
        Sport sport = new Sport();
        sport.setSportName(sportName);
        return sportRepository.save(sport);
    }

    @Transactional
    public Sport setSportName(String oldName, String newName) throws IllegalArgumentException {
        if (oldName == null || oldName.trim().length() == 0) {
            throw new IllegalArgumentException("Sport does not exist!");
        }
        Sport sport = sportRepository.findBySportName(oldName);
        if (sport == null) {
            throw new IllegalArgumentException("Sport does not exist!");
        }
        sport.setSportName(newName);
        return sportRepository.save(sport);
    }

    @Transactional
    public Set<SpecificSport> getSpecificSports(String sportName) throws IllegalArgumentException {
        return getSport(sportName).getSpecificSports();
    }

    @Transactional
    public Sport directDeleteSpecificSport(String sportName, SpecificSport specificSport) throws IllegalArgumentException {
        Sport sport = getSport(sportName);
        sport.removeSpecificSport(specificSport);
        return sportRepository.save(sport);
    }

    @Transactional
    public void deleteSport(String sportName) throws IllegalArgumentException {
        // remove all instances of specificsport from members
        Sport sport = getSport(sportName);

        for (SpecificSport ss: sport.getSpecificSports()) {
            memberService.directDeleteSpecificSport(ss.getMember().getUsername(), ss);
            sport.removeSpecificSport(ss);
            specificSportService.directDeleteSpecificSport(ss);
        }

        sportRepository.delete(sport);
    }
}
