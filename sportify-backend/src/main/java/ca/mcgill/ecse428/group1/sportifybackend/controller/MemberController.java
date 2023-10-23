package ca.mcgill.ecse428.group1.sportifybackend.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse428.group1.sportifybackend.dto.MemberDto;
import ca.mcgill.ecse428.group1.sportifybackend.model.Member;
import ca.mcgill.ecse428.group1.sportifybackend.service.MemberService;

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
			@RequestParam String email, @RequestParam String address) throws IllegalArgumentException {
		Member member = service.createMember(username, password, email, address);
		return convertToDto(member);
	}

	@GetMapping(value = { "/member/{username}", "/member/{username}/" })
	public MemberDto getMember(@PathVariable("username") String username) throws IllegalArgumentException {
		return convertToDto(service.getMember(username));
	}

	@DeleteMapping(value = { "/member/{username}", "/member/{username}/" })
	public void deleteMember(@PathVariable("username") String username) throws IllegalArgumentException {
		service.deleteMember(username);
	}

	@PatchMapping(value = { "/member/{username}", "/member/{username}/" })
	public MemberDto updateMember(@PathVariable("username") String username, @RequestParam Optional<String> email,
			@RequestParam Optional<String> password, @RequestParam Optional<String> address)
			throws IllegalArgumentException {
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
		return convertToDto(Member);
	}

	private MemberDto convertToDto(Member m) throws IllegalArgumentException {
		if (m == null) {
			throw new IllegalArgumentException("Member does not exist!");
		}
		MemberDto memberDto = new MemberDto(m.getUsername(), m.getPassword(), m.getEmail(), m.getAddress());
		return memberDto;
	}
}