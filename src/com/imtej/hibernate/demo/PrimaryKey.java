package com.imtej.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.imtej.hibernate.demo.entity.Student;

public class PrimaryKey {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// Create student object
			System.out.println("creating 3 student...");
			Student student1 = new Student("Jack", "Daniels", "jd@gmail.com");
			Student student2 = new Student("John", "Wick", "johnwick@gmail.com");
			Student student3 = new Student("Rain", "Man", "rainman@gmail.com");

			// start a transaction
			session.beginTransaction();

			// save the student
			System.out.println("saving the students");
			session.save(student1);
			session.save(student2);
			session.save(student3);

			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");

		} finally {
			session.close();
		}

	}


}
