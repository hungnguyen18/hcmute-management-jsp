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

import Bean.CTSVStaffBean;
import Dao.CTSVStaffDao;
import Util.DBConnector;

@WebServlet("/ctsv")
public class CTSVStaffController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action != null) {
			switch (action) {
			case "delete":
				deleteCTSVStaff(request, response);
				break;
			case "edit":
				detailCTSVStaff(request, response);
				break;
			case "get":
				getAllCTSVStaffsJson(request, response);
				break;
			default:
				getAllCTSVStaffs(request, response);
				break;
			}
		} else {
			getAllCTSVStaffs(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action != null) {
			switch (action) {
			case "add":
				addCTSVStaff(request, response);
				break;
			case "update":
				updateCTSVStaff(request, response);
				break;
			default:
				break;
			}
		}
	}

	private void getAllCTSVStaffsJson(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (Connection connection = DBConnector.getConnection()) {
			CTSVStaffDao ctsvStaffDAO = new CTSVStaffDao(connection);
			List<CTSVStaffBean> ctsvStaffs = ctsvStaffDAO.getAllCTSVStaff();
			request.setAttribute("ctsvStaffs", ctsvStaffs);
			String json = new Gson().toJson(ctsvStaffs);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void getAllCTSVStaffs(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (Connection connection = DBConnector.getConnection()) {
			CTSVStaffDao ctsvStaffDAO = new CTSVStaffDao(connection);
			List<CTSVStaffBean> ctsvStaffs = ctsvStaffDAO.getAllCTSVStaff();
			request.setAttribute("ctsvStaffs", ctsvStaffs);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ctsv.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void addCTSVStaff(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CTSVStaffBean ctsvStaff = createCTSVStaffFromRequest(request);
		try (Connection connection = DBConnector.getConnection()) {
			CTSVStaffDao ctsvStaffDAO = new CTSVStaffDao(connection);
			ctsvStaffDAO.addCTSVStaff(ctsvStaff);
			response.sendRedirect("ctsv");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void detailCTSVStaff(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ctsvStaffID = request.getParameter("ctsvID");
		try (Connection connection = DBConnector.getConnection()) {
			CTSVStaffDao ctsvStaffDAO = new CTSVStaffDao(connection);
			CTSVStaffBean ctsvStaff = ctsvStaffDAO.getCTSVStaffById(ctsvStaffID);

			request.setAttribute("ctsvStaff", ctsvStaff);
			String json = new Gson().toJson(ctsvStaff);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updateCTSVStaff(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CTSVStaffBean ctsvStaff = createCTSVStaffFromRequest(request);
		try (Connection connection = DBConnector.getConnection()) {
			CTSVStaffDao ctsvStaffDAO = new CTSVStaffDao(connection);
			ctsvStaffDAO.updateCTSVStaff(ctsvStaff);
			response.sendRedirect("ctsv");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void deleteCTSVStaff(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ctsvStaffID = request.getParameter("ctsvStaffID");
		try (Connection connection = DBConnector.getConnection()) {
			CTSVStaffDao ctsvStaffDAO = new CTSVStaffDao(connection);
			ctsvStaffDAO.deleteCTSVStaff(ctsvStaffID);
			response.sendRedirect("ctsv");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private CTSVStaffBean createCTSVStaffFromRequest(HttpServletRequest request) {
		CTSVStaffBean ctsvStaff = new CTSVStaffBean();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String ctsvStaffID = request.getParameter("ctsvStaffID");
		String staffName = request.getParameter("staffName");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		String userID = request.getParameter("userID");

		ctsvStaff.setUsername(username);
		ctsvStaff.setPassword(password);
		ctsvStaff.setCtsvStaffID(ctsvStaffID);
		ctsvStaff.setStaffName(staffName);
		ctsvStaff.setEmail(email);
		ctsvStaff.setPhoneNumber(phoneNumber);
		ctsvStaff.setUserID(userID);

		return ctsvStaff;
	}
}
