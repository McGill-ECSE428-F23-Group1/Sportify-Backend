package ca.mcgill.ecse428.group1.sportifybackend.controller;

import ca.mcgill.ecse428.group1.sportifybackend.dto.SportDto;
import ca.mcgill.ecse428.group1.sportifybackend.model.SpecificSport;
import ca.mcgill.ecse428.group1.sportifybackend.model.Sport;
import ca.mcgill.ecse428.group1.sportifybackend.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class SportController {
    @Autowired
    private SportService sportService;

    @GetMapping(value = { "/sport", "/sport/" })
    public List<SportDto> getAllSports() {
        return sportService.getAllSports().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
    }

    @GetMapping(value = { "/sport/{sportname}", "/sport/{sportname}/" })
    public SportDto getSport(@PathVariable("sportname") String sportName) throws IllegalArgumentException {
        return convertToDto(sportService.getSport(sportName));
    }

    @PostMapping(value = { "/sport/{sportname}", "/sport/{sportname}/" })
    public SportDto createSport(@PathVariable("sportname") String sportName) throws IllegalArgumentException {
        return convertToDto(sportService.createSport(sportName));
    }

    @PatchMapping(value = { "/sport/{sportname}", "/sport/{sportname}/" })
    public SportDto updateSport(@PathVariable("sportname") String sportName, @RequestParam String newName) throws IllegalArgumentException {
        return convertToDto(sportService.setSportName(sportName, newName));
    }

    @DeleteMapping(value = { "/sport/{sportname}", "/sport/{sportname}/" })
    public void deleteSport(@PathVariable("sportname") String sportName) throws IllegalArgumentException {
        sportService.deleteSport(sportName);
    }

    private SportDto convertToDto(Sport s) throws IllegalArgumentException {
        if (s == null) {
            throw new IllegalArgumentException("Sport does not exist!");
        }

        List<String> members = new ArrayList<>();
        for (SpecificSport ss: s.getSpecificSports()) {
            members.add(ss.getMember().getUsername());
        }

        return new SportDto(s.getSportName(), members);
    }
}
