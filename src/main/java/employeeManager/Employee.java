package employeeManager;
import java.util.UUID;

public class Employee implements Comparable<Employee>{
	
	private String firstName;
	private String lastName;
	private String title;
	private String employeeId;
	
	public Employee(String firstName, String lastName, String title){
		this(UUID.randomUUID().toString(), firstName, lastName, title);
	}
	
	public Employee(String employeeID, String firstName, String lastName, String title){
		this.employeeId = employeeID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
	}
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getEmployeeId(){
		return this.employeeId;
	}

	@Override
	public int compareTo(Employee employee) {
		return this.firstName.compareTo(employee.firstName);
	}
	
}
