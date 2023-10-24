package ca.mcgill.ecse428.group1.sportifybackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse428.group1.sportifybackend.dto.FriendRequestDto;
import ca.mcgill.ecse428.group1.sportifybackend.service.FriendRequestService;

@CrossOrigin(origins = "*")
@RestController
public class FriendRequestController {

    @Autowired
    private FriendRequestService friendRequestService;

    @PostMapping(value = { "/friendRequest/{id}", "/friendRequest/{id}/" })
    public FriendRequestDto createFriendRequest(@PathVariable("id") int id) {
        friendRequestService.createFriendRequest(id);
        return new FriendRequestDto(id);
    }

    
}
