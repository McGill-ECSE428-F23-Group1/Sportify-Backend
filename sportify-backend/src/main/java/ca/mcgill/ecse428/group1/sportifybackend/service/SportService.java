package ca.mcgill.ecse428.group1.sportifybackend.service;

import ca.mcgill.ecse428.group1.sportifybackend.dao.MemberRepository;
import ca.mcgill.ecse428.group1.sportifybackend.dao.SpecificSportRepository;
import ca.mcgill.ecse428.group1.sportifybackend.dao.SportRepository;
import ca.mcgill.ecse428.group1.sportifybackend.model.Member;
import ca.mcgill.ecse428.group1.sportifybackend.model.SpecificSport;
import ca.mcgill.ecse428.group1.sportifybackend.model.Sport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SportService {
    @Autowired
    SportRepository sportRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    SpecificSportRepository specificSportRepository;

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
    public List<Sport> getAllSports() {
        return sportRepository.findAllByOrderBySportName();
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

//    @Transactional
//    public Sport setSportName(String oldName, String newName) throws IllegalArgumentException {
//        if (oldName == null || oldName.trim().length() == 0) {
//            throw new IllegalArgumentException("Sport does not exist!");
//        }
//        Sport sport = sportRepository.findBySportName(oldName);
//        if (sport == null) {
//            throw new IllegalArgumentException("Sport does not exist!");
//        }
//        sport.setSportName(newName);
//        return sportRepository.save(sport);
//    }

    @Transactional
    public void deleteSport(String sportName) throws IllegalArgumentException {
        Sport sport = getSport(sportName);

//        for (Member m: memberRepository.findAllByOrderByUsername()) {
//            for (SpecificSport ss: m.getSports()) {
//                if (ss.getSport().equals(sport)) {
//                    m.removeSport(ss);
//                    memberRepository.save(m);
//                }
//            }
//        }

        List<SpecificSport> sports = specificSportRepository.findBySport(sport);

        for (SpecificSport ss: sports) {
            Member m = ss.getMember();
            m.removeSport(ss);
            memberRepository.save(m);
            specificSportRepository.delete(ss);
        }

        sportRepository.delete(sport);
    }
}
