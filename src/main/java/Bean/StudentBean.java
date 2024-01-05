package Bean;

import java.io.Serializable;

public class StudentBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String studentID;
	private String mssv;
	private String studentName;
	private String birthdate;
	private String gender;
	private String department;
	private String majorID;
	private String batchID;
	private String educationSystem;
	private String email;
	private String phoneNumber;
	private String address;
	private String userID;

	// Additional properties for User
	private String username;
	private String password;
	private String role;

	// Constructors, getters, and setters

	public StudentBean() {
	}

	public StudentBean(String studentID, String mssv, String studentName, String birthdate, String gender,
			String department, String majorID, String batchID, String educationSystem, String email, String phoneNumber,
			String address, String userID, String username, String password, String role) {
		this.studentID = studentID;
		this.mssv = mssv;
		this.studentName = studentName;
		this.birthdate = birthdate;
		this.gender = gender;
		this.department = department;
		this.majorID = majorID;
		this.batchID = batchID;
		this.educationSystem = educationSystem;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.role = role;
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

	// Getters and Setters for Student properties
	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getMssv() {
		return mssv;
	}

	public void setMssv(String mssv) {
		this.mssv = mssv;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getMajorID() {
		return majorID;
	}

	public void setMajorID(String majorID) {
		this.majorID = majorID;
	}

	public String getBatchID() {
		return batchID;
	}

	public void setBatchID(String batchID) {
		this.batchID = batchID;
	}

	public String getEducationSystem() {
		return educationSystem;
	}

	public void setEducationSystem(String educationSystem) {
		this.educationSystem = educationSystem;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
}
