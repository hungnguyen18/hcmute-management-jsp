package Bean;

import java.io.Serializable;

public class CTSVStaffBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String ctsvStaffID;
	private String staffName;
	private String email;
	private String userID;

	// Additional properties for User
	private String username;
	private String password;
	private String role;

	// Additional property for CTSVStaff
	private String phoneNumber;

	// Constructors, getters, and setters

	public CTSVStaffBean() {
	}

	public CTSVStaffBean(String ctsvStaffID, String staffName, String email, String userID, String username,
			String password, String role, String phoneNumber) {
		this.ctsvStaffID = ctsvStaffID;
		this.staffName = staffName;
		this.email = email;
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.role = role;
		this.phoneNumber = phoneNumber;
	}

	// Getters and Setters for User properties
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	// Getters and Setters for CTSVStaff properties
	public String getCtsvStaffID() {
		return ctsvStaffID;
	}

	public void setCtsvStaffID(String ctsvStaffID) {
		this.ctsvStaffID = ctsvStaffID;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	// Getter and Setter for phoneNumber
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
