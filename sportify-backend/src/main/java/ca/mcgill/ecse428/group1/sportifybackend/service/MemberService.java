package ca.mcgill.ecse428.group1.sportifybackend.service;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.group1.sportifybackend.dao.MemberRepository;
import ca.mcgill.ecse428.group1.sportifybackend.model.Member;

@Service
public class MemberService {
	@Autowired
	MemberRepository memberRepository;

	@Transactional
	public Member createMember(String username, String password, String email, String address)
			throws IllegalArgumentException {
		if (username == null || username.trim().length() == 0) {
			throw new IllegalArgumentException("Username cannot be empty!");
		}
		if (memberRepository.findByUsername(username) != null) {
			throw new IllegalArgumentException("Username is already taken!");
		}
		Member member = new Member();
		member.setUsername(username);
		setPassword(member, password);
		setEmail(member, email);
		setAddress(member, address);
		return memberRepository.save(member);
	}

	@Transactional
	public Member getMember(String username) throws IllegalArgumentException {
		if (username == null || username.trim().length() == 0) {
			throw new IllegalArgumentException("Username cannot be empty!");
		}
		Member member = memberRepository.findByUsername(username);
		if (member == null) {
			throw new IllegalArgumentException("Member does not exist!");
		}
		return member;
	}

	@Transactional
	public Member setMemberPassword(String username, String password) throws IllegalArgumentException {
		Member member = getMember(username);
		return setPassword(member, password);
	}

	@Transactional
	public Member setMemberEmail(String username, String email) throws IllegalArgumentException {
		Member member = getMember(username);
		return setEmail(member, email);
	}

	@Transactional
	public Member setMemberAddress(String username, String address) throws IllegalArgumentException {
		Member member = getMember(username);
		return setAddress(member, address);
	}

	@Transactional
	public void deleteMember(String username) throws IllegalArgumentException {
		Member member = getMember(username);
		memberRepository.delete(member);
	}

	@Transactional
	public List<Member> getAllMembers() {
		return memberRepository.findAllByOrderByUsername();
	}

	private Member setPassword(Member member, String password) throws IllegalArgumentException {
		if (password == null || password.trim().length() == 0) {
			throw new IllegalArgumentException("Password cannot be empty!");
		}
		member.setPassword(password);
		return memberRepository.save(member);
	}

	private Member setEmail(Member member, String email) throws IllegalArgumentException {
		if (email == null || email.trim().length() == 0) {
			throw new IllegalArgumentException("Email cannot be empty!");
		}
		if (!verifyEmail(email)) {
			throw new IllegalArgumentException("Email is invalid!");
		}
		member.setEmail(email);
		return memberRepository.save(member);
	}

	private Member setAddress(Member member, String address) throws IllegalArgumentException {
		if (address == null || address.trim().length() == 0) {
			throw new IllegalArgumentException("Address cannot be empty!");
		}
		member.setAddress(address);
		return memberRepository.save(member);
	}

	/**
	 * Used to match the email string to a regex which checks for proper email
	 * format. The restrictions for an email to be considered valid can be found
	 * <a href=
	 * "https://www.baeldung.com/java-email-validation-regex#strict-regular-expression-validation">here</a>
	 *
	 * @param email - the email string to be checked
	 * @return a boolean indicating whether the email conforms to standards or not.
	 *         True indicates that the email is valid.
	 */
	private boolean verifyEmail(String email) {
		return Pattern.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
				+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", email);
	}
}