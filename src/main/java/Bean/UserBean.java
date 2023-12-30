package Bean;

import java.io.Serializable;

public class UserBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String user_ID;
	private String username;
	private String password;
	private UserRole role;
	private String email;
	private String phone_number;
	private Boolean active;

	// Constructors
	public UserBean() {
		// Default constructor
	}

	public UserBean(String user_ID, String username, String password, UserRole role, String email, String phone_number,
			Boolean active) {
		this.user_ID = user_ID;
		this.username = username;
		this.password = password;
		this.role = role;
		this.email = email;
		this.phone_number = phone_number;
		this.active = active;
	}

	// Getters and Setters
	public String getUser_ID() {
		return user_ID;
	}

	public void setUser_ID(String user_ID) {
		this.user_ID = user_ID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	// Enum for User Roles
	public enum UserRole {
		student, ctsv_staff, system_admin
	}

	// toString method for debugging/logging
	@Override
	public String toString() {
		return "User{" + "user_ID='" + user_ID + '\'' + ", username='" + username + '\'' + ", password='" + password
				+ '\'' + ", role=" + role + ", email='" + email + '\'' + ", phone_number='" + phone_number + '\''
				+ ", active='" + active + '\'' + '}';
	}
}
