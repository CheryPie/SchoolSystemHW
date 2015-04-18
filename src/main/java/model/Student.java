package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the STUDENT database table.
 * 
 */
@Entity
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="STUDENT_ID")
	private Long studentId;

	@Column(name="FIRST_NAME")
	private String firstName;

	private Integer fkNumber;

	@Column(name="LAST_NAME")
	private String lastName;

	//bi-directional many-to-one association to Faculty
	@ManyToOne
	@JoinColumn(name="FACULTY_ID")
	private Faculty faculty;

	//bi-directional many-to-one association to StudentCourseRel
	@OneToMany(mappedBy="student")
	private List<StudentCourseRel> rels;

	public Student() {
	}

	public long getStudentId() {
		return this.studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Integer getFknumber() {
		return this.fkNumber;
	}

	public void setFknumber(Integer fknumber) {
		this.fkNumber = fknumber;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Faculty getFaculty() {
		return this.faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public List<StudentCourseRel> getRels() {
		return this.rels;
	}

	public void setStudentCourseRels(List<StudentCourseRel> rels) {
		this.rels = rels;
	}

}