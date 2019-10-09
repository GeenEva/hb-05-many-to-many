package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.DateUtils;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateStudentWithCourse {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
				.configure()						
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			
			session.beginTransaction();
			
	/*		Course tempCourse = new Course("MyFifthCourse");
			session.save(tempCourse);
			
			Student student = new Student("123123", "Ak21313a", "mail");
			Student student2 = new Student("Tu2131314ki", "Ol2113o", "meeuw");
			
			tempCourse.addStudent(student);
			tempCourse.addStudent(student2);
			
			session.save(student);
			session.save(student2);
			*/
			
			Course tempCourse = new Course("MyThirdCourse");		
			
			Student student = new Student("Hama", "Okiodu", "beeuw");
			Student student2 = new Student("Tama", "werwke", "leeuw");
			
			tempCourse.addStudent(student);
			tempCourse.addStudent(student2);
			
			session.persist(tempCourse);	
			
			session.getTransaction().commit();
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			session.close();
			factory.close();
		}


	}

}
