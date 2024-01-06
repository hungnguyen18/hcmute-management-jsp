package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.FeedbackDao;
import Util.DBConnector;

@WebServlet("/feedbacks")
public class FeedbackController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String action = request.getParameter("action");

//		if (action != null) {
//			switch (action) {
//			case "delete":
//				deleteFeedback(request, response);
//				break;
//			case "get":
//				getAllFeedbacksJson(request, response);
//				break;
//			default:
//				getAllFeedbacks(request, response);
//				break;
//			}
//		} else {
//			getAllFeedbacks(request, response);
//		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action != null) {
			switch (action) {
			case "add":
				addFeedback(request, response);
				break;
			default:
				break;
			}
		}
	}

//    private void getAllFeedbacksJson(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try (Connection connection = DBConnector.getConnection()) {
//            FeedbackDao feedbackDAO = new FeedbackDao(connection);
//            List<FeedbackBean> feedbacks = feedbackDAO.getAllFeedbacks();
//            request.setAttribute("feedbacks", feedbacks);
//            String json = new Gson().toJson(feedbacks);
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().write(json);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

//    private void getAllFeedbacks(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try (Connection connection = DBConnector.getConnection()) {
//            FeedbackDao feedbackDAO = new FeedbackDao(connection);
//            List<FeedbackBean> feedbacks = feedbackDAO.getAllFeedbacks();
//            request.setAttribute("feedbacks", feedbacks);
//            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/feedbacks.jsp");
//            dispatcher.forward(request, response);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

	private void addFeedback(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestID = request.getParameter("requestID");
		String studentID = request.getParameter("studentID");
		String feedbackText = request.getParameter("feedbackText");

		try (Connection connection = DBConnector.getConnection()) {
			FeedbackDao feedbackDAO = new FeedbackDao(connection);
			feedbackDAO.addFeedbackAndUpdateRequest(requestID, studentID, feedbackText);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//
//    private void deleteFeedback(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String feedbackID = request.getParameter("feedbackID");
//
//        try (Connection connection = DBConnector.getConnection()) {
//            FeedbackDao feedbackDAO = new FeedbackDao(connection);
//            feedbackDAO.deleteFeedback(feedbackID);
//            response.sendRedirect("feedbacks");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
