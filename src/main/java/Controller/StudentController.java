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

import Bean.StudentBean;
import Dao.StudentDao;
import Util.DBConnector;

@WebServlet("/students")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action != null) {
			switch (action) {
			case "delete":
				deleteStudent(request, response);
				break;
			case "edit":
				detailStudent(request, response);
				break;
			case "get":
				getStudentByUserId(request, response);
				break;
			default:
				getAllStudents(request, response);
				break;
			}
		} else {
			getAllStudents(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action != null) {
			switch (action) {
			case "add":
				addStudent(request, response);
				break;
			case "update":
				updateStudent(request, response);
				break;
			default:
				break;
			}
		}
	}

	private void getAllStudents(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (Connection connection = DBConnector.getConnection()) {
			StudentDao studentDAO = new StudentDao(connection);
			List<StudentBean> students = studentDAO.getAllStudents();
			request.setAttribute("students", students);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/students.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StudentBean student = createStudentFromRequest(request);
		try (Connection connection = DBConnector.getConnection()) {
			StudentDao studentDAO = new StudentDao(connection);
			studentDAO.addStudent(student);
			response.sendRedirect("students");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void detailStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String studentID = request.getParameter("studentID");
		try (Connection connection = DBConnector.getConnection()) {
			StudentDao studentDAO = new StudentDao(connection);
			StudentBean student = studentDAO.getStudentById(studentID);
			request.setAttribute("student", student);
			String json = new Gson().toJson(student);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/detail-student.jsp");
//			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void getStudentByUserId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");

		try (Connection connection = DBConnector.getConnection()) {
			StudentDao studentDao = new StudentDao(connection);
			StudentBean student = studentDao.getStudentByUserId(userId);

			if (student != null) {
				request.setAttribute("student", student);
				String json = new Gson().toJson(student);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			} else {
				response.sendRedirect("students");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StudentBean student = createStudentFromRequest(request);
		try (Connection connection = DBConnector.getConnection()) {
			StudentDao studentDAO = new StudentDao(connection);
			studentDAO.updateStudent(student);
			response.sendRedirect("students");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String studentID = request.getParameter("studentID");
		try (Connection connection = DBConnector.getConnection()) {
			StudentDao studentDAO = new StudentDao(connection);
			studentDAO.deleteStudent(studentID);
			response.sendRedirect("students");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private StudentBean createStudentFromRequest(HttpServletRequest request) {
		StudentBean student = new StudentBean();

		// Lấy thông tin từ request parameters
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String studentID = request.getParameter("studentID");
		String mssv = request.getParameter("mssv");
		String studentName = request.getParameter("studentName");
		String birthdate = request.getParameter("birthdate");
		String gender = request.getParameter("gender");
		String department = request.getParameter("department");
		String majorID = request.getParameter("majorID");
		String batchID = request.getParameter("batchID");
		String educationSystem = request.getParameter("educationSystem");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		String address = request.getParameter("address");
		String userID = request.getParameter("userID");

		// Thiết lập các thuộc tính của StudentBean
		student.setUsername(username);
		student.setPassword(password);
		student.setStudentID(studentID);
		student.setMssv(mssv);
		student.setStudentName(studentName);
		student.setBirthdate(birthdate);
		student.setGender(gender);
		student.setDepartment(department);
		student.setMajorID(majorID);
		student.setBatchID(batchID);
		student.setEducationSystem(educationSystem);
		student.setEmail(email);
		student.setPhoneNumber(phoneNumber);
		student.setAddress(address);
		student.setUserID(userID);

		return student;
	}
}
