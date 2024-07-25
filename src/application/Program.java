package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.DAO.DAOFactory;
import model.DAO.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class Program {
	
	public static SellerDAO sellerdao = DAOFactory.createSellerDao();
	

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Program 'seller' test running...");

		/*
		 * Department obj = new Department(1, "Books"); Seller seller = new Seller(21,
		 * "Bob", "bob@gmail.com", new Date(), 3000.0, obj);
		 * System.out.println(obj);
		 */
	
		
		System.out.println("=== TEST 1: seller findByid ======");
		Seller seller = sellerdao.findById(3);
		System.out.println(seller);
		
		System.out.println("\n=== TEST 2: seller findByDepartment ======");
		Department department = new Department(2, null);
		List<Seller> list = sellerdao.findByDepartment(department);
		for(Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== TEST 3: seller findAll ======");
		list = sellerdao.findall();
		for(Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== TEST 4: seller insert ======");
		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
		sellerdao.insert(newSeller);
		System.out.println("Inserted! New id = " + newSeller.getId());

		System.out.println("\n=== TEST 5: seller update ======");
		seller = sellerdao.findById(1);
		seller.setName("Marta Waine");
		sellerdao.update(seller);
		System.out.println("Update completed!");

		System.out.println("\n=== TEST 6: seller delete ======");
		System.out.println("Enter id for delete test: ");
		int id = input.nextInt();
		sellerdao.deleteById(id);
		System.out.println("Delete completed!");
		
		input.close();
		
	}

}
                                                                                                                     