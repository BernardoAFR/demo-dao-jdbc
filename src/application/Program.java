package application;

import java.util.Date;

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

		Seller seller = sellerdao.findById(3);
		
		System.out.println(seller);
		// System.out.println(obj);

	}

}
