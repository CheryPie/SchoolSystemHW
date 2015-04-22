package model;

import java.io.Serializable;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

import java.util.List;


/**
 * The persistent class for the FACULTY database table.
 * 
 */
@Entity
@NamedQuery(name="Faculty.findAll", query="SELECT f FROM Faculty f")
public class Faculty implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Expose
	@Column(name="FACULTY_ID")
	private Long facultyId;
	@Expose
	private String name;

	//bi-directional many-to-one association to Student
	@OneToMany(mappedBy="faculty",orphanRemoval=true,cascade=CascadeType.REMOVE)
	private List<Student> students;

	public Faculty() {
	}

	public Long getFacultyId() {
		return this.facultyId;
	}

	public void setFacultyId(Long facultyId) {
		this.facultyId = facultyId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Student> getStudents() {
		return this.students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Student addStudent(Student student) {
		getStudents().add(student);
		student.setFaculty(this);

		return student;
	}

	public Student removeStudent(Student student) {
		getStudents().remove(student);
		student.setFaculty(null);

		return student;
	}

}