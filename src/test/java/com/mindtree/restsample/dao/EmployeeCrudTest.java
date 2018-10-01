package com.mindtree.restsample.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.mindtree.restsample.dto.Employee;

public class EmployeeCrudTest {

	private  SessionFactory sessionFactory;
	private  Session session;
	private  EmployeeCrud crud;
	
	private  Configuration conf;
	
	private  ServiceRegistry serviceRegistryObj;
	
	@Before
	public void before() {

		// setup the session factory

		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(Employee.class);
		configuration.setProperty("hibernate.dialect","org.hibernate.dialect.H2Dialect");
		configuration.setProperty("hibernate.connection.driver_class","org.h2.Driver");
		configuration.setProperty("hibernate.connection.url", "jdbc:h2:mem:employees");
		configuration.setProperty("hibernate.connection.username", "root");
		configuration.setProperty("hibernate.connection.password", "toor");
		configuration.setProperty("hibernate.hbm2ddl.auto", "create");
		
		serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
				.build();
		sessionFactory = configuration.buildSessionFactory();
		session = sessionFactory.openSession();

	}
	
	public Employee loadDummyEmployeeInDb()
	{
		Employee e = new Employee();
		e.setEmpid("testId");
		e.setDob("1-Jan-1992");
		e.setEmail("abc@xyz.com");
		e.setFullName("Test Name");
		e.setGender("F");
		e.setPassword("p@$sword");
		return e;
	}
	
	@Test
	public void testadd()
	{
		Employee e = new Employee();
		e.setDob("12-Oct-1994");
		e.setEmpid("abc123");
		e.setFullName("testName");
		e.setGender("Male");
		EmployeeCrud ec = new EmployeeCrud(sessionFactory);
		ec.create(e);
		Employee emp = ec.readById("abc123");
		assertNotNull(emp);
		assertEquals(emp.getFullName(),"testName");
	}
	@Test
	public void testGetAllemployees()
	{
		EmployeeCrud ec = new EmployeeCrud(sessionFactory);
		ec.create(loadDummyEmployeeInDb());
		List<Employee> list =ec.readAll();
		System.out.println(list);
		assertNotNull(list);
		assertEquals(list.size(),1);
	}
	
	@Test
	public void testDelete()
	{
		EmployeeCrud ec = new EmployeeCrud(sessionFactory);
		ec.create(loadDummyEmployeeInDb());
		Employee e = ec.readById("testId");
		assertEquals(e.getFullName(),"Test Name");
		
		ec.delete("testId");
		assertEquals(ec.readAll().size(),0);
	}
	
	
	@After
	public void after() {
		//session.close();
		sessionFactory.close();

	}
}
