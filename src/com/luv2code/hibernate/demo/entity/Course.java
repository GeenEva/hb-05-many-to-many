package com.luv2code.hibernate.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="course")
public class Course {

//PROPERTY FIELDS
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="title")
	private String title;

	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE, 
							CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name="instructor_id") 	
	private Instructor instructor;
	
	@OneToMany(	cascade = CascadeType.ALL, 				//because when the course is deleted, then also all reviews should be deleted.
				fetch=FetchType.LAZY)
	@JoinColumn(name="course_id")
	private List<Review> reviews;
	
	@ManyToMany(	fetch=FetchType.LAZY,
					cascade= {CascadeType.DETACH, CascadeType.MERGE, 
					CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(		name="course_student",
					joinColumns=@JoinColumn(name="course_id"),
					inverseJoinColumns=@JoinColumn(name="student_id")
				)
	private List<Student> students;
	
	
//CONSTRUCTORS
	
	public Course() {}

	public Course(String title) {
		super();
		this.title = title;
	}

// GETTERS & SETTERS	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}
	

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

// ToString	
	
	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + ", students: " + students + "]";
	}
	
// Convenience Methods	
	
	public void addReview(Review theReview) {
		
		if(reviews == null) {
			reviews = new ArrayList<>();
		}
		
		reviews.add(theReview);
	}
	
	public void addStudent(Student newStudent) {
		
		if(students == null) {
			students = new ArrayList<>();
		}
		
		students.add(newStudent);
	}
}
