package application;

import java.util.List;
import java.util.Scanner;

import model.DAO.DAOFactory;
import model.DAO.DepartmentDAO;
import model.entities.Department;
import model.entities.Seller;

public class Program2 {

	public static DepartmentDAO departmentDao = DAOFactory. createDepartmentDao();

	
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		System.out.println("Program 'department' test running...");
		
		System.out.println("\n=== TEST 1: department insert ======");
		Department newDepartment = new Department(null, "Vehicles");
		departmentDao.insert(newDepartment);
		System.out.println("Inserted! New id = " + newDepartment.getId());

		System.out.println("\n=== TEST 2: department update ======");
		Department RandomDepartment = departmentDao.findById(6);
		RandomDepartment.setName("Sports");
		departmentDao.update(RandomDepartment);
		System.out.println("Update completed!");
		
		System.out.println("\n=== TEST 3: department findById ======");
		System.out.println(RandomDepartment);
	
		System.out.println("\n=== TEST 4: department deleteById ======");
		System.out.println("Enter id for delete test: ");
		int depId = input.nextInt();
		departmentDao.deleteById(depId);
		System.out.println("Delete completed!");


		System.out.println("\n=== TEST 3: seller findAll ======");
		List<Department> departments = departmentDao.findall();
		for(Department department : departments)
			System.out.println(department);
		
		
	}

}
