package com.mindtree.restsample.dao;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mindtree.restsample.dto.Employee;

public class EmployeeCrudTest {
	private SessionFactory sessionFactory;
	//private Session session = null;

	/*@Before
	public void before() {

		// setup the session factory

		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(Employee.class);
		configuration.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
		configuration.setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver");
		configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/employees");
		configuration.setProperty("hibernate.connection.username", "root");
		configuration.setProperty("hibernate.connection.password", "toor");
		configuration.setProperty("hibernate.hbm2ddl.auto", "create");

		sessionFactory = configuration.buildSessionFactory();
		session = sessionFactory.openSession();

	}*/
	
	@Test
	public void testadd()
	{
		Employee e = new Employee();
		e.setDob("12-Oct-1994");
		e.setEmpid("abc123");
		e.setFullName("testName");
		e.setGender("Male");
		//session.save(e);
		
		//Employee emp = session.load(Employee.class, "abc123");
		//assertNotNull(emp);
		assertEquals(e.getFullName(),"testName");
	}
	
	
	/*@After
	public void after() {
		session.close();
		sessionFactory.close();

	}*/
}
