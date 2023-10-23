package ca.mcgill.ecse428.group1.sportifybackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Member {
	@Id
	private String username;
	private String password;
	private Gender gender;
	private String email;
	private String address;

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

}
