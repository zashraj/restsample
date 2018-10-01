package com.mindtree.restsample.restapi;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import com.mindtree.restsample.dto.Employee;
import com.mindtree.restsample.service.EmployeeService;

public class RestApiTest {

	
	EmployeeService service = Mockito.mock(EmployeeService.class);
	
	
	public Employee getDummyEmployee()
	{
		Employee e = new Employee();
		e.setEmpid("TestId");
		e.setFullName("TestName");
		e.setDob("1-Jan-1992");
		e.setEmail("abc@xyz.com");
		e.setGender("F");
		return e;
	}
	@Test
	public void getEmployeesById()
	{
		Employee e = getDummyEmployee();
		Mockito.when(service.getEmployee("TestId")).thenReturn(e);
		EmployeeRest rest = new EmployeeRest(service);
		Employee emp = (Employee) rest.getEmployeeById("TestId").getEntity();
		assertNotNull(emp);
	}
	
	@Test
	public void getAllEmployees()
	{
		Employee e = getDummyEmployee();
		List<Employee> list = new ArrayList<>();
		list.add(e);
		Mockito.when(service.getAllEmployees()).thenReturn(list);
		EmployeeRest rest = new EmployeeRest(service);
		assertEquals(rest.getAllEmployees().getEntity(),list);
	}
	
	
}
