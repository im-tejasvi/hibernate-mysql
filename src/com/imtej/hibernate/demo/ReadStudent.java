package com.imtej.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.imtej.hibernate.demo.entity.Student;

public class ReadStudent {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// Create student object
			System.out.println("creating student...");
			Student student = new Student("Tyler", "Durden", "tdfc@gmail.com");
			System.out.println("created student: " + student);

			// start a transaction
			session.beginTransaction();

			// save the student
			System.out.println("saving the student");
			session.save(student);

			// commit the transaction
			session.getTransaction().commit();

			System.out.println("Done!");

			// find out generated primary key
			System.out.println("Generated student ID: " + student.getId());

			// create new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();

			// retrieve student based on primary key

			System.out.println("Fetching student with ID: " + student.getId());
			Student myStudent = session.get(Student.class, student.getId());
			System.out.println("Fetched student: " + student);

			// commit the transaction
			session.getTransaction().commit();

		} finally {
			session.close();
		}

	}

}
