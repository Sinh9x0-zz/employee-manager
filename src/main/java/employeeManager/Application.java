package employeeManager;

import java.util.Scanner;

public class Application {

	public static void main(String[] args){
		EmployeeManager em = new EmployeeManager();
		em.uploadEmployees();

		System.out.println("Welcome to Employee Manager.");
		System.out.println("You can (A)dd an employee or "
							+ "(V)iew all employees.");
		System.out.print("What would you like to do? ");
		
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		
		while(!input.equals("exit")){
			System.out.println();
			switch (input) {
	            case "A":  em.addEmployee(scanner);
	            	break;
	            case "V":  em.printEmployees();
	            	break;
	            default: System.out.println("Your input is invalid. Please try again.");
	            	break;
			}
			
			System.out.println("You can (A)dd an employee or "
								+ "(V)iew all employees. ");
        	System.out.println("Alternatively, type 'exit' to end the program.");
        	System.out.print("What would you like to do? ");
			input = scanner.nextLine();
		}
		
		scanner.close();
	}	
}
