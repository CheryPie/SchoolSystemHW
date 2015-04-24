package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Course;
import model.Student;
import model.StudentCourseRel;
import dao.CourseDAO;
import dao.StudentCourseRelDAO;
import dao.StudentDAO;

/**
 * Servlet implementation class RelationSCServlet
 */
@WebServlet("/RelationSCServlet")
public class RelationSCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	StudentDAO studentDAO;
	CourseDAO courseDAO;
	StudentCourseRelDAO relDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RelationSCServlet() {
        super();
         studentDAO = new StudentDAO();
    	 courseDAO = new CourseDAO();
    	 relDAO = new StudentCourseRelDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	private static final String STUDENT_ID ="studentId";
	private static final String COURSE_ID="course";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String studentId = request.getParameter(STUDENT_ID);
			String courseId = request.getParameter(COURSE_ID);
			String delete = request.getParameter("delete");
			String create = request.getParameter("create");
			if(studentId!=null && courseId!=null){
				if(create!=null && create.equals("true")){
					Student student = studentDAO.find(new Long(studentId));
					Course course = courseDAO.find(new Long(courseId));
					StudentCourseRel rel = new StudentCourseRel();
					rel.setStudent(student);
					rel.setCourse(course);
					relDAO.create(rel);
				}else if(delete!=null && delete.equals("true")){
					relDAO.deleteWithIds(studentId, courseId);
				}
			}
			request.getRequestDispatcher("student.html").forward(request, response);
	}

}
