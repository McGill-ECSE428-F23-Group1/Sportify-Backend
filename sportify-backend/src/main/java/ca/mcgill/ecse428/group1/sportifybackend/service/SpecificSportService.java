package ca.mcgill.ecse428.group1.sportifybackend.service;

import ca.mcgill.ecse428.group1.sportifybackend.dao.MemberRepository;
import ca.mcgill.ecse428.group1.sportifybackend.dao.SpecificSportRepository;
import ca.mcgill.ecse428.group1.sportifybackend.dao.SportRepository;
import ca.mcgill.ecse428.group1.sportifybackend.model.Member;
import ca.mcgill.ecse428.group1.sportifybackend.model.SpecificSport;
import ca.mcgill.ecse428.group1.sportifybackend.model.Sport;
import ca.mcgill.ecse428.group1.sportifybackend.model.SportLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SpecificSportService {
    @Autowired
    SpecificSportRepository specificSportRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    SportRepository sportRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    SportService sportService;

    @Transactional
    public SpecificSport getSpecificSport(String username, String sportName) throws IllegalArgumentException {
        if (username == null || username.trim().length() == 0) {
            throw new IllegalArgumentException("Username cannot be empty!");
        }
        if (sportName == null || sportName.trim().length() == 0) {
            throw new IllegalArgumentException("Sport name cannot be empty!");
        }
        Sport sport = sportService.getSport(sportName);

        for (SpecificSport ss: memberService.getSpecificSports(username)) {
            if (ss.getSport() == sport) {
                return ss;
            }
        }

        throw new IllegalArgumentException("Member does not play the sport!");
    }

    @Transactional
    public SpecificSport addSpecificSport(String username, String sportName, SportLevel sportLevel) throws IllegalArgumentException {
        if (username == null || username.trim().length() == 0) {
            throw new IllegalArgumentException("Username cannot be empty!");
        }
        if (sportName == null || sportName.trim().length() == 0) {
            throw new IllegalArgumentException("Sport name cannot be empty!");
        }
        if (sportLevel == null) {
            throw new IllegalArgumentException("Sport level cannot be empty!");
        }
        Member member = memberService.getMember(username);
        Sport sport = sportService.getSport(sportName);

        for (SpecificSport ss: member.getSports()) {
            if (ss.getSport() == sport) {
                throw new IllegalArgumentException("Member already plays the sport!");
            }
        }

        SpecificSport specificSport = new SpecificSport();
        specificSport.setMember(member);
        specificSport.setSport(sport);
        specificSport.setSportLevel(sportLevel);
        specificSportRepository.save(specificSport);

        member.addSport(specificSport);
        memberRepository.save(member);
        sport.addSpecificSport(specificSport);
        sportRepository.save(sport);

        return specificSport;
    }

    @Transactional
    public SpecificSport setSportLevel(String username, String sportName, SportLevel sportLevel) throws IllegalArgumentException {
        if (username == null || username.trim().length() == 0) {
            throw new IllegalArgumentException("Username cannot be empty!");
        }
        if (sportName == null || sportName.trim().length() == 0) {
            throw new IllegalArgumentException("Sport name cannot be empty!");
        }
        if (sportLevel == null) {
            throw new IllegalArgumentException("Sport level cannot be empty!");
        }
        Member member = memberService.getMember(username);
        Sport sport = sportService.getSport(sportName);

        for (SpecificSport ss: member.getSports()) {
            if (ss.getSport() == sport) {
                ss.setSportLevel(sportLevel);
                return specificSportRepository.save(ss);
            }
        }

        throw new IllegalArgumentException("Member does not play the sport!");
    }

    @Transactional
    public void deleteSpecificSport(String username, String sportName) throws IllegalArgumentException {
        if (username == null || username.trim().length() == 0) {
            throw new IllegalArgumentException("Username cannot be empty!");
        }
        if (sportName == null || sportName.trim().length() == 0) {
            throw new IllegalArgumentException("Sport name cannot be empty!");
        }
        SpecificSport specificSport = getSpecificSport(username, sportName);

        memberService.directDeleteSpecificSport(username, specificSport);
        sportService.directDeleteSpecificSport(sportName, specificSport);
        specificSportRepository.delete(specificSport);
    }

    @Transactional
    public void directDeleteSpecificSport(SpecificSport specificSport) throws IllegalArgumentException {
        specificSportRepository.delete(specificSport);
    }
}
