package ca.mcgill.ecse428.group1.sportifybackend.dto;

public class MemberDto {

	private String username;
	private String password;
	private String gender;
	private String email;
	private String address;

	public MemberDto(String username, String password, String gender, String email, String address) {
		super();
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.email = email;
		this.address = address;
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

}
