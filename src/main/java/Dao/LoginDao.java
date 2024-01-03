package Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import Bean.LoginBean;
import Bean.UserBean;
import Util.DBConnector;
import Util.UserSessionUtil;

public class LoginDao {

	private static final int STATUS_PARAM_INDEX = 3;
	private static final int USER_ID_PARAM_INDEX = 4;
	private static final int ROLE_PARAM_INDEX = 5;
	private static final int EMAIL_PARAM_INDEX = 6;
	private static final int PHONE_NUMBER_PARAM_INDEX = 7;

	public class ValidationResult {
		private boolean status;
		private UserBean user;

		public ValidationResult(boolean status, UserBean user) {
			this.status = status;
			this.user = user;
		}

		public boolean isStatus() {
			return status;
		}

		public UserBean getUser() {
			return user;
		}
	}

	// Validate user credentials using a stored procedure
	public ValidationResult validate(LoginBean loginBean, HttpServletRequest request) throws ClassNotFoundException {
		boolean status = false;
		UserBean user = null;

		try (Connection connection = DBConnector.getConnection();
				CallableStatement callableStatement = connection
						.prepareCall("{CALL ValidateLogin(?, ?, ?, ?, ?, ?, ?)}")) {

			// Set input parameters
			callableStatement.setString(1, loginBean.getUsername());
			callableStatement.setString(2, loginBean.getPassword());
			// Register the output parameters
			callableStatement.registerOutParameter(STATUS_PARAM_INDEX, java.sql.Types.BOOLEAN);
			callableStatement.registerOutParameter(USER_ID_PARAM_INDEX, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(ROLE_PARAM_INDEX, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(EMAIL_PARAM_INDEX, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(PHONE_NUMBER_PARAM_INDEX, java.sql.Types.VARCHAR);

			// Execute the stored procedure
			callableStatement.execute();

			// Retrieve the result
			status = callableStatement.getBoolean(STATUS_PARAM_INDEX);

			// Check if login is successful
			if (status) {
				// Retrieve user information
				String userId = callableStatement.getString(USER_ID_PARAM_INDEX);
				String role = callableStatement.getString(ROLE_PARAM_INDEX);
				String email = callableStatement.getString(EMAIL_PARAM_INDEX);
				String phoneNumber = callableStatement.getString(PHONE_NUMBER_PARAM_INDEX);

				// Create a User object with the retrieved information
				user = new UserBean(userId, loginBean.getUsername(), "", role, email, phoneNumber, status);
				UserSessionUtil.addUser(request, user);
			}

		} catch (SQLException e) {
			// Handle SQL exception
			printSQLException(e);
		}
		return new ValidationResult(status, user);
	}

	// Print SQL exception details
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());

				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
