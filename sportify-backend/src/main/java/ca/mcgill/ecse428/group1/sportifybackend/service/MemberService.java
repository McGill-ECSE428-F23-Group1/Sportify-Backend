package ca.mcgill.ecse428.group1.sportifybackend.service;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.group1.sportifybackend.dao.MemberRepository;
import ca.mcgill.ecse428.group1.sportifybackend.model.Gender;
import ca.mcgill.ecse428.group1.sportifybackend.model.Member;

@Service
public class MemberService {
	@Autowired
	MemberRepository memberRepository;

	@Transactional
	public Member createMember(String username, String password) throws IllegalArgumentException {
		if (username == null || username.trim().length() == 0) {
			throw new IllegalArgumentException("Username cannot be empty!");
		}
		if (memberRepository.findByUsername(username) != null) {
			throw new IllegalArgumentException("Username is already taken!");
		}
		Member member = new Member();
		member.setUsername(username);
		setPassword(member, password);
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
	public Member verifyLogin(String username, String password) throws IllegalArgumentException {
		if (username == null || username.trim().length() == 0) {
			throw new IllegalArgumentException("Username cannot be empty!");
		}
		Member member = memberRepository.findByUsername(username);
		if (member == null) {
			throw new IllegalArgumentException("Member does not exist!");
		}
		if (!password.equals(member.getPassword())) {
			throw new IllegalArgumentException("Wrong password!");
		}
		return member;
	}

	@Transactional
	public boolean verifyFriendStatus(String username1, String username2) throws IllegalArgumentException {
		Member x = getMember(username1);
		Member y = getMember(username2);
		return areFriends(x, y);
	}

	@Transactional
	public Member setMemberGender(String username, Gender gender) throws IllegalArgumentException {
		Member member = getMember(username);
		return setGender(member, gender);
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
		// remove friends foreign key constraint
		for (Member x : member.getFriends()) {
			x.removeFriend(member);
			memberRepository.save(x);
		}
		memberRepository.delete(member);
	}

	@Transactional
	public List<Member> getAllMembers() {
		return memberRepository.findAllByOrderByUsername();
	}

	@Transactional
	public void addFriend(String username1, String username2) throws IllegalArgumentException {
		Member x = getMember(username1);
		Member y = getMember(username2);
		if (x.equals(y)) {
			throw new IllegalArgumentException("Cannot add oneself as friend!");
		}
		if (areFriends(x, y)) {
			throw new IllegalArgumentException("Members are already friends!");
		}
		x.addFriend(y);
		y.addFriend(x);
		memberRepository.save(x);
		memberRepository.save(y);
	}

	@Transactional
	public void removeFriend(String username1, String username2) throws IllegalArgumentException {
		Member x = getMember(username1);
		Member y = getMember(username2);
		if (!areFriends(x, y)) {
			throw new IllegalArgumentException("Members are not friends!");
		}
		x.removeFriend(y);
		y.removeFriend(x);
		memberRepository.save(x);
		memberRepository.save(y);
	}

	private Member setGender(Member member, Gender gender) throws IllegalArgumentException {
		member.setGender(gender);
		return memberRepository.save(member);
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

	private boolean areFriends(Member x, Member y) throws IllegalArgumentException {
		return x.getFriends().contains(y);
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
