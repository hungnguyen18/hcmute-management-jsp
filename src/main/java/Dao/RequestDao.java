package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bean.RequestBean;

public class RequestDao {

	private Connection connection;

	public RequestDao(Connection connection) {
		this.connection = connection;
	}

	public void insertRequest(String studentID, String serviceID, String requestText) throws SQLException {
		String query = "{CALL CreateRequest(?, ?, ?)}";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, studentID);
			preparedStatement.setString(2, serviceID);
			preparedStatement.setString(3, requestText);
			preparedStatement.execute();
		}
	}

	public List<RequestBean> getAllRequests() throws SQLException {
		List<RequestBean> requests = new ArrayList<>();
		String query = "SELECT * FROM Requests";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				RequestBean request = new RequestBean();
				request.setRequestID(resultSet.getString("Request_ID"));
				request.setRequestCode(resultSet.getString("request_code"));
				request.setStudentID(resultSet.getString("student_id"));
				request.setServiceID(resultSet.getString("service_id"));
				request.setSubmissionDate(resultSet.getDate("submission_date"));
				request.setSubmissionTime(resultSet.getTimestamp("submission_time"));
				request.setExpirationTime(resultSet.getTimestamp("expiration_time"));
				request.setStatus(resultSet.getString("status"));
				request.setRequestText(resultSet.getString("request_text"));
				requests.add(request);
			}
		}

		return requests;
	}

	public RequestBean getRequestByID(String requestID) throws SQLException {
		String query = "SELECT * FROM Requests WHERE Request_ID = ?";
		RequestBean request = null;

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, requestID);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				request = new RequestBean();
				request.setRequestID(resultSet.getString("Request_ID"));
				request.setRequestCode(resultSet.getString("request_code"));
				request.setStudentID(resultSet.getString("student_id"));
				request.setServiceID(resultSet.getString("service_id"));
				request.setSubmissionDate(resultSet.getDate("submission_date"));
				request.setSubmissionTime(resultSet.getTimestamp("submission_time"));
				request.setExpirationTime(resultSet.getTimestamp("expiration_time"));
				request.setStatus(resultSet.getString("status"));
				request.setRequestText(resultSet.getString("request_text"));
			}
		}

		return request;
	}

	public void updateRequest(RequestBean request) throws SQLException {
		String query = "UPDATE Requests SET request_code = ?, student_id = ?, service_id = ?, "
				+ "submission_date = ?, submission_time = ?, expiration_time = ?, status = ?, request_text = ? "
				+ "WHERE Request_ID = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, request.getRequestCode());
			preparedStatement.setString(2, request.getStudentID());
			preparedStatement.setString(3, request.getServiceID());
			preparedStatement.setDate(4, request.getSubmissionDate());
			preparedStatement.setTimestamp(5, request.getSubmissionTime());
			preparedStatement.setTimestamp(6, request.getExpirationTime());
			preparedStatement.setString(7, request.getStatus());
			preparedStatement.setString(8, request.getRequestText());
			preparedStatement.setString(9, request.getRequestID());

			preparedStatement.executeUpdate();
		}
	}

	public void deleteRequest(String requestID) throws SQLException {
		String query = "DELETE FROM Requests WHERE Request_ID = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, requestID);
			preparedStatement.executeUpdate();
		}
	}

}
