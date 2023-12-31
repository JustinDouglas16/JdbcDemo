package sr.unasat.jdbc.crud.repositories;

import org.jetbrains.annotations.NotNull;
import sr.unasat.jdbc.crud.database.DatabaseConnectionManager;
import sr.unasat.jdbc.crud.entities.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRepository {
    private Connection connection;

    public EmployeeRepository() {
        this.connection = DatabaseConnectionManager.getConnection();
    }

    public void close() {
        DatabaseConnectionManager.closeConnection(this.connection);
    }

    public int insertOneRecord(@NotNull Employee employee) {
        PreparedStatement statement = null;
        int result = 0;

        try {
            String sql = "INSERT INTO employee (id, first_name, last_name, email) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());
            statement.setString(4, employee.getEmail());
            result = statement.executeUpdate();
            System.out.println("Inserted: " + employee.getFirstName() + " " + employee.getLastName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    public Employee findOneRecord(int id) {
        Employee employee = null;
        PreparedStatement statement = null;
        try {
            String sql = "SELECT * FROM employee WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int idNumber = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                System.out.println("Employee ID: " + id);
                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);
                System.out.println("Email: " + email);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return employee;
    }

    public int updateOneRecord(@NotNull Employee employee) {
        PreparedStatement statement = null;
        int result = 0;
        try {
            String sql = "UPDATE employee SET first_name = ?, last_name = ?, email = ? WHERE employee.id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getEmail());
            statement.setInt(4, employee.getId());
            result = statement.executeUpdate();
            System.out.println("Updated: ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    public int deleteOneRecord(@NotNull Employee employee) {
        PreparedStatement statement = null;
        int result = 0;

        try {
            String sql = "DELETE FROM employee WHERE employee.id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, employee.getId());
            result = statement.executeUpdate();
            System.out.println("Deleted: " + employee.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
}
