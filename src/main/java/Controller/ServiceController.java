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

import Bean.ServiceBean;
import Dao.ServiceDao;
import Util.DBConnector;

@WebServlet("/services")
public class ServiceController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action != null) {
			switch (action) {
			case "delete":
				deleteService(request, response);
				break;
			case "get":
				getAllServicesJson(request, response);
				break;
			case "edit":
				getServiceByID(request, response);
				break;
			default:
				getAllServices(request, response);
				break;
			}
		} else {
			getAllServices(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action != null) {
			switch (action) {
			case "add":
				addService(request, response);
				break;
			case "update":
				updateService(request, response);
				break;
			default:
				break;
			}
		}
	}

	private void getAllServicesJson(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (Connection connection = DBConnector.getConnection()) {
			ServiceDao serviceDAO = new ServiceDao(connection);
			List<ServiceBean> services = serviceDAO.getAllServices();
			String json = new Gson().toJson(services);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void getAllServices(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (Connection connection = DBConnector.getConnection()) {
			ServiceDao serviceDAO = new ServiceDao(connection);
			List<ServiceBean> services = serviceDAO.getAllServices();
			request.setAttribute("services", services);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/services.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void getServiceByID(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String serviceID = request.getParameter("serviceID");

		try (Connection connection = DBConnector.getConnection()) {
			ServiceDao serviceDAO = new ServiceDao(connection);
			ServiceBean service = serviceDAO.getServiceByID(serviceID);

			if (service != null) {

				String json = new Gson().toJson(service);

				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			} else {

				response.sendRedirect("services");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void addService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String serviceName = request.getParameter("serviceName");
		String description = request.getParameter("description");
		String responsibleDepartment = request.getParameter("responsibleDepartment");
		int processingTime = Integer.parseInt(request.getParameter("processingTime"));
		String createdBy = request.getParameter("createdBy");

		try (Connection connection = DBConnector.getConnection()) {
			ServiceDao serviceDAO = new ServiceDao(connection);

			serviceDAO.insertService(serviceName, description, responsibleDepartment, processingTime, createdBy);
			response.sendRedirect("services");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updateService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String serviceID = request.getParameter("serviceID");
		String serviceCode = request.getParameter("serviceCode");
		String serviceName = request.getParameter("serviceName");
		String description = request.getParameter("description");
		String responsibleDepartment = request.getParameter("responsibleDepartment");
		int processingTime = Integer.parseInt(request.getParameter("processingTime"));
		String createdBy = request.getParameter("createdBy");

		try (Connection connection = DBConnector.getConnection()) {
			ServiceDao serviceDAO = new ServiceDao(connection);
			ServiceBean service = new ServiceBean(serviceID, serviceCode, serviceName, description,
					responsibleDepartment, processingTime, createdBy);
			serviceDAO.updateService(service);
			response.sendRedirect("services");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void deleteService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String serviceID = request.getParameter("serviceID");

		try (Connection connection = DBConnector.getConnection()) {
			ServiceDao serviceDAO = new ServiceDao(connection);
			serviceDAO.deleteService(serviceID);
			response.sendRedirect("services");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
