package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Student;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.FacultyDAO;
import dao.StudentDAO;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StudentDAO studentDAO;
	private FacultyDAO facultyDAO;

	public StudentServlet() {
		super();
		studentDAO = new StudentDAO();
		facultyDAO = new FacultyDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		String resource = null;
		resource = gson.toJson(studentDAO.findAll());
		response.setContentType("application/json");
		response.getWriter().print(resource);
		response.getWriter().flush();
	}

	private static final String FK_NUMBER = "fkNumber";
	private static final String FIRST_NAME = "firstName";
	private static final String LAST_NAME = "lastName";
	private static final String INDEX_TO_DELETE = "deleted";
	private static final String FACULTY_ID = "facultyId";
	private static final String STUDENT_ID = "studentId";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String fkNumber = request.getParameter(FK_NUMBER);
		String firstName = request.getParameter(FIRST_NAME);
		String lastName = request.getParameter(LAST_NAME);
		String facultyId = request.getParameter(FACULTY_ID);
		String deleted = request.getParameter(INDEX_TO_DELETE);
		String id = request.getParameter(STUDENT_ID);
		if (studentDAO == null) {
			studentDAO = new StudentDAO();
		}
		if (deleted != null) {
			Student student = studentDAO.find(new Long(deleted));
			studentDAO.delete(student);
		} else {
			Student student = new Student();
			if (id != null && !id.equals("")) {
				student.setStudentId(new Long(id));
			}
			student.setFknumber(new Integer(fkNumber));
			student.setLastName(lastName);
			student.setFirstName(firstName);
			student.setFaculty(facultyDAO.find(new Long(facultyId)));
			studentDAO.create(student);
		}

		request.getRequestDispatcher("student.html").forward(request, response);
	}

}
