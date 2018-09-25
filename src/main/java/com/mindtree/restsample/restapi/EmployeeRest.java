package com.mindtree.restsample.restapi;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mindtree.restsample.dao.EmployeeCrud;
import com.mindtree.restsample.dto.Employee;
import com.mindtree.restsample.service.EmployeeService;


@Path("/EmpMgt")
//@Api(value = "EmpMgt", description = "REST Apis related to Employee Entity!!!!")
public class EmployeeRest {

	@PUT
	@Path("/addEmp")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	/*@ApiOperation(value = "Add Employees to Database", response = List.class, tags = "getAllEmpDetails")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 404, message = "not found!!!") })*/
	public Response createEmployee(Employee e) {
		EmployeeService service = new EmployeeService();
		service.addEmployee(e);
		return Response.status(200).entity("Employee data inserted successfully.").build();
	}

	@PUT
	@Path("/deleteEmp/{empid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	/*@ApiOperation(value = "Get list of Employees", response = List.class, tags = "getAllEmpDetails")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 404, message = "not found!!!") })*/
	public Response createEmployee(@PathParam("empid") String empid) {
		EmployeeService service = new EmployeeService();
		service.deleteEmployee(empid);
		return Response.status(200).entity("Employee data deleted successfully").build();
	}

	@GET
	@Path("/getAllEmpDetails")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	/*@ApiOperation(value = "Get list of Employees", response = List.class, tags = "getAllEmpDetails")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 404, message = "not found!!!") })*/
	public Response getAllEmployees() {
		EmployeeService service = new EmployeeService();
		List<Employee> l = service.getAllEmployees();
		return Response.status(200).entity(l).build();
	}

	@GET
	@Path("/getByEmpId/{empid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getEmployeeById(@PathParam("empid") String empid) {
		EmployeeCrud c = new EmployeeCrud();
		Employee e = c.readById(empid);
		return Response.status(200).entity(e).build();
	}

	@POST
	@Path("/checkLogin")
	public Response checkValidity(Employee e) {
		EmployeeService service = new EmployeeService();
		if (service.isEmployeeValid(e)) {
			return Response.status(200).entity("Employee has authenticated successfully").build();
		}
		return Response.status(404).entity("Invalid Username and Password").build();

	}
}
