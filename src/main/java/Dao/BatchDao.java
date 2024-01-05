package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bean.BatchBean;

public class BatchDao {

	private Connection connection;

	public BatchDao(Connection connection) {
		this.connection = connection;
	}

	public void insertBatch(String batchCode, String batchName) throws SQLException {
		String query = "CALL AddBatch(?, ?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, batchCode);
			preparedStatement.setString(2, batchName);
			preparedStatement.executeUpdate();
		}
	}

	public List<BatchBean> getAllBatches() throws SQLException {
		List<BatchBean> batches = new ArrayList<>();
		String query = "SELECT * FROM Batches";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				BatchBean batch = new BatchBean();
				batch.setBatchID(resultSet.getString("Batch_ID"));
				batch.setBatchCode(resultSet.getString("batch_code"));
				batch.setBatchName(resultSet.getString("batch_name"));
				batches.add(batch);
			}
		}

		return batches;
	}

	public BatchBean getBatchByID(String batchID) throws SQLException {
		String query = "SELECT * FROM Batches WHERE Batch_ID = ?";
		BatchBean batch = null;

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, batchID);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				batch = new BatchBean();
				batch.setBatchID(resultSet.getString("Batch_ID"));
				batch.setBatchCode(resultSet.getString("batch_code"));
				batch.setBatchName(resultSet.getString("batch_name"));
			}
		}

		return batch;
	}

	public void updateBatch(String batchID, String batchCode, String batchName) throws SQLException {
		String query = "UPDATE Batches SET batch_code = ?, batch_name = ? WHERE Batch_ID = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, batchCode);
			preparedStatement.setString(2, batchName);
			preparedStatement.setString(3, batchID);

			preparedStatement.executeUpdate();
		}
	}

	public void deleteBatch(String batchID) throws SQLException {
		String query = "DELETE FROM Batches WHERE Batch_ID = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, batchID);
			preparedStatement.executeUpdate();
		}
	}
}
