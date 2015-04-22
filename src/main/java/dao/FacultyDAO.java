package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.Faculty;

public class FacultyDAO {
	private EntityManager em;

	public FacultyDAO() {
		this.em = Persistence.createEntityManagerFactory("fish")
				.createEntityManager();
	}

	public void create(Faculty faculty) {
		em.getTransaction().begin();
		em.persist(faculty);
		em.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<Faculty> findAll() {
		return em.createQuery("select object(f) from Faculty f").getResultList();
	}

	public void delete(Faculty faculty) {
		em.getTransaction().begin();
		em.remove(faculty);
		em.getTransaction().commit();
	}

	public Faculty find(Long id) {
		return em.find(Faculty.class, id);
	}
}
