package application;

import java.util.List;

import model.DAO.DAOFactory;
import model.DAO.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		System.out.println("Main program running...");

		/*
		 * Department obj = new Department(1, "Books"); Seller seller = new Seller(21,
		 * "Bob", "bob@gmail.com", new Date(), 3000.0, obj);
		 */
		
	
		SellerDAO sellerdao = DAOFactory.createSellerDao();

		System.out.println("=== TEST 1: seller findByid ======");
		
		Seller seller = sellerdao.findById(3);
		
		System.out.println(seller);
		// System.out.println(obj);
		System.out.println("\n=== TEST 1: seller findByDepartment ======");
		Department department = new Department(2, null);
		List<Seller> list = sellerdao.findByDepartment(department);
		for(Seller obj : list) {
			System.out.println(obj);
		}
	}

}
                                                                                                                     