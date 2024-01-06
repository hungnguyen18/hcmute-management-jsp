package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Bean.RequestBean;
import Dao.RequestDao;
import Util.DBConnector;

@WebServlet("/requests")
public class RequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action != null) {
			switch (action) {
			case "delete":
				deleteRequest(request, response);
				break;
			case "get":
				getAllRequestsJson(request, response);
				break;
			case "edit":
				getRequestByID(request, response);
				break;
			default:
				getAllRequests(request, response);
				break;
			}
		} else {
			getAllRequests(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action != null) {
			switch (action) {
			case "add":
				addRequest(request, response);
				break;
			case "update":
				updateRequest(request, response);
				break;
			default:
				break;
			}
		}
	}

	private void getAllRequestsJson(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (Connection connection = DBConnector.getConnection()) {
			RequestDao requestDAO = new RequestDao(connection);
			List<RequestBean> requests = requestDAO.getAllRequests();
			request.setAttribute("requests", requests);
			String json = new Gson().toJson(requests);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void getAllRequests(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (Connection connection = DBConnector.getConnection()) {
			RequestDao requestDAO = new RequestDao(connection);
			List<RequestBean> requests = requestDAO.getAllRequests();
			request.setAttribute("requests", requests);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/requests.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void getRequestByID(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestID = request.getParameter("requestID");

		try (Connection connection = DBConnector.getConnection()) {
			RequestDao requestDAO = new RequestDao(connection);
			RequestBean requestBean = requestDAO.getRequestByID(requestID);

			if (requestBean != null) {
				String json = new Gson().toJson(requestBean);

				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			} else {
				response.sendRedirect("requests");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void addRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String studentID = request.getParameter("studentID");
		String serviceID = request.getParameter("serviceID");
		String requestText = request.getParameter("requestText");

		try (Connection connection = DBConnector.getConnection()) {
			RequestDao requestDAO = new RequestDao(connection);

			requestDAO.insertRequest(studentID, serviceID, requestText);
			response.sendRedirect("requests");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updateRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestID = request.getParameter("requestID");
		String requestText = request.getParameter("requestText");

		try (Connection connection = DBConnector.getConnection()) {
			RequestDao requestDAO = new RequestDao(connection);
			RequestBean requestBean = new RequestBean();
			requestBean.setRequestID(requestID);
			requestBean.setRequestText(requestText);

			requestDAO.updateRequest(requestBean);
			response.sendRedirect("requests");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void deleteRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestID = request.getParameter("requestID");

		try (Connection connection = DBConnector.getConnection()) {
			RequestDao requestDAO = new RequestDao(connection);
			requestDAO.deleteRequest(requestID);
			response.sendRedirect("requests");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
