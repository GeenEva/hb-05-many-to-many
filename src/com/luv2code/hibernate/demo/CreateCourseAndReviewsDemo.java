package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;

public class CreateCourseAndReviewsDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
				.configure()						
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			
			session.beginTransaction();
			
		//	Course course = new Course("Aint no cors like dis cors");
		
			Course course = session.get(Course.class, 12);
//			course.addReview(new Review("Incredible! I thought that it was amasing! When is your next corse?!"));
//			course.addReview(new Review("Just... No words for this... So... here are my no words: ..."));
//			course.addReview(new Review("Ai ai aiiii"));
			
			Instructor instructor = session.get(Instructor.class, 2);
			
			course.setInstructor(instructor);
			session.save(course);  				//when cascade.ALL when saving course, the associated objects (i.e. reviews) are also saved
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
