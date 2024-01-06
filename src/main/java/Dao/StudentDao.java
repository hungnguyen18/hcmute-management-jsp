package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bean.StudentBean;

public class StudentDao {
	private Connection connection;

	public StudentDao(Connection connection) {
		this.connection = connection;
	}

	// Add Student
	public void addStudent(StudentBean student) {
		String query = "CALL AddStudent(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, student.getUsername());
			preparedStatement.setString(2, student.getPassword());
			preparedStatement.setString(3, student.getEmail());
			preparedStatement.setString(4, student.getPhoneNumber());
			preparedStatement.setString(5, student.getStudentName());
			preparedStatement.setDate(6, java.sql.Date.valueOf(student.getBirthdate()));
			preparedStatement.setString(7, student.getGender());
			preparedStatement.setString(8, student.getDepartment());
			preparedStatement.setString(9, student.getMajorID());
			preparedStatement.setString(10, student.getBatchID());
			preparedStatement.setString(11, student.getEducationSystem());
			preparedStatement.setString(12, student.getAddress());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Get all students
	public List<StudentBean> getAllStudents() throws SQLException {
		List<StudentBean> students = new ArrayList<>();
		String query = "SELECT * FROM Students";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				StudentBean student = new StudentBean();
				student.setStudentID(resultSet.getString("Student_ID"));
				student.setMssv(resultSet.getString("MSSV"));
				student.setStudentName(resultSet.getString("student_name"));
				student.setBirthdate(resultSet.getString("birthdate"));
				student.setGender(resultSet.getString("gender"));
				student.setDepartment(resultSet.getString("department"));
				student.setMajorID(resultSet.getString("major_id"));
				student.setBatchID(resultSet.getString("batch_id"));
				student.setEducationSystem(resultSet.getString("education_system"));
				student.setEmail(resultSet.getString("email"));
				student.setPhoneNumber(resultSet.getString("phone_number"));
				student.setAddress(resultSet.getString("address"));
				student.setUserID(resultSet.getString("User_ID"));

				students.add(student);
			}
		}

		return students;
	}

	// Get student by ID using stored procedure with PreparedStatement
	public StudentBean getStudentById(String studentID) throws SQLException {
		String query = "{CALL GetStudentInfoByStudentID(?)}";
		StudentBean student = null;

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, studentID);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				student = new StudentBean();
				student.setStudentID(resultSet.getString("Student_ID"));
				student.setMssv(resultSet.getString("MSSV"));
				student.setUsername(resultSet.getString("Username"));
				student.setPassword(resultSet.getString("Password"));
				student.setStudentName(resultSet.getString("Student_Name"));
				student.setBirthdate(resultSet.getString("Birthdate"));
				student.setGender(resultSet.getString("Gender"));
				student.setDepartment(resultSet.getString("Department"));
				student.setMajorID(resultSet.getString("Major_ID"));
				student.setBatchID(resultSet.getString("Batch_ID"));
				student.setEducationSystem(resultSet.getString("Education_System"));
				student.setEmail(resultSet.getString("Email"));
				student.setPhoneNumber(resultSet.getString("Phone_Number"));
				student.setAddress(resultSet.getString("Address"));
				student.setUserID(resultSet.getString("User_ID"));
			}
		}

		return student;
	}

	public StudentBean getStudentByUserId(String userId) throws SQLException {
		String query = "SELECT * FROM Students WHERE User_ID = ?";
		StudentBean student = null;

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				student = new StudentBean();
				student.setStudentID(resultSet.getString("Student_ID"));
				student.setMssv(resultSet.getString("MSSV"));
				student.setStudentName(resultSet.getString("student_name"));
				student.setBirthdate(resultSet.getString("birthdate"));
				student.setGender(resultSet.getString("gender"));
				student.setDepartment(resultSet.getString("department"));
				student.setMajorID(resultSet.getString("major_id"));
				student.setBatchID(resultSet.getString("batch_id"));
				student.setEducationSystem(resultSet.getString("education_system"));
				student.setEmail(resultSet.getString("email"));
				student.setPhoneNumber(resultSet.getString("phone_number"));
				student.setAddress(resultSet.getString("address"));
				student.setUserID(resultSet.getString("User_ID"));
			}
		}

		return student;
	}

	// Update student using PreparedStatement
	public void updateStudent(StudentBean student) throws SQLException {
		String query = "CALL UpdateStudent(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, student.getStudentID());
			preparedStatement.setString(2, student.getStudentName());
			preparedStatement.setString(3, student.getBirthdate());
			preparedStatement.setString(4, student.getGender());
			preparedStatement.setString(5, student.getDepartment());
			preparedStatement.setString(6, student.getMajorID());
			preparedStatement.setString(7, student.getBatchID());
			preparedStatement.setString(8, student.getEducationSystem());
			preparedStatement.setString(9, student.getEmail());
			preparedStatement.setString(10, student.getPhoneNumber());
			preparedStatement.setString(11, student.getAddress());
			preparedStatement.setString(12, student.getUsername());
			preparedStatement.setString(13, student.getPassword());
			preparedStatement.executeUpdate();
		}
	}

	// Delete student by ID
	public void deleteStudent(String studentID) throws SQLException {
		String query = "CALL DeleteStudent(?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, studentID);
			preparedStatement.executeUpdate();
		}
	}
}
