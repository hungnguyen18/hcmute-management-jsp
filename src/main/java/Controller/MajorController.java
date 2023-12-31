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

import Bean.MajorBean;
import Dao.MajorDao;
import Util.DBConnector;

@WebServlet("/majors")
public class MajorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action != null) {
			switch (action) {
			case "delete":
				deleteMajor(request, response);
				break;
			case "get":
				getAllMajorsJson(request, response);
				break;
			default:
				getAllMajors(request, response);
				break;
			}
		} else {
			getAllMajors(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action != null) {
			switch (action) {
			case "add":
				addMajor(request, response);
				break;
			case "update":
				updateMajor(request, response);
				break;
			default:
				break;
			}
		}
	}

	private void getAllMajorsJson(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (Connection connection = DBConnector.getConnection()) {
			MajorDao majorDAO = new MajorDao(connection);
			List<MajorBean> majors = majorDAO.getAllMajors();
			request.setAttribute("majors", majors);
			String json = new Gson().toJson(majors);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void getAllMajors(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (Connection connection = DBConnector.getConnection()) {
			MajorDao majorDAO = new MajorDao(connection);
			List<MajorBean> majors = majorDAO.getAllMajors();
			request.setAttribute("majors", majors);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/majors.jsp");
			dispatcher.forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void addMajor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String majorCode = request.getParameter("majorCode");
		String majorName = request.getParameter("majorName");

		try (Connection connection = DBConnector.getConnection()) {
			MajorDao majorDAO = new MajorDao(connection);
			majorDAO.insertMajor(majorCode, majorName);
			response.sendRedirect("majors");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updateMajor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String majorID = request.getParameter("majorID");
		String majorCode = request.getParameter("majorCode");
		String majorName = request.getParameter("majorName");

		try (Connection connection = DBConnector.getConnection()) {
			MajorDao majorDAO = new MajorDao(connection);
			majorDAO.updateMajor(majorID, majorCode, majorName);
			response.sendRedirect("majors");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void deleteMajor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String majorID = request.getParameter("majorID");

		try (Connection connection = DBConnector.getConnection()) {
			MajorDao majorDAO = new MajorDao(connection);
			majorDAO.deleteMajor(majorID);
			response.sendRedirect("majors");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
