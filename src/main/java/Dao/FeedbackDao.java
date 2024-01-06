package Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class FeedbackDao {

	private Connection connection;

	public FeedbackDao(Connection connection) {
		this.connection = connection;
	}

	// Thêm phản hồi và cập nhật trạng thái của request
	public void addFeedbackAndUpdateRequest(String requestID, String studentID, String feedbackText)
			throws SQLException {
		try (CallableStatement callableStatement = connection.prepareCall("{CALL AddFeedback(?, ?, ?)}")) {
			callableStatement.setString(1, requestID);
			callableStatement.setString(2, studentID);
			callableStatement.setString(3, feedbackText);
			callableStatement.execute();
		}
	}
}
