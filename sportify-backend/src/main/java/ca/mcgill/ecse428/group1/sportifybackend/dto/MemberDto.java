package ca.mcgill.ecse428.group1.sportifybackend.dto;

import java.util.List;

public class MemberDto {

	private String username;
	private String password;
	private String gender;
	private String email;
	private String address;
	private List<String> friends; // to prevent infinite loop when fetching, only friend username is kept in Dto
	private List<SpecificSportDto> sports;	// list of names of sports the member plays

	public MemberDto(String username, String password, String gender, String email, String address,
			List<String> friends, List<SpecificSportDto> sports) {
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.email = email;
		this.address = address;
		this.friends = friends;
		this.sports = sports;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getGender() {
		return gender;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public List<String> getFriends() {
		return friends;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setFriends(List<String> friends) {
		this.friends = friends;
	}

	public List<SpecificSportDto> getSports() {
		return sports;
	}

	public void setSports(List<SpecificSportDto> sports) {
		this.sports = sports;
	}
}
