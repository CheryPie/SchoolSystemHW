package dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.Student;
import model.StudentCourseRel;

public class StudentCourseRelDAO {
	private EntityManager em;

	public StudentCourseRelDAO() {
		this.em = Persistence.createEntityManagerFactory("fish")
				.createEntityManager();
	}

	public void create(StudentCourseRel rel) {
		em.getTransaction().begin();	
		if (rel.getRelId() == null) {
			em.persist(rel	);
		} 
		em.getTransaction().commit();
	}


	public void delete(StudentCourseRel rel) {
		em.getTransaction().begin();
		em.remove(rel);
		em.getTransaction().commit();
	}

	public Student find(Long id) {
		return em.find(Student.class, id);
	}
	
	public void deleteWithIds(String studentId, String courseId){
		em.getTransaction().begin();	
		em.createNativeQuery("delete from student_course_rel where student_id="+studentId+" and course_id="+courseId).executeUpdate();
		em.getTransaction().commit();
	}
}
