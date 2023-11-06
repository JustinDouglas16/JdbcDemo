package sr.unasat.jdbc.crud.app;

import sr.unasat.jdbc.crud.entities.Employee;
import sr.unasat.jdbc.crud.repositories.EmployeeRepository;

public class Application {
    public static void main(String[] args) {
        Employee employee = new Employee(3, "Bruce", "Wayne", "brucewayne@gmail.com");
        EmployeeRepository employeeRepository = new EmployeeRepository();
        employeeRepository.insertOneRecord(employee);
    }
}
