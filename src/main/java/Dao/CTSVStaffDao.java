package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bean.CTSVStaffBean;

public class CTSVStaffDao {
	private Connection connection;

	public CTSVStaffDao(Connection connection) {
		this.connection = connection;
	}

	// Add CTSVStaff
	public void addCTSVStaff(CTSVStaffBean ctsvStaff) {
		String query = "CALL AddCTSVStaff(?, ?, ?, ?, ?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, ctsvStaff.getStaffName());
			preparedStatement.setString(2, ctsvStaff.getEmail());
			preparedStatement.setString(3, ctsvStaff.getUsername());
			preparedStatement.setString(4, ctsvStaff.getPassword());
			preparedStatement.setString(5, ctsvStaff.getPhoneNumber());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Get all CTSVStaff members
	public List<CTSVStaffBean> getAllCTSVStaff() throws SQLException {
		List<CTSVStaffBean> ctsvStaffList = new ArrayList<>();
		String query = "SELECT * FROM CTSVStaff";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				CTSVStaffBean ctsvStaff = new CTSVStaffBean();
				ctsvStaff.setCtsvStaffID(resultSet.getString("CTSVStaff_ID"));
				ctsvStaff.setStaffName(resultSet.getString("staff_name"));
				ctsvStaff.setEmail(resultSet.getString("email"));
				ctsvStaff.setUserID(resultSet.getString("User_ID"));

				ctsvStaffList.add(ctsvStaff);
			}
		}

		return ctsvStaffList;
	}

	// Get CTSVStaff by ID using stored procedure with PreparedStatement
	public CTSVStaffBean getCTSVStaffById(String ctsvStaffID) throws SQLException {
		String query = "{CALL GetCTSVStaffInfoById(?)}";
		CTSVStaffBean ctsvStaff = null;

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, ctsvStaffID);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				ctsvStaff = new CTSVStaffBean();
				ctsvStaff.setCtsvStaffID(resultSet.getString("CTSVStaff_ID"));
				ctsvStaff.setUsername(resultSet.getString("Username"));
				ctsvStaff.setPassword(resultSet.getString("Password"));
				ctsvStaff.setStaffName(resultSet.getString("Staff_Name"));
				ctsvStaff.setEmail(resultSet.getString("Email"));
				ctsvStaff.setPhoneNumber(resultSet.getString("Phone_Number"));
				ctsvStaff.setUserID(resultSet.getString("User_ID"));
			}
		}

		return ctsvStaff;
	}

	// Update CTSVStaff using PreparedStatement
	public void updateCTSVStaff(CTSVStaffBean ctsvStaff) throws SQLException {
		String query = "CALL UpdateCTSVStaff(?, ?, ?, ?, ?, ?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, ctsvStaff.getCtsvStaffID());
			preparedStatement.setString(2, ctsvStaff.getStaffName());
			preparedStatement.setString(3, ctsvStaff.getEmail());
			preparedStatement.setString(4, ctsvStaff.getPhoneNumber());
			preparedStatement.setString(5, ctsvStaff.getUsername());
			preparedStatement.setString(6, ctsvStaff.getPassword());

			preparedStatement.executeUpdate();
		}
	}

	// Delete CTSVStaff by ID
	public void deleteCTSVStaff(String ctsvStaffID) throws SQLException {
		String query = "CALL DeleteCTSVStaff(?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, ctsvStaffID);
			preparedStatement.executeUpdate();
		}
	}
}
