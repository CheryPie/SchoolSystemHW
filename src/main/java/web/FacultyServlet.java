package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Faculty;

import com.google.gson.Gson;

import dao.FacultyDAO;

/**
 * Servlet implementation class FacultyServlet
 */
@WebServlet("/FacultyServlet")
public class FacultyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	FacultyDAO facultyDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FacultyServlet() {
		super();
		facultyDAO = new FacultyDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		String resource = null;
		resource = gson.toJson(facultyDAO.findAll());
		response.setContentType("application/json");
		response.getWriter().print(resource);
		response.getWriter().flush();
	}

	/**
	 */

	private static final String NAME = "name";
	private static final String INDEX_TO_DELETE = "deleted";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter(NAME);
		String deleted = request.getParameter(INDEX_TO_DELETE);
		if (facultyDAO == null) {
			facultyDAO = new FacultyDAO();
		}
		if (deleted != null) {
			Faculty faculty = facultyDAO.find(new Long(deleted));
			facultyDAO.delete(faculty);
		} else {
			Faculty faculty = new Faculty();
			faculty.setName(name);
			facultyDAO.create(faculty);
		}

		request.getRequestDispatcher("faculty.html").forward(request, response);
	}

}
