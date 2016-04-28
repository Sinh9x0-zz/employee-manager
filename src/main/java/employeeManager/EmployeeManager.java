package employeeManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class EmployeeManager {

	private HashMap<String, Employee> employees = new HashMap<String, Employee>();
	private int employeeCount;
	
	public void printEmployees(){
     
        ArrayList<Employee> sortedEmployees = new ArrayList<Employee>(employees.values());
        Collections.sort(sortedEmployees);
                       
		for (int i = 0; i < employees.size(); i++){
             System.out.println("Employee #" 
            		 			+ sortedEmployees.get(i).getEmployeeId() + " " 
            		 			+ sortedEmployees.get(i).getFirstName() + " " 
            		 			+ sortedEmployees.get(i).getLastName());
		}             
		
		System.out.println();
	}
	
	public void addEmployee(Scanner scanner){
		String firstName, lastName, title;
		System.out.println("New Employee's first name: ");
		firstName = scanner.nextLine();
		System.out.println("New Employee's last name: ");
		lastName = scanner.nextLine();
		System.out.println("New Employee's job title: ");
		title = scanner.nextLine();
		
		System.out.println();
		Employee employee = new Employee(firstName, lastName, title);
		employees.put(employee.getEmployeeId(), employee);
		System.out.println("Employee succesfully entered into system.");
				
		try {
	        FileInputStream file = new FileInputStream(new File("EmployeeData.xlsx"));
	        XSSFWorkbook workbook = new XSSFWorkbook(file);	
	        XSSFSheet sheet = workbook.getSheetAt(0);
            
            //Creates a new entry in spreadsheet
            Row row = sheet.createRow(employeeCount + 1);
            row.createCell(0).setCellValue(employee.getEmployeeId());
            row.createCell(1).setCellValue(employee.getFirstName());
            row.createCell(2).setCellValue(employee.getLastName());
            row.createCell(3).setCellValue(employee.getTitle());
            
            employeeCount++;
            FileOutputStream fileOut = new FileOutputStream("EmployeeData.xlsx");
            workbook.write(fileOut);
            
            file.close();   
            workbook.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void uploadEmployees() {
		try {
			//TODO: If it exist open it, otherwise create it.
			//System.out.println(new File("EmployeeData.xlsx").exists());

	        FileInputStream file = new FileInputStream(new File("EmployeeData.xlsx"));
	        XSSFWorkbook workbook = new XSSFWorkbook(file);	
	        XSSFSheet sheet = workbook.getSheetAt(0);
	        
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); //Skips the header row
            employeeCount = 0;
            
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                
                String employeeId = row.getCell(0).getStringCellValue();
                String firstName = row.getCell(1).getStringCellValue();
                String lastName = row.getCell(2).getStringCellValue();
                String title = row.getCell(3).getStringCellValue();
                
                Employee employee = new Employee(employeeId, firstName, lastName, title);
                employees.put(employee.getEmployeeId(), employee);
                employeeCount++;
            }
           
            file.close();   
            workbook.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
