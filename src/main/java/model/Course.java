package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.google.gson.annotations.Expose;


/**
 * The persistent class for the COURSE database table.
 * 
 */
@Entity
@NamedQuery(name="Course.findAll", query="SELECT c FROM Course c")
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Expose
	@Column(name="COURSE_ID")
	private Long courseId;
	@Expose
	private Integer credits;
	@Expose
	private String description;
	@Expose
	private String name;

	//bi-directional many-to-one association to StudentCourseRel
	@OneToMany(mappedBy="course")
	private List<StudentCourseRel> studentCourseRels;

	public Course() {
	}

	public long getCourseId() {
		return this.courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public Integer getCredits() {
		return this.credits;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<StudentCourseRel> getStudentCourseRels() {
		return this.studentCourseRels;
	}

	public void setStudentCourseRels(List<StudentCourseRel> studentCourseRels) {
		this.studentCourseRels = studentCourseRels;
	}

	public StudentCourseRel addStudentCourseRel(StudentCourseRel studentCourseRel) {
		getStudentCourseRels().add(studentCourseRel);
		studentCourseRel.setCourse(this);

		return studentCourseRel;
	}

	public StudentCourseRel removeStudentCourseRel(StudentCourseRel studentCourseRel) {
		getStudentCourseRels().remove(studentCourseRel);
		studentCourseRel.setCourse(null);

		return studentCourseRel;
	}

}