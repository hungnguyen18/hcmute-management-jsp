package Bean;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class RequestBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String requestID;
	private String requestCode;
	private String studentID;
	private String serviceID;
	private Date submissionDate;
	private Timestamp submissionTime;
	private Timestamp expirationTime;
	private String status;
	private String requestText;

	// Constructors, getters, and setters

	public RequestBean() {
	}

	public RequestBean(String requestID, String requestCode, String studentID, String serviceID, Date submissionDate,
			Timestamp submissionTime, Timestamp expirationTime, String status, String requestText) {
		this.requestID = requestID;
		this.requestCode = requestCode;
		this.studentID = studentID;
		this.serviceID = serviceID;
		this.submissionDate = submissionDate;
		this.submissionTime = submissionTime;
		this.expirationTime = expirationTime;
		this.status = status;
		this.requestText = requestText;
	}

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	public String getRequestCode() {
		return requestCode;
	}

	public void setRequestCode(String requestCode) {
		this.requestCode = requestCode;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getServiceID() {
		return serviceID;
	}

	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	public Timestamp getSubmissionTime() {
		return submissionTime;
	}

	public void setSubmissionTime(Timestamp submissionTime) {
		this.submissionTime = submissionTime;
	}

	public Timestamp getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Timestamp expirationTime) {
		this.expirationTime = expirationTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRequestText() {
		return requestText;
	}

	public void setRequestText(String requestText) {
		this.requestText = requestText;
	}
}
