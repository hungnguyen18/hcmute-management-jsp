package Bean;

import java.sql.Timestamp;

public class FeedbackBean {
	private String feedbackID;
	private String requestID;
	private String studentID;
	private Timestamp feedbackDate;
	private String feedbackText;

	public FeedbackBean() {
		// Hàm khởi tạo mặc định
	}

	public FeedbackBean(String feedbackID, String requestID, String studentID, Timestamp feedbackDate,
			String feedbackText) {
		this.feedbackID = feedbackID;
		this.requestID = requestID;
		this.studentID = studentID;
		this.feedbackDate = feedbackDate;
		this.feedbackText = feedbackText;
	}

	public String getFeedbackID() {
		return feedbackID;
	}

	public void setFeedbackID(String feedbackID) {
		this.feedbackID = feedbackID;
	}

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public Timestamp getFeedbackDate() {
		return feedbackDate;
	}

	public void setFeedbackDate(Timestamp feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

	public String getFeedbackText() {
		return feedbackText;
	}

	public void setFeedbackText(String feedbackText) {
		this.feedbackText = feedbackText;
	}
}
