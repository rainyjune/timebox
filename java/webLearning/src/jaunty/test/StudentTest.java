package jaunty.test;

import jaunty.hibernate.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class StudentTest {
	public static void main(String[] args) {
		StudentTest st = new StudentTest();
		st.runTest();
	}
	
	public void runTest() {
		
		Student student = new Student();
		student.setAge(23);
		student.setId(1);
		student.setName("hellojinjie");
		
		
		Configuration cfg = new Configuration();
		cfg.configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(student);
		session.getTransaction().commit();
	}
}
