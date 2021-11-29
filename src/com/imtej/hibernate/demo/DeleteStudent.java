package com.imtej.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.imtej.hibernate.demo.entity.Student;

public class DeleteStudent {

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
			System.out.println("Deleting student with ID: 4");
			Student student = session.get(Student.class, 4);
			session.delete(student);

			// commit the transaction
			session.getTransaction().commit();

			// start a new transaction
			session = factory.getCurrentSession();
			session.beginTransaction();

			// retrieve student based on primary key
			System.out.println("Deleting student with ID: 2");
			session.createQuery("delete from Student where id=2").executeUpdate();

			// commit the transaction
			session.getTransaction().commit();

		} finally {
			session.close();
		}

	}

}
