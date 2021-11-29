package com.imtej.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.imtej.hibernate.demo.entity.Student;

public class CreateStudent {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// Create student object
			System.out.println("creating student...");
			Student student = new Student("Jack", "Daniels", "jd@gmail.com");

			// start a transaction
			session.beginTransaction();

			// save the student
			System.out.println("saving the student");
			session.save(student);

			// commit the transaction
			session.getTransaction().commit();

			System.out.println("Done!");

		} finally {
			session.close();
		}

	}

}
