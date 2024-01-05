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

import Bean.BatchBean;
import Dao.BatchDao;
import Util.DBConnector;

@WebServlet("/batches")
public class BatchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action != null) {
			switch (action) {
			case "delete":
				deleteBatch(request, response);
				break;
			case "get":
				getAllBatchesJson(request, response);
				break;
			default:
				getAllBatches(request, response);
				break;
			}
		} else {
			getAllBatches(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action != null) {
			switch (action) {
			case "add":
				addBatch(request, response);
				break;
			case "update":
				updateBatch(request, response);
				break;
			default:
				break;
			}
		}
	}

	private void getAllBatchesJson(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (Connection connection = DBConnector.getConnection()) {
			BatchDao batchDAO = new BatchDao(connection);
			List<BatchBean> batches = batchDAO.getAllBatches();
			request.setAttribute("batches", batches);
			String json = new Gson().toJson(batches);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void getAllBatches(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (Connection connection = DBConnector.getConnection()) {
			BatchDao batchDAO = new BatchDao(connection);
			List<BatchBean> batches = batchDAO.getAllBatches();
			request.setAttribute("batches", batches);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/batches.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void addBatch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String batchCode = request.getParameter("batchCode");
		String batchName = request.getParameter("batchName");

		try (Connection connection = DBConnector.getConnection()) {
			BatchDao batchDAO = new BatchDao(connection);
			batchDAO.insertBatch(batchCode, batchName);
			response.sendRedirect("batches");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updateBatch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String batchID = request.getParameter("batchID");
		String batchCode = request.getParameter("batchCode");
		String batchName = request.getParameter("batchName");

		try (Connection connection = DBConnector.getConnection()) {
			BatchDao batchDAO = new BatchDao(connection);
			batchDAO.updateBatch(batchID, batchCode, batchName);
			response.sendRedirect("batches");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void deleteBatch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String batchID = request.getParameter("batchID");

		try (Connection connection = DBConnector.getConnection()) {
			BatchDao batchDAO = new BatchDao(connection);
			batchDAO.deleteBatch(batchID);
			response.sendRedirect("batches");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
