package com.mindtree.restsample.service;

import java.util.List;

import com.mindtree.restsample.dao.EmployeeCrud;
import com.mindtree.restsample.dto.Employee;

public class EmployeeService {

	EmployeeCrud ec = new EmployeeCrud();
	public void addEmployee(Employee e)
	{
		ec.create(e);
	}
	
	public void deleteEmployee(String eid)
	{
		ec.delete(eid);
	}
	
	public List<Employee> getAllEmployees()
	{
		return ec.readAll();
	}
	
	public Employee getEmployee(String empId)
	{
		return ec.readById(empId);
	}
	
	public boolean isEmployeeValid(Employee e)
	{
		Employee emp = ec.getEmployeeByUsername(e.getUsername());
		if(emp.getPassword().equals(e.getPassword()))
			return true;
		return false;
	}
	
}
