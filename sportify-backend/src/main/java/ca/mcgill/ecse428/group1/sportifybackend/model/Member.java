package ca.mcgill.ecse428.group1.sportifybackend.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Member {
	@Id
	private String username;
	private String password;
	private Gender gender;
	private String email;
	private String address;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Member> friends;

	public Member() {
		this.friends = new HashSet<>();
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Gender getGender() {
		return gender;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public Set<Member> getFriends() {
		return this.friends;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setFriends(Set<Member> set) {
		this.friends = set;
	}

	public boolean addFriend(Member m) {
		return this.friends.add(m);
	}

	public boolean removeFriend(Member m) {
		return this.friends.remove(m);
	}

	@Override
	public int hashCode() {
		return Objects.hash(username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return Objects.equals(username, other.username);
	}

}
