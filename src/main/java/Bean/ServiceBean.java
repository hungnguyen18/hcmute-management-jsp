package Bean;

import java.io.Serializable;

public class ServiceBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String serviceID;
	private String serviceCode;
	private String serviceName;
	private String description;
	private String responsibleDepartment;
	private int processingTime;
	private String createdBy;
	// Constructors, getters, and setters

	public ServiceBean() {
	}

	public ServiceBean(String serviceID, String serviceCode, String serviceName, String description,
			String responsibleDepartment, int processingTime, String createdBy) {
		this.serviceID = serviceID;
		this.serviceCode = serviceCode;
		this.serviceName = serviceName;
		this.description = description;
		this.responsibleDepartment = responsibleDepartment;
		this.processingTime = processingTime;
		this.createdBy = createdBy;
	}

	public String getServiceID() {
		return serviceID;
	}

	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResponsibleDepartment() {
		return responsibleDepartment;
	}

	public void setResponsibleDepartment(String responsibleDepartment) {
		this.responsibleDepartment = responsibleDepartment;
	}

	public int getProcessingTime() {
		return processingTime;
	}

	public void setProcessingTime(int processingTime) {
		this.processingTime = processingTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
}
