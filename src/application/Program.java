package application;

import model.entities.Department;

public class Program {
	
	public static void main(String[] args) {
		System.out.println("Main program running...");
		
		Department obj = new Department(1, "Books");
		System.out.println(obj);
		
	}

}
