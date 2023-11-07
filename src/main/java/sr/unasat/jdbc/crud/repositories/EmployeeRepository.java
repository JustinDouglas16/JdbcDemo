package sr.unasat.jdbc.crud.repositories;

import org.jetbrains.annotations.NotNull;
import sr.unasat.jdbc.crud.database.DatabaseConnectionManager;
import sr.unasat.jdbc.crud.entities.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public int deleteOneRecord(Employee employee) {
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
