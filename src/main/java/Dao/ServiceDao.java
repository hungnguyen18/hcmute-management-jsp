package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bean.ServiceBean;

public class ServiceDao {

	private Connection connection;

	public ServiceDao(Connection connection) {
		this.connection = connection;
	}

	public void insertService(String serviceName, String description, String responsibleDepartment, int processingTime,
			String CreatedBy) throws SQLException {
		String query = "CALL AddService( ?, ?, ?, ?, ?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, serviceName);
			preparedStatement.setString(2, description);
			preparedStatement.setString(3, responsibleDepartment);
			preparedStatement.setInt(4, processingTime);
			preparedStatement.setString(5, CreatedBy);

			preparedStatement.executeUpdate();
		}
	}

	public List<ServiceBean> getAllServices() throws SQLException {
		List<ServiceBean> services = new ArrayList<>();
		String query = "SELECT * FROM Services";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				ServiceBean service = new ServiceBean();
				service.setServiceID(resultSet.getString("Service_ID"));
				service.setServiceCode(resultSet.getString("service_code"));
				service.setServiceName(resultSet.getString("service_name"));
				service.setDescription(resultSet.getString("description"));
				service.setResponsibleDepartment(resultSet.getString("responsible_department"));
				service.setProcessingTime(resultSet.getInt("processing_time"));
				service.setCreatedBy(resultSet.getString("created_by"));
				services.add(service);
			}
		}

		return services;
	}

	public ServiceBean getServiceByID(String serviceID) throws SQLException {
		String query = "SELECT * FROM Services WHERE Service_ID = ?";
		ServiceBean service = null;

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, serviceID);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				service = new ServiceBean();
				service.setServiceID(resultSet.getString("Service_ID"));
				service.setServiceCode(resultSet.getString("service_code"));
				service.setServiceName(resultSet.getString("service_name"));
				service.setDescription(resultSet.getString("description"));
				service.setResponsibleDepartment(resultSet.getString("responsible_department"));
				service.setProcessingTime(resultSet.getInt("processing_time"));
				service.setCreatedBy(resultSet.getString("created_by"));
			}
		}

		return service;
	}

	public void updateService(ServiceBean service) throws SQLException {
		String query = "UPDATE Services SET service_code = ?, service_name = ?, description = ?, "
				+ "responsible_department = ?, processing_time = ? WHERE Service_ID = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, service.getServiceCode());
			preparedStatement.setString(2, service.getServiceName());
			preparedStatement.setString(3, service.getDescription());
			preparedStatement.setString(4, service.getResponsibleDepartment());
			preparedStatement.setInt(5, service.getProcessingTime());
			preparedStatement.setString(6, service.getServiceID());

			preparedStatement.executeUpdate();
		}
	}

	public void deleteService(String serviceID) throws SQLException {
		String query = "DELETE FROM Services WHERE Service_ID = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, serviceID);
			preparedStatement.executeUpdate();
		}
	}
}
