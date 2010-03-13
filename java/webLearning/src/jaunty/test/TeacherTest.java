package jaunty.test;

import static org.junit.Assert.assertTrue;
import jaunty.hibernate.Teacher;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class TeacherTest {
	public static void main(String[] args) {
		TeacherTest st = new TeacherTest();
		st.runTest();
	}
	
	public void runTest() {
		
		Teacher teacher = new Teacher();
		teacher.setTitle("高级");
		teacher.setId(1);
		teacher.setName("hellojinjie");
		
		
		Configuration cfg = new Configuration();
		cfg.configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(teacher);
		session.getTransaction().commit();
	}
	
	@Test
	public void testTeacher() {
		Teacher teacher = new Teacher();
		teacher.setTitle("高级");
		teacher.setId(1);
		teacher.setName("hellojinjie");
		
		
		Configuration cfg = new AnnotationConfiguration();
		cfg.configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(teacher);
		session.getTransaction().commit();
		assertTrue(true);
	}
}
