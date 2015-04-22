package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.Student;

public class StudentDAO {

	private EntityManager em;

	public StudentDAO() {
		this.em = Persistence.createEntityManagerFactory("fish")
				.createEntityManager();
	}

	public void create(Student student) {
		em.getTransaction().begin();
		if (student.getStudentId() != null) {
			em.persist(student);
		} else {
			em.merge(student);
			em.persist(student);
		}
		em.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<Student> findAll() {
		return em.createQuery("select object(s) from Student s")
				.getResultList();
	}

	public void delete(Student student) {
		em.getTransaction().begin();
		em.remove(student);
		em.getTransaction().commit();
	}

	public Student find(Long id) {
		return em.find(Student.class, id);
	}
}
