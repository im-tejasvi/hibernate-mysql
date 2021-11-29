package com.imtej.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.imtej.hibernate.demo.entity.Student;

public class QueryStudent {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a transaction
			session.beginTransaction();

			// query the student
			System.out.println("querying students");
			List<Student> students = session.createQuery("from Student").getResultList();

			displayStudents(students);

			System.out.println("querying students whose lastname is durden");
			students = session.createQuery("from Student where lastName='Durden'").getResultList();

			displayStudents(students);

			System.out.println("querying students whose lastname is durden or firstname is jack");
			students = session.createQuery("from Student s where s.lastName='Durden' or s.firstName='Jack'")
					.getResultList();

			displayStudents(students);

			System.out.println("querying students whose email ends with hotmail.com");
			students = session.createQuery("from Student s where s.email like '%hotmail.com'").getResultList();

			displayStudents(students);

			// commit the transaction
			session.getTransaction().commit();

			System.out.println("Done!");

		} finally {
			session.close();
		}

	}

	private static void displayStudents(List<Student> students) {
		for (Student s : students) {
			System.out.println(s);
		}
	}

}
