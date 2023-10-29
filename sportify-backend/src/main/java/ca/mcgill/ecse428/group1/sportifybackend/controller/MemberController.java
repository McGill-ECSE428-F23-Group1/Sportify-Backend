package ca.mcgill.ecse428.group1.sportifybackend.controller;

import ca.mcgill.ecse428.group1.sportifybackend.dto.MemberDto;
import ca.mcgill.ecse428.group1.sportifybackend.dto.SpecificSportDto;
import ca.mcgill.ecse428.group1.sportifybackend.model.Gender;
import ca.mcgill.ecse428.group1.sportifybackend.model.Member;
import ca.mcgill.ecse428.group1.sportifybackend.model.SpecificSport;
import ca.mcgill.ecse428.group1.sportifybackend.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class MemberController {
	@Autowired
	private MemberService service;

	@GetMapping(value = { "/member", "/member/" })
	public List<MemberDto> getAllMembers() {
		return service.getAllMembers().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
	}

	@PostMapping(value = { "/member/{username}", "/member/{username}/" })
	public MemberDto createMember(@PathVariable("username") String username, @RequestParam String password,
			@RequestParam Optional<Gender> gender, @RequestParam Optional<String> email,
			@RequestParam Optional<String> address) throws IllegalArgumentException {
		Member member = service.createMember(username, password);
		if (gender.isPresent()) {
			service.setMemberGender(username, gender.get());
		}
		if (email.isPresent()) {
			service.setMemberEmail(username, email.get());
		}
		if (address.isPresent()) {
			service.setMemberAddress(username, address.get());
		}
		return convertToDto(member);
	}

	@GetMapping(value = { "/member/{username}", "/member/{username}/" })
	public MemberDto getMember(@PathVariable("username") String username) throws IllegalArgumentException {
		return convertToDto(service.getMember(username));
	}

	@GetMapping(value = { "/memberlogin/{username}", "/memberlogin/{username}/" })
	public MemberDto verifyLogin(@PathVariable("username") String username, @RequestParam String password)
			throws IllegalArgumentException {
		return convertToDto(service.verifyLogin(username, password));
	}

	@DeleteMapping(value = { "/member/{username}", "/member/{username}/" })
	public void deleteMember(@PathVariable("username") String username) throws IllegalArgumentException {
		service.deleteMember(username);
	}

	@PatchMapping(value = { "/member/{username}", "/member/{username}/" })
	public MemberDto updateMember(@PathVariable("username") String username, @RequestParam Optional<String> email,
			@RequestParam Optional<String> password, @RequestParam Optional<String> address,
			@RequestParam Optional<Gender> gender) throws IllegalArgumentException {
		Member Member = service.getMember(username);
		if (email.isPresent()) {
			Member = service.setMemberEmail(username, email.get());
		}
		if (password.isPresent()) {
			Member = service.setMemberPassword(username, password.get());
		}
		if (address.isPresent()) {
			Member = service.setMemberAddress(username, address.get());
		}
		if (gender.isPresent()) {
			Member = service.setMemberGender(username, gender.get());
		}
		return convertToDto(Member);
	}

	@GetMapping(value = { "/memberfriend", "/memberfriend/" })
	public boolean areFriends(@RequestParam String username1, @RequestParam String username2)
			throws IllegalArgumentException {
		return service.verifyFriendStatus(username1, username2);
	}

	@PostMapping(value = { "/memberfriend", "/memberfriend/" })
	public void addFriend(@RequestParam String username1, @RequestParam String username2)
			throws IllegalArgumentException {
		service.addFriend(username1, username2);
	}

	@DeleteMapping(value = { "/memberfriend", "/memberfriend/" })
	public void removeFriend(@RequestParam String username1, @RequestParam String username2)
			throws IllegalArgumentException {
		service.removeFriend(username1, username2);
	}

	private MemberDto convertToDto(Member m) throws IllegalArgumentException {
		if (m == null) {
			throw new IllegalArgumentException("Member does not exist!");
		}
		// parse friends
		List<String> friends = new ArrayList<>();
		for (Member friend : m.getFriends()) {
			friends.add(friend.getUsername());
		}
		List<SpecificSportDto> sports = new ArrayList<>();
		for (SpecificSport ss: m.getSports()) {
			sports.add(new SpecificSportDto(ss.getId(), ss.getMember().getUsername(), ss.getSport().getSportName(),
					ss.getSportLevel().toString()));
		}
		// build Dto
		MemberDto memberDto = new MemberDto(m.getUsername(), m.getPassword(), null, m.getEmail(), m.getAddress(),
				friends, sports);
		// parse gender enum
		if (m.getGender() != null) {
			memberDto.setGender(m.getGender().toString());
		}
		return memberDto;
	}
}
