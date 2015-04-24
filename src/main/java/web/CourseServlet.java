package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Course;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.CourseDAO;

/**
 * Servlet implementation class CourseServlet
 */
@WebServlet("/CourseServlet")
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	CourseDAO courseDAO;
    public CourseServlet() {
        super();
        courseDAO = new CourseDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    private static final String STUDENT_ID = "studentId";
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String resource = null;
		List<Course> results = new ArrayList<Course>() ;
		String id = request.getParameter(STUDENT_ID);
		String has = request.getParameter("has");
		if(id!=null){
			if(has.equals("true")){
				results = courseDAO.findByStudent(new Long(id));
			}else{
				results = courseDAO.findNotByStudent(new Long(id));
			}
		}else{
			
			results = courseDAO.findAll();
		}
		resource = gson.toJson(results);
		response.setContentType("application/json");
		response.getWriter().print(resource);
		response.getWriter().flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String CREDITS = "credits";
    private static final String INDEX_TO_DELETE = "deleted";
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter(NAME);
        String description = request.getParameter(DESCRIPTION);
        String credits = request.getParameter(CREDITS);
        String deleted = request.getParameter(INDEX_TO_DELETE);
        if(courseDAO==null){
	        	courseDAO = new CourseDAO();
	        }
        if(deleted!=null){
        	Course course = courseDAO.find(new Long(deleted));
        	courseDAO.delete(course);
        }else{
        	Course course = new Course();
        	course.setName(name);
        	course.setDescription(description);
        	course.setCredits(new Integer(credits));
        	courseDAO.create(course);
        }

       request.getRequestDispatcher("course.html").forward(request, response);
	}

}
