package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.DAO.DAOFactory;
import model.DAO.DepartmentDAO;
import model.DAO.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static String dateStr = null;
	public static Date date = null;

	public static Scanner input = new Scanner(System.in);
	public static DepartmentDAO departmentDao = DAOFactory. createDepartmentDao();

	public static SellerDAO sellerdao = DAOFactory.createSellerDao();

	private static void Test1() {
		System.out.println("=== TEST 1: seller findByid ======");

		Integer id = null;
		while (id == null) {
			try {
				System.out.println("Enter id for findByID test: ");
				id = input.nextInt();
				Seller seller = sellerdao.findById(id);
				System.out.println(seller);
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid integer.");
				input.next();
			} catch (Exception e) {
				System.out.println("An error occurred: " + e.getMessage());
			}
		}

	}

	private static void Test2() {
		System.out.println("\n=== TEST 2: seller findByDepartment ======");

		Integer DepId = null;
		while (DepId == null) {
			try {
				System.out.println("Enter id for findByDepartment test: ");
				DepId = input.nextInt();
				input.nextLine();
				Department departmentLoc = departmentDao.findById(DepId);

				while( departmentLoc == null) {
					System.out.print("Department Id not found! Please, select order Id: ");
					DepId = input.nextInt();
					input.nextLine();
					departmentLoc = departmentDao.findById(DepId);

				}
				Department department = new Department(DepId, null);

				List<Seller> sellers = sellerdao.findByDepartment(department);
				for (Seller seller : sellers) {
					System.out.println(seller);
				}
				System.out.println(sellers.size());
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid integer.");
				input.next();
			} catch (Exception e) {
				System.out.println("An error occurred: " + e.getMessage());
			}
		}

	}

	private static void Test3() {
		System.out.println("\n=== TEST 3: seller findAll ======");
		List<Seller> AllSellers = sellerdao.findall();
		for (Seller seller : AllSellers) {
			System.out.println(seller);
		}
	}

	private static void Test4() {
		System.out.println("\n=== TEST 4: seller insert ======");

		String name = null;
		String email = null;
		Double baseSalary = null;
		Integer departmentID = null;

		while (name == null && email == null && departmentID == null) {

			try {

				System.out.println("Enter with the seller name: ");
				input.nextLine();
				name = input.nextLine();

				System.out.println("Enter with the seller email: ");
				email = input.nextLine();

				while (date == null && baseSalary == null) {
					try {
						System.out.print("Enter with a birthdate seller (yyyy-MM-dd HH:mm:ss): ");
						dateStr = input.nextLine();
						date = dateFormat.parse(dateStr);

						System.out.println("Enter with a base salary seller: ");
						baseSalary = input.nextDouble();
						input.nextLine();
					}

					catch (ParseException e) {
						System.out.println(
								"Invalid date format. Please enter the date in the format yyyy-MM-dd HH:mm:ss.");
						date = null;
					} catch (InputMismatchException e) {
						System.out.println("Invalid input. Please enter a valid number for the salary.");
						input.nextLine();
						baseSalary = null;
					} catch (Exception e) {
						System.out.println("An error occurred: " + e.getMessage());
						date = null;
						baseSalary = null;
					}

				}

				System.out.print("Enter with a department ID: ");
				departmentID = input.nextInt();
				input.nextLine();

				Seller newSeller = new Seller(null, name, email, date, baseSalary, new Department(departmentID, null));
				sellerdao.insert(newSeller);
				System.out.println("Inserted! New id = " + newSeller.getId());

			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());

				name = null;
				email = null;
				date = null;
				baseSalary = null;
				departmentID = null;
			}

		}

	}

	private static void Test5() throws ParseException {
		System.out.println("\n=== TEST 5: seller update ======");
		Seller UpdateSeller = null;
		Integer sellerID = null;

		while (sellerID == null) {
			try {
				System.out.println("\nEnter with a seller id to data update: ");
				sellerID = input.nextInt();
				input.nextLine();
				UpdateSeller = sellerdao.findById(sellerID);

				if (UpdateSeller != null) {
					System.out.println(UpdateSeller);
				} else {
					System.out.println("No seller found with the provided ID. Please try again.");
					sellerID = null;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid integer.");
				input.next();
			} catch (Exception e) {
				System.out.println("An error occurred: " + e.getMessage());
			}

		}

		String executeUpdate = "yes";
		String UpdateDate;

		do {

			System.out.println("Please, select your data for update: ");
			System.out.println("---------------------------"
					+ "\nname " + 
					"\nemail " 
					+ "\nbirthdate "
					+ "\nbaseSalary " 
					+ "\ndepartment "
					+ "\nquit - To quit");
			
			UpdateDate = input.nextLine();

			switch (UpdateDate) {
			case "name":
                System.out.print("Enter the new name: ");
				String name = input.nextLine();
				UpdateSeller.setName(name);
				sellerdao.update(UpdateSeller);
				break;

			case "email":
                System.out.println("Enter the new email: ");
				String email = input.nextLine();
				UpdateSeller.setEmail(email);
				sellerdao.update(UpdateSeller);
				break;

			case "birthdate":
				try {
					System.out.print("Enter the new birthdate (yyyy-MM-dd HH:mm:ss): ");
					dateStr = input.nextLine();
					date = dateFormat.parse(dateStr);
					UpdateSeller.setBirthDate(date);
					sellerdao.update(UpdateSeller);
				} catch (ParseException e) {
					System.out.println("Invalid date format. Please enter the date in the format yyyy-MM-dd HH:mm:ss.");
				}
				break;

			case "baseSalary":
			    try {
			        System.out.print("Enter the new base salary: ");
			        Double baseSalary = input.nextDouble();
			        UpdateSeller.setBaseSalary(baseSalary);
			        sellerdao.update(UpdateSeller);
			        input.nextLine(); 
			    } catch (InputMismatchException e) {
			        System.out.println("Invalid input. Please enter a valid number for the salary.");
			        input.nextLine(); 
			    }
			    break;
			case "department":
				try {
                    System.out.print("Enter the new department ID: ");
					Integer departmentId = input.nextInt();
					UpdateSeller.setDepartment(new Department(departmentId, null));
					sellerdao.update(UpdateSeller);
					input.nextLine();
				} catch (InputMismatchException e) {
					System.out.println("Invalid input. Please enter a valid department ID.");
					input.nextLine();
				}
				break;

			case "quit":
				System.out.println("Exiting program...");
				executeUpdate = "no";
				break;

			default:
				System.out.println("Invalid option. Please select a valid test.");
				break;
			}

			if (!UpdateDate.equals("quit")) {
				System.out.println("Continue data update ?('yes' or 'no')");
				executeUpdate = input.nextLine().toLowerCase();
			}

		} while (executeUpdate.equals("yes"));

		if (UpdateSeller != null) {
			sellerdao.update(UpdateSeller);
			System.out.println("Update completed!");
		}

	}

	private static void Test6() {

		System.out.println("\n=== TEST 6: seller delete ======");

		String deleteSeller = "yes";
		Integer sellerID = null;

		do {
			System.out.println("\nEnter with a seller id to delete: ");
			
			while (sellerID == null) {
				try {
			
					sellerID = input.nextInt();
					input.nextLine();
					sellerdao.deleteById(sellerID);

				} catch (InputMismatchException e) {
					System.out.println("Invalid input. Please enter a valid integer.");
					input.nextLine();
				} catch (Exception e) {
					System.out.println("An error occurred: " + e.getMessage());
					input.nextLine();
				}

			}
			
			sellerID = null;
			System.out.println("\nAre there more sellers to exclude?('yes' or 'no'): ");
			deleteSeller = input.nextLine();
			
		} while (deleteSeller.equals("yes"));

		System.out.println("Delete completed!");

	}
	

	public static void main(String[] args) throws ParseException {
		System.out.println("Program 'seller' test running...");

		String execute = "yes";
		Integer test = null;
		do {
			System.out.println("Please, select your test: ");
			System.out.println("---------------------------" 
					+ "\n1 - Find by Id "
					+ "\n2 - FindByDepartment "
					+ "\n3 - FindAll" 
					+ "\n4 - Insert a seller " 
					+ "\n5 - Update a seller " 
					+ "\n6 - Delete a seller "
					+ "\n7 - To quit");
			
			test = input.nextInt();

			switch (test) {
			case 1:
				Test1();
				break;

			case 2:
				Test2();
				break;

			case 3:
				Test3();
				break;

			case 4:
				Test4();
				break;

			case 5:
				Test5();
				break;
				
			case 6:
				Test6();
				break;

			case 7:
				System.out.println("Exiting program...");
				execute = "no";
				break;

			default:
				System.out.println("Invalid option. Please select a valid test.");
				break;
			}
			
			input.nextLine();
			if (test != 7) {
				System.out.print("\nContinue execution?('yes' or 'no'): ");
				execute = input.nextLine();
			} else
				execute = "no";

		} while (execute.equals("yes"));

	}

}
