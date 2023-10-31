package ca.mcgill.ecse428.group1.sportifybackend.controller;

import ca.mcgill.ecse428.group1.sportifybackend.dto.SpecificSportDto;
import ca.mcgill.ecse428.group1.sportifybackend.dto.SportDto;
import ca.mcgill.ecse428.group1.sportifybackend.model.SpecificSport;
import ca.mcgill.ecse428.group1.sportifybackend.model.Sport;
import ca.mcgill.ecse428.group1.sportifybackend.service.SpecificSportService;
import ca.mcgill.ecse428.group1.sportifybackend.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class SportController {
    @Autowired
    private SportService sportService;
    @Autowired
    private SpecificSportService specificSportService;

    @GetMapping(value = { "/sport", "/sport/" })
    public List<SportDto> getAllSports() {
        return sportService.getAllSports().stream().map(p -> convertToSportDto(p)).collect(Collectors.toList());
    }

    @GetMapping(value = { "/sport/{sportname}", "/sport/{sportname}/" })
    public List<SpecificSportDto> getSportMembers(@PathVariable("sportname") String sportName) throws IllegalArgumentException {
        return specificSportService.getSpecificSportsBySport(sportName).stream().map(p -> convertToSpecificSportDto(p)).collect(Collectors.toList());
    }

    @PostMapping(value = { "/sport/{sportname}", "/sport/{sportname}/" })
    public SportDto createSport(@PathVariable("sportname") String sportName) throws IllegalArgumentException {
        return convertToSportDto(sportService.createSport(sportName));
    }

//    @PatchMapping(value = { "/sport/{sportname}", "/sport/{sportname}/" })
//    public SportDto updateSport(@PathVariable("sportname") String sportName, @RequestParam String newName) throws IllegalArgumentException {
//        return convertToDto(sportService.setSportName(sportName, newName));
//    }

    @DeleteMapping(value = { "/sport/{sportname}", "/sport/{sportname}/" })
    public List<SportDto> deleteSport(@PathVariable("sportname") String sportName) throws IllegalArgumentException {
        sportService.deleteSport(sportName);
        return sportService.getAllSports().stream().map(p -> convertToSportDto(p)).collect(Collectors.toList());
    }

    private SportDto convertToSportDto(Sport s) throws IllegalArgumentException {
        if (s == null) {
            throw new IllegalArgumentException("Sport does not exist!");
        }

        return new SportDto(s.getSportName());
    }

    private SpecificSportDto convertToSpecificSportDto(SpecificSport ss) throws IllegalArgumentException {
        if (ss == null) {
            throw new IllegalArgumentException("Specific sport does not exist!");
        }

        return new SpecificSportDto(ss.getId(), ss.getSport().getSportName(), ss.getSportLevel().toString());
    }
}
