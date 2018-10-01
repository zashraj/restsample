package com.mindtree.restsample.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.service.ServiceRegistry;

import com.mindtree.restsample.dto.Employee;

public class EmployeeCrud {
	private static Session session;
	private SessionFactory sessionFactory;
	
	public EmployeeCrud()
	{
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		config.addAnnotatedClass(com.mindtree.restsample.dto.Employee.class);
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(config.getProperties())
				.build();
		sessionFactory = config.buildSessionFactory(serviceRegistryObj);
	}
	
	public EmployeeCrud(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}



	public void create(Employee e) {
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			try {
				session.save(e);
			} catch (ConstraintViolationException ex) {
				System.out.println("Haha =" +ex.getErrorCode());
			}
			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void update(Employee e) {
		session = sessionFactory.openSession();
		session.beginTransaction();
	}

	public Employee readById(String empid) {
		Employee emp = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			emp = session.load(Employee.class, empid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}

	public List<Employee> readAll() {
		List<Employee> employeelist = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			employeelist = (List<Employee>) session.createQuery("from Employee").list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return employeelist;
	}

	public Employee getEmployeeByUsername(String name)
	{
		Employee emp = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Employee> e = session.createQuery("from Employee where username like :u").setParameter("u", name).list();
			emp=e.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return emp;
	}
	public void delete(String empid) {
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Employee emp = readById(empid);
			session.delete(emp);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
