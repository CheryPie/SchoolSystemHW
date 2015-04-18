package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the STUDENT_COURSE_REL database table.
 * 
 */
@Entity
@Table(name="STUDENT_COURSE_REL")
@NamedQuery(name="StudentCourseRel.findAll", query="SELECT s FROM StudentCourseRel s")
public class StudentCourseRel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="REL_ID")
	private Long relId;

	//bi-directional many-to-one association to Course
	@ManyToOne
	@JoinColumn(name="COURSE_ID")
	private Course course;

	//bi-directional many-to-one association to Student
	@ManyToOne
	@JoinColumn(name="STUDENT_ID")
	private Student student;

	public StudentCourseRel() {
	}

	public Long getRelId() {
		return this.relId;
	}

	public void setRelId(Long relId) {
		this.relId = relId;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}