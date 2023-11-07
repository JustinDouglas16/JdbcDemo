package sr.unasat.jdbc.crud.app;

import sr.unasat.jdbc.crud.entities.Employee;
import sr.unasat.jdbc.crud.repositories.EmployeeRepository;

public class Application {
    public static void main(String[] args) {
//        Employee employee = new Employee(1, "Bruce", "Wayne", "brucewayne@gmail.com");
        EmployeeRepository employeeRepository = new EmployeeRepository();
//        employeeRepository.insertOneRecord(employee); // For inserting one record
//        employeeRepository.deleteOneRecord(employee); // For deleting one record
//        employeeRepository.updateOneRecord(employee); // For updating one record
        employeeRepository.findOneRecord(2); // For finding one record
    }
}
