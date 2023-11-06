package sr.unasat.jdbc.crud.app;

import sr.unasat.jdbc.crud.entities.Employee;
import sr.unasat.jdbc.crud.repositories.EmployeeRepository;

public class Application {
    public static void main(String[] args) {
        Employee employee = new Employee(2, "Peter", "Parker", "peterparker@gmail.com");
        EmployeeRepository employeeRepository = new EmployeeRepository();
        employeeRepository.insertOneRecord(employee);
    }
}
