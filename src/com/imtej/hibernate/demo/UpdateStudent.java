package com.imtej.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.imtej.hibernate.demo.entity.Student;

public class UpdateStudent {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start a transaction
			session.beginTransaction();

			// retrieve student based on primary key
			System.out.println("Fetching student with ID: 1");
			Student student = session.get(Student.class, 1);
			student.setEmail("jack@daniels.com");

			// commit the transaction
			session.getTransaction().commit();

			// start a new transaction
			session = factory.getCurrentSession();
			session.beginTransaction();

			// retrieve student based on primary key
			System.out.println("Updating student emails");
			session.createQuery("update Student set email='foo@bar.com'").executeUpdate();

			// commit the transaction
			session.getTransaction().commit();

		} finally {
			session.close();
		}

	}

}
