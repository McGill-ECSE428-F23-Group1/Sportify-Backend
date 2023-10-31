package ca.mcgill.ecse428.group1.sportifybackend.controller;

import ca.mcgill.ecse428.group1.sportifybackend.dto.SpecificSportDto;
import ca.mcgill.ecse428.group1.sportifybackend.model.SpecificSport;
import ca.mcgill.ecse428.group1.sportifybackend.model.SportLevel;
import ca.mcgill.ecse428.group1.sportifybackend.service.SpecificSportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class SpecificSportController {
    @Autowired
    private SpecificSportService specificSportService;

    @GetMapping(value = { "/membersport/{username}", "/membersport/{username}/" })
    public List<SpecificSportDto> getSportsByMember(@PathVariable("username") String username) throws IllegalArgumentException {
        return specificSportService.getSpecificSportsByMember(username).stream().map(ss -> convertToDto(ss)).
                collect(Collectors.toList());
    }

    @PostMapping(value = { "/membersport/{username}", "/membersport/{username}/" })
    public SpecificSportDto addSport(@PathVariable("username") String username, @RequestParam String sportName, @RequestParam String sportLevel)
            throws IllegalArgumentException {
        SportLevel sl = Arrays.stream(SportLevel.values()).filter(e -> e.name().equalsIgnoreCase(sportLevel)).
                findAny().orElse(null);
        if (sl == null) {
            throw new IllegalArgumentException("Sport level cannot be empty!");
        }
        SpecificSport ss = specificSportService.addSpecificSport(username, sportName, sl);
        return convertToDto(ss);
    }

    @PatchMapping(value = { "/membersport/{username}", "/membersport/{username}/" })
    public SpecificSportDto modifySport(@PathVariable("username") String username, @RequestParam String sportName, @RequestParam String sportLevel)
            throws IllegalArgumentException {
        SportLevel sl = Arrays.stream(SportLevel.values()).filter(e -> e.name().equalsIgnoreCase(sportLevel)).
                findAny().orElse(null);
        if (sl == null) {
            throw new IllegalArgumentException("Sport level cannot be empty!");
        }
        SpecificSport ss = specificSportService.setSportLevel(username, sportName, sl);
        return convertToDto(ss);
    }

    @DeleteMapping(value = { "/membersport/{id}", "/membersport/{id}/" })
    public void deleteSport(@PathVariable("id") long id) throws IllegalArgumentException {
        specificSportService.deleteSpecificSport(id);
    }

    private SpecificSportDto convertToDto(SpecificSport ss) throws IllegalArgumentException {
        if (ss == null) {
            throw new IllegalArgumentException("Specific sport does not exist!");
        }

        return new SpecificSportDto(ss.getId(), ss.getSport().getSportName(), ss.getSportLevel().toString());
    }
}
