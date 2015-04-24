package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.Course;

public class CourseDAO {
private EntityManager em;
	
	public CourseDAO(){
		this.em = Persistence.createEntityManagerFactory("fish").createEntityManager();
	}

	public void create(Course course){
		em.getTransaction().begin();
		em.persist(course);
		em.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<Course> findAll(){
		return em.createQuery("select object(c) from Course c").getResultList();
	}
	
	public void delete(Course course){
		em.getTransaction().begin();
		em.remove(course);
		em.getTransaction().commit();
	}
	
	public Course find(Long id){
		return em.find( Course.class,id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Course> findByStudent(Long studentId){
		if(studentId!=null){
		    return em.createNativeQuery("select * from course where Course_Id in "
		       		+ " (select course_id from student_course_rel where student_id="+studentId.toString()+")",Course.class).getResultList();
		}
		return new ArrayList<Course>();
	}
	
	@SuppressWarnings("unchecked")
	public List<Course> findNotByStudent(Long studentId){
		if(studentId!=null){
		    return em.createNativeQuery("select * from course where Course_Id not in "
		       		+ " (select course_id from student_course_rel where student_id="+studentId.toString()+")",Course.class).getResultList();
		}
		return new ArrayList<Course>();
	}
}
