package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bean.MajorBean;

public class MajorDao {

	private Connection connection;

	public MajorDao(Connection connection) {
		this.connection = connection;
	}

	public void insertMajor(String majorCode, String majorName) throws SQLException {
		String query = "CALL AddMajor(?, ?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, majorCode);
			preparedStatement.setString(2, majorName);
			preparedStatement.executeUpdate();
		}
	}

	public List<MajorBean> getAllMajors() throws SQLException {
		List<MajorBean> majors = new ArrayList<>();
		String query = "SELECT * FROM Majors";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				MajorBean major = new MajorBean();
				major.setMajorID(resultSet.getString("Major_ID"));
				major.setMajorCode(resultSet.getString("major_code"));
				major.setMajorName(resultSet.getString("major_name"));
				majors.add(major);
			}
		}

		return majors;
	}

	public MajorBean getMajorByID(String majorID) throws SQLException {
		String query = "SELECT * FROM Majors WHERE Major_ID = ?";
		MajorBean major = null;

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, majorID);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				major = new MajorBean();
				major.setMajorID(resultSet.getString("Major_ID"));
				major.setMajorCode(resultSet.getString("major_code"));
				major.setMajorName(resultSet.getString("major_name"));
			}
		}

		return major;
	}

	public void updateMajor(String majorID, String majorCode, String majorName) throws SQLException {
		String query = "UPDATE Majors SET major_code = ?, major_name = ? WHERE Major_ID = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, majorCode);
			preparedStatement.setString(2, majorName);
			preparedStatement.setString(3, majorID);

			preparedStatement.executeUpdate();
		}
	}

	public void deleteMajor(String majorID) throws SQLException {
		String query = "DELETE FROM Majors WHERE Major_ID = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, majorID);
			preparedStatement.executeUpdate();
		}
	}
}
